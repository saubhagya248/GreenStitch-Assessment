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

**Setting up the postman**
Download and install the postman on your computer.
* Run the project 
* Create new Http Request in postman
* Set the method as POST
* In the uri bar enter https://localhost:8080 (choose this only if you are running the app on local server)
* Now add /login or /signup (Depending on which api you want to test)
* Go to the Body tab and select raw 
* From the leftmost dropdown select JSON
* Now in the editor below place the JSON object in the format mentioned below and send the request you will be able to see the response status as OK and JWT token 


**Request Objects**

The below object is for /signup endpoint, you are required to send a POST request with below mentioned fields i.e. name, username, password, role as a JSON Object

*Note: the role field has two values ADMIN, USER as it is defined as Enum in the project you can add more by modifying the Enums.Role file *

```
   [
       "name": "examplename",
   
       "username": "example@1",
   
       "password": "password@123",
   
       "role": "ADMIN"
   ]
```

In response to the above request you will receive a JWT token 

When sending a request to /login endpoint you are required to choose HttpMethod.POST and send a JSON object in below mentioned format:

```
    [
        "username": "example",
        "password": "example@123"
    ]
```

You will get a JWT Token for the above request

**Using the token**
Inside Postman
* Go to the authorization tab 
* From the type select Bearer Token 
* Enter the token you got in the text area and send the request 

