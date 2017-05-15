package com.fiskra.atm.finder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiskra.atm.finder.model.Atm;

public class RestConsume {
	
	private static final char[] protectingCharacters = {')', ']', '}', '\'', ','};

	public static void main(String[] args) throws IOException {
		System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
		testWithJackson();
	}

	public static void testWithJackson() throws IOException {
	    URL url = new URL("https://www.ing.nl/api/locator/atms/");
	    try ( final Reader reader = stripProtection(new InputStreamReader(url.openStream())) ) {
	        final ObjectMapper objectMapper = new ObjectMapper();
	        final JsonNode jsonNode = objectMapper.readTree(reader);
	        List<Atm> atms = objectMapper.convertValue(jsonNode, new TypeReference<List<Atm>>(){});
	        Set<String> cities = new HashSet<>();
			
			for (Atm atm : atms) {
				cities.add(atm.getAddress().getCity().getCityName());
			}
			
			String[] cityArray = cities.toArray(new String[cities.size()]);
			
			for (int i = 1; i < cityArray.length; i++) {
				System.out.println("INSERT INTO City (city_id,city_name) VALUES("+i+",'"+ cityArray[i] +"');" );
			}
			
			for (int i = 1; i<atms.size();i++) {
				System.out.println("INSERT INTO GeoLocation(geo_id,lat,lng,address_id) VALUES ("+i+","+atms.get(i).getAddress().getGeoLocation().getLat()+","+atms.get(i).getAddress().getGeoLocation().getLng()+");");
				
				System.out.println("INSERT INTO Address (address_id,street,house_number,postal_code,city_id,geo_id) VALUES ("+i+",'"+atms.get(i).getAddress().getStreet()+"','"+atms.get(i).getAddress().getHousenumber()+"','"+atms.get(i).getAddress().getPostalcode()+"',(select city_id from City where city_name='"+atms.get(i).getAddress().getCity()+"'),"+i+");");
				
				System.out.println("INSERT INTO Atm(atm_id,distance,atm_type,address_id) VALUES	("+i+","+atms.get(i).getDistance()+",'"+atms.get(i).getType().name()+"',"+i+");");
			}
	        
	    }
	}
	

	public static Reader stripProtection(final Reader reader) throws IOException {
		final char[] buffer = new char[protectingCharacters.length];
		final Reader normalizedReader = reader.markSupported() ? reader : new BufferedReader(reader);
		normalizedReader.mark(protectingCharacters.length);
		normalizedReader.read(buffer, 0, protectingCharacters.length);
		if (!Arrays.equals(protectingCharacters, buffer)) {
			normalizedReader.reset();
		}
		return normalizedReader;
	}

	
}
