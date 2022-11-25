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
```
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
```
### Adding discovery service
1. @EnableEurekaServer to main application file along with dependency
2. Changes in application.yaml file
application.yaml
```
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
```

### Changes in emp-service to register with Eureka and API Gateway

1. @EnableEurekaClient annotaion to main class along with dependency
2. Changes in application.yaml file
application.yaml
```
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
    
```

## Service 2: api-gateway
port : 8711

## API's Calling employee-management service through gateway

Add Employee - POST - http://localhost:8711/employees/add

Get Employee by Id - GET - http://localhost:8711/employees/122222

Get all Employees - GET - http://localhost:8711/employees

Update Employee - PUT - http://localhost:8711/employees/update

Delete Employee - DELETE - http://localhost:8711/employees/122222


## Service 3: service-discovery
port : 8761
url : http://localhost:8761/

## Service 4 : config-service
### Changes to add config service:
1. Create a repo in github to store property files
2. Create a springboot project with dependencies
```
<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

#### This is needed for using bootstrap.property file configuration
```
<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```

3. bootstrap.properties changes
```
#Server port
server.port = 8888
#Git repo location
spring.cloud.config.server.git.uri=https://github.com/vkwjope/microservices-2022-config-repo
spring.cloud.config.server.git.cloneOnStart=false
#Disable security of the Management endpoint
management.security.enabled=false
spring.cloud.config.server.bootstrap=true
management.endpoints.web.exposure.include=*

```

## Changes in employee-management service to use config server 
1. @RefreshScope annotation for controller class
2. Dependencies to be added
```
a. 
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-config</artifactId>
</dependency>


b.
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```

2. Bootstrap.properties changes

```
spring.profiles.active=default
#connect to config server
spring.cloud.config.uri=http://localhost:8888
management.security.enabled=false
management.endpoints.web.exposure.include=*
server.port=8111

```

## Enabling AOP logging for employee-management service

#### Steps:
1. Add @EnableAspectJAutoProxy annotation to MainApplication file
2. Added a new file for AOP log configuration AopLogger.java
3. No dependency to be added
4. Add below properties in the bootstrap.proprties file

```
logging.file.name=logs/emp/employye-mgmt.log
logging.level.com.example.employeemanagement=TRACE
logging.pattern.file: %d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [${spring.application.name}, %X{X-B3-TraceId},%X{X-B3-SpanId},%X{X-Span-Export}] [%thread] %logger : %msg%n
logging.logback.rollingpolicy.max-file-size=10MB
```

###### Pending changes
* ~~Add spring-gateway microservice~~
* ~~Add Eureka Server~~
* ~~Add Config Server~~
* ~~Add AOP logging~~
* Add a login service with OAuth token validation



#### References:
##### Swagger config - https://www.baeldung.com/spring-rest-openapi-documentation
