# Spring Security with the JWT Authentication
Implementing the Spring boot security with thw JWT Authentication
In this we are using JWT package to generate the token for the authenticated user and use it for consuming other API.
We are not using any form based authentication here.

## Steps to test the application
    1. Start the application (Make sure you have running MySQL Server and username/password is correct)
    2. Application will run on default port 8080.
    3. Create the user using postman by below details -
        Creating Admin User :
        Endpoint - https://localhost:8080/auth/signup
        Method - POST
        Payload - {
            "firstName" : "Admin",
            "lastName" : "Admin",
            "username" : "admin",
            "password" : "admin",
            "authorities": [
                {
                    "authority": "ROLE_USER"
                },
                {
                    "authority": "ROLE_USER"
                }
            ]
        }

        Creating Normal User :
        Endpoint - https://localhost:8080/auth/signup
        Method - POST
        Payload - {
            "firstName" : "User",
            "lastName" : "User",
            "username" : "user",
            "password" : "password",
            "authorities": [
                {
                    "authority": "ROLE_USER"
                }
            ]
        }
        
    4. Generate the JWT Token using login API - 
        Endpoint - https://localhost:8080/auth/login
        Method - POST
        Payload - {
          "username" : "admin",
          "password" : "password"
        }

    5. Checking if generated JWT Token is correct
        Endpoint - https://localhost:8080/home
        Method - GET
        Headers - 
            Authorization : Bearer <JWT Token>
