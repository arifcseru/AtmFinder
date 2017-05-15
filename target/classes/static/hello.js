app = angular.module('mapApp', []);

app.controller('cityController', function($scope, $http) {

	$http.get('/city/all/').then(function(response) {
		$scope.cities = response.data;
	});

	$http.get('/atm/all').then(function(response) {
		$scope.atms = response.data;
		console.log($scope.atms);
	});

	$scope.myFunc = function(menu) {
		$http({
			method : "GET",
			url : "/city/all/",
		}).success(function(data) {
			$scope.cities = data;
		});

		var cityId = document.getElementById('menu');
		var netherlands = {
			lat : 52.1326,
			lng : 5.2913
		};
		var mapOptions = {
			zoom : 8,
			center : netherlands,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}

		$scope.map = new google.maps.Map(document.getElementById('map'),
				mapOptions);
		$scope.markers = [];
		$scope.cities = [];
		var infoWindow = new google.maps.InfoWindow();

		$http.get('/atm?name=' + menu.cityName).success(function(data) {
			$scope.atms = data;
			angular.forEach($scope.atms, function(atm) {
				createMarker(atm);
			});

		});

		var createMarker = function(atm) {

			var marker = new google.maps.Marker({
				map : $scope.map,
				position : new google.maps.LatLng(atm.address.geoLocation.lat,
						atm.address.geoLocation.lng),
				title : atm.address.street + " " + atm.address.housenumber
						+ " " + atm.address.postalcode

			});
			marker.content = '<div class="infoWindowContent">' + "Atm_"
					+ atm.id + '</div>';

			google.maps.event.addListener(marker, 'click', function() {
				infoWindow.setContent('<h2>' + marker.title + '</h2>'
						+ marker.content);
				infoWindow.open($scope.map, marker);
			});

			$scope.markers.push(marker);
		};

	}

});
