# microservices-2022

# Service 1 - employee-management

## API's

Add Employee - POST - http://localhost:8111/employees/add

Get Employee by Id - GET - http://localhost:8111/employees/122222

Get all Employees - GET - http://localhost:8111/employees

Update Employee - PUT - http://localhost:8111/employees/update

Delete Employee - DELETE - http://localhost:8111/employees/122222


## DB used

H2 - In-memory DB

## Swagger UI (No Configuration is needed for Swagger 3) 
## Dependencies to be added :

### 1. To enable Swagger
org.springdoc springdoc-openapi-ui 1.6.4

### 2. To enable webflux for Swagger
org.springdoc springdoc-openapi-webflux-ui 1.6.4

## Swagger link
http://localhost:8111/swagger-ui/index.html

###### Pending changes
* Add spring-gateway microservice
* Add Eureka Server
* Add Config Server
* Add AOP logging
* Add a login service with OAuth token validation
* Enable logs
