# ATM FINDER PROJECT

This project aims to show all atms in Netherlands on Google Map. There is also a filter feature by city. 

## Getting Started
This project is developed by using Spring-Boot so it has tomcat container already and no need to deploy. 

###Infrastructure

##### JAVA 8
##### Maven 
##### Spring-Boot and other dependencies(data-rest,data-jpa, security, test)
##### H2 in-memory db
##### Angular.js
##### Bootstrap

### Prerequisites
The data is used in this application consumed a rest service and create insert queries. `RestConsume.java` class represents client codes of that rest service and parse the data and prepare insert queries. Given rest service has a protection against called ** JSON highjacking exploit **, it starts ** )]}', ** so first needed to remove invalid characters then convert json data to java object. After prepared insert queries, they must be in data.sql because h2 in-memory db is selected for this project and Spring-Boot recognise **data.sql** and **schema.sql** files and run them when server starts up.


## Installing

Just clone the project and run `Application.java` class.

### JPA Queries

There are many ways to create queries in Spring world. I implemented repository interface and explained how to configure it in our application. It's so simple, let's dive into :)

#### What is Spring repository?

The goal of Spring Data repository abstraction is to significantly reduce the amount of boilerplate code required to implement data access layers for various persistence stores.

#### How we use Spring repository?

The ex below for repository service:

 ```
    public interface RepositoryName extends Repository<T, ID> { … }
 ```
 
Type should be our Entity and Id is an entity id. There are additional Repository interface that we can
extend : CrudRepository,PagingAndSortingRepository. Spring also provides persistence technology-specific abstractions like JpaRepository or MongoRepository.

These repositories have some useful methods like findAll(),T findOne(ID primaryKey),count(),void delete(T entity),etc...If we want to get all values from an entity we don't need to write additional code just call **findAll()** method.

##### Creating Database Queries With Query Methods 

*1
If we need to create specific query like find city from city name we can just write a method in interface which named findByCityName
   ```
   City findByCityName(String cityname);
   ```
This query generation method gives us to create queries by using method name strategy. There are some rules to create these queries : 
 * Query method name should start and end with these words: find…By, read…By, query…By, count…By, and get…By.
 * If we want to limit the number of returned query results, we can add first of top keyword like findTopBy, findTop1By, findFirstBy
 * For unique results : findTitleDistinctBy or findDistinctTitleBy
 
The result set  can be entry, Optional<Entry>, List<Entry>,Stream<Entry> . This query generation strategy is used to when we need simple queries. For the complex queries it might seem ugly and long besides the query keywords must provide our needs. The disadvantage of this method it does not have dynamic query support.

*2
We can use @Query annotation to write custom queries :

```
@Query("SELECT * FROM City c where c.city_name = :name") 
City findByCityName(@Param("name") String name);

```
We can also  use @Async annotation to execute queries asynchronously.

In addition, there is one way to set parameters to query other than @Query annotation

```
@Query("SELECT t FROM Address t where t.street = ?1 AND t.house_number = ?2")
    public Optional<Address> findByStreetAndHouseNumber(String street, String houseNumber);
    
```
Or we can write query in Entity class with @NamedQuery annotation :
```
@Entity
@NamedQuery(name = "City.findByCityName",
  query = "select c from City c where c.city_name = ?1")
public class City {

}
```

```
public interface CityRepository extends JpaRepository<City, Long> {
	City findByCityName(String cityName);
}

```
The next two approach should be considered more convenient than static one if we work on real-life applications and requirements might be more complex.

[Spring Boot Jpa doc](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#dependencies.spring-boot)
   
##### Creating Database Queries With the JPA Criteria API


##### Creating Database Queries With Querydsl

Querydsl is a framework which enables to build dynamic and complex queries but we need to add it' dependency in pom.xml

### Securing the Rest Service 

Spring security provides to secure application access.   
We should apply at least the most important best practices for our application that we can say it's done.
Authentication, authorization, CSRF protection.

There are numerous approaches that could be used here, with numerous tradeoffs.
The rest api we built on can be used by all manner of HTML, native mobile, desktop clients, etc. The security approach we picked up should cover all.
You can check here for more details :
[Rest Cheat Sheet](https://www.owasp.org/index.php/REST_Security_Cheat_Sheet)

Active Directory, LDAP, pam, CAAS, Spring Social(OAuth based services like Facebook,Twitter,Github) for authentication providers.

HTTPS is important to prevent Man-in-the-Middle Attacks. Spring boot has easy steps to apply this protection.
First create this resource file :
```
security/src/main/resources/application-https.properties
```
Second add these properties :

```
server.port = 8443
server.ssl.key-store = classpath:tomcat.keystore
server.ssl.key-store-password = password
server.ssl.key-password = password
```

To setup Spring security we have to add spring security dependency to maven

```
<dependencies>
    ...
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
    ...
</dependencies>
```

`WebSecurityConfig.java`  class is annotated with `@EnableWebSecurity` and `@Configuration`
to enable Spring security supports and provide MVC integration.`configure(HttpSecurity)`  method defines
which URL paths are configured to not require any authentication. We can also make secure our application from CSRF attacks by adding `.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())`

` httpBasic()` provides basic authentication

`configureGlobal(AuthenticationManagerBuilder)` method sets up an in-memory user store with a single user.It can be configured to login by many users. 

Spring Security provides many implementations of authentication contract that adapt existing identity providers, like Active Directory, LDAP, pam, CAAS, etc. Spring Social even provides a nice integration that delegates to different OAuth-based services like Facebook, Twitter, etc., for authentication.

### Exception Handling in Spring 

There are three approaches to handle exceptions : Controller Based Exception Handling, Global Exception Handling, Exception Based Exception Handling

I believe to present http status codes, message, exception code when exception occurs in REST API.
There are many(nearly 70) http status code but we shouldn't use all of them just some of them that covers basic status like : everything works, the application did something wrong, the api did something wrong.

I prefer to implement global exception mechanism approach to manage exceptions from one source. But all approaches deal with separation of concerns very well. 

```
@RestControllerAdvice
public class GlobalExceptionHandler {

}
```
** @RestControllerAdvice ** annotation provides global exception mechanism to handle exceptions and reduce
boilerplate codes in Spring(after 4.3 version) by removing  ** @ResponseBody ** annotation on each method in controller advice. It says in the documentation : annotation that is itself annotated with ** @ControllerAdvice ** and ** @ResponseBody ** 

For handling 404 errors, we need to add `@EnableWebMvc` annotation in Spring Boot Application class and 
`spring.mvc.throw-exception-if-no-handler-found=true`in application.properties

## Running the tests

To test our application we add `spring-boot-starter-test` and it includes some test libraries like : JUnit, Spring Test, AssertJ, Hamcrest, Mockito, JSONAssert, JsonPath
`@SpringBootTest` annotation is used for regular tests, `@RunWith(SpringRunner.class)` tells JUnit to run using Spring’s testing support. `@DataJpaTest` provides to test jpa part. It configures an in-memory database, configure hibernate, spring data, datasource automatically and performs `@EntityScan`