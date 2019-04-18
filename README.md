# List Back-End

## Prerequisites 

+ Install java 11 from https://www.oracle.com
+ Install PostgreSQL from https://www.postgresql.org/
+ Install Maven from https://maven.apache.org/

## Setup local

+ Configure PostgreSQL database with the configuration found in application.yml file.

## Run on Local Environment

+ Run the following command in the root folder: mvn spring-boot:run -Drun.profiles=local

## Flow 

+ Register a new client by giving the client UUID name and UUID secret as first and second run param. (Alternative on local profile: path = /develop/client, method = get, responseBody= ClientDTO)
+ Register a new user. path=/register, method = post requestBody = UserRegistrationDTO
+ Activate the user using the UUID sent on email. path = /register method = get, requestParams = code:String
+ Get JWT. path = /login, method = post, requestBody = UserLoginRequestDTO, responseBody = TokenDTO
+ Use the JWT for further requests.
+ If the JWT expires, use the provided refresh token to get a new one. path = /refresh, method = post, requestParams = refreshToken