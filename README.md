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

## SAP Cloud Platform - Cloud Foundry Environment

1. To add a new client as JVM arguments add the following variable to user provided variables section:
 - key: JBP_CONFIG_JAVA_MAIN
 - value: { arguments: 'e8c3e87d-00fb-428b-888e-dcd6f9b99729 d8bbe44d-9fab-4725-8f1e-ced5e551bd56' } 