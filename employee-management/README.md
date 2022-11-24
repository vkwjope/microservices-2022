API's

1. Add Employee         - POST    - http://localhost:8111/employees/add
2. Get Employee by Id   - GET     - http://localhost:8111/employees/122222
3. Get all Employees    - GET     - http://localhost:8111/employees
4. Update Employee      - PUT     - http://localhost:8111/employees/update
5. Delete Employee      - DELETE  - http://localhost:8111/employees/122222


Swagger UI (No Configuration is needed for Swagger 3)
Dependencies to be added :

To enable Swagger 

<dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-ui</artifactId>
	<version>1.6.4</version>
</dependency>


To enable webflux for Swagger


<dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-webflux-ui</artifactId>
	<version>1.6.4</version>
</dependency>
    
http://localhost:8111/swagger-ui/index.html
