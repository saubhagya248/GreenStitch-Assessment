**To Run the Application in IDE**
Below are mentioned the basic requirements to run the application:
    * Java version: 17
    * Intellij Idea/ or any IDE with Java 17
    * Maven
    * Postman (Optional)

Step 1. import the project from the repository into your IDE
Step 2. After importing let the maven synchronise the project
Step 3. Navigate to the main class and click on run
Step 4. You will be able to see the Spring Boot app running on your local server at port 8080

**About the application**
This Spring Boot app is a login-signup service which uses JWT Authentication.
This application uses Spring boot, H2 Database, Spring Security, JWT
JSON Web Tokens (JWT) are the JSON Object which is used to securely transfer information over the web(between two parties).

**Purpose**
The main purpose of this application is to provide client the JWT token when client raises a sign up request or a login request.
The JWT token can be used for authentication, authorization and information exchange
The token provided can be used by the client to get the authorization as the client will send the token along with the request.

**Dummy Entries**
The DummyInitializer class will initialize 2 entries whenever the application will be started.

**Endpoints**
There are two endpoints /login and /signup 
Both the endpoints are uses HttpMethod.POST


**Request Objects**

```
   public class UserRequestDTO {
       String name;
   
       String username;
   
       String password;
   
       Role role;
   }
```
