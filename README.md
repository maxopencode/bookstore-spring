# Getting Started

The Bookstore application was developed using the following technologies: 
    
* Spring Boot (https://spring.io/projects/spring-boot) 
* Spring Framework (MVC, Data JPA, Security, Transactions, Testing) (https://spring.io/projects/spring-framework)
* JPA (Hibernate) (https://hibernate.org/orm/)
* Jackson (https://github.com/FasterXML/jackson)  
* SpringFox (https://springfox.github.io/springfox/)
* Liquibase (https://www.liquibase.org/)
* Gradle (https://gradle.org/)

## Prerequisites

* ### Java 11 or higher

    JDK installer can be downloaded from https://www.oracle.com/java/technologies/javase-jdk13-downloads.html

* ### MySQL database

    MySQL installer can be downloaded from https://dev.mysql.com/downloads/mysql/

    On Mac MySQL can also be installed using Homebrew:
    ```bash
        brew install mysql
        brew services start mysql
    ```

* ### Configure MySQL database

Before running or testing the application we need to configure our database.
* Please run commands from the terminal in `create-db.txt` to create application database and user. 
* Please run commands from the terminal in `create-test-db.txt` to create test database and user.

> Note: I use MySQL default port when connecting to the database. 
> You can change connection settings for the application in `src/main/resources/application.properties` 
> and for running tests in `src/test/resources/application-test.properties`. 

## Running the application

I use Gradle for building, testing and running the application.

* To build the app open the terminal and run the following from the project folder:
    ```bash
        ./gradlew clean build -x test
    ```
* To run tests run the following from the project folder:
    ```bash
        ./gradlew test
    ```
* To start the Spring Boot server run the following from the project folder:
    ```bash
        ./gradlew bootRun
    ```
### Testing REST API endpoints 

Once server starts you should be able to access Swagger UI in your browser from http://localhost:8080/api/swagger-ui/
From there you have options to test all the available API methods.
  
> Note: by default the application starts without OAuth2/JWT security enabled. 
Unfortunately, Swagger UI doesn't support Auth0 and Okta authentication currently.

### Testing REST API endpoints with OAuth2/JWT security enabled

To enable OAuth2 security: 

1. Open `src/main/resources/application.properties`
2. Uncomment the following line:
  ```
    #spring.profiles.active=oauth2
  ```
3. Restart the Spring boot server (Ctrl+C then ./gradlew bootRun)

Once server starts the REST API endpoints will be secured according to the rules defined in `java/com/instrument/bookstore/config/OAuth2WebSecurityConfig.java`

Like I mentioned above we can't use Swagger UI to test the secured endpoints. 
Instead, I provided **Postman** (https://www.postman.com/downloads/) collection export `postman/Instrument Bookstore.postman_collection.json` with all requests pre-configured.

> You may need to re-generate Bearer access tokens while testing requests with Postman if they are expired.
To do so please run `./generate-token.sh` script and then copy the access token hash from the generated output and paste it into the **Authorization** tab of a request in Postman.

> Note: I use Auth0 authorization server to generate JWT tokens according to the Client Credentials flow (https://auth0.com/docs/flows/client-credentials-flow) for simplicity.
