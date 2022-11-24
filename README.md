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

http://localhost:8711/swagger-ui/index.html {API Gateway}

### Adding api-gateway service

1. @EnableEurekaClient annotaion to main class along with dependency
2. Changes in application.yaml file
application.yaml
---
spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      routes:
      - id: employee-management
        predicates:
        - Path=/**
        uri: lb://employee-management
server:
  port: 8711
eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
  instance:
    hostname: localhost
    
---
### Adding discovery service
1. @EnableEurekaServer to main application file along with dependency
2. Changes in application.yaml file
application.yaml
---
spring:
  application:
    name: discovery-service
eureka:
  client:
    fetch-registry: false
    register-with-eureka: false
  instance:
    hostname: localhost
server:
  port: 8761
  
---

### Changes in emp-service to register with Eureka and API Gateway

1. @EnableEurekaClient annotaion to main class along with dependency
2. Changes in application.yaml file
application.yaml
---
server:
  port: 8111
spring:
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
  application:
    name: employee-management
eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
  instance:
    hostname: localhost
    
---

###### Pending changes
* Add spring-gateway microservice
* Add Eureka Server
* Add Config Server
* Add AOP logging
* Add a login service with OAuth token validation
* Enable logs


#### References:
##### Swagger config - https://www.baeldung.com/spring-rest-openapi-documentation
