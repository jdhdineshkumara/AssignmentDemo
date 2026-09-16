# Assignment Demo

A modular monolith implementation using Spring Boot 4.1, Java 21,
Spring Data JPA and MySQL.

## Architecture

The application is monolithic application and contains two logical modules:

- HR Module
- Utility Module

Each module owns an independent database:

- `hr_db`
- `utility_db`

### HR Module

Owns:

- Employee entity
- Employee repository
- HR datasource
- HR database

### Utility Module

Owns:

- Department entity
- Department repository
- Utility datasource
- Utility database

The HR module doesn't have direct access to Utility repository or database directly.

We dont need to use REST/GRPC for communication between Utility and HR module because both modules are in same application and withing same JVM. We can use direct service invocations through service autowiring.

HR module communicates with Utility through the `DepartmentService` interface.

The connectivity of HR module to Utility module handle by Utility connector which centralize and isolate the communication and protocol.

In future, if we separate utility module into a separate micro service, we can easily replace/restructure the UtilityConnector with REST/GRPC etc.
this will protect/isolate the business logic modifications when utility module changed.



## Technology

- Java 21
- Spring Boot 4.x
- Spring Data JPA
- MySQL
- Maven

## Database Setup and Execute

 - `resources/scripts/hr_db.sql`
 - `resources/scripts/utility_db.sql`

## Data source configuration

Configure the data sources in `application.properties` file with correct database connection details.

`# hr_db data source configuration`
 - `app.datasource.hr.url=jdbc:mysql://localhost:3306/hr_db`
 - `app.datasource.hr.username=dummyuser`
 - `app.datasource.hr.password=password`
 - `app.datasource.hr.driver-class-name=com.mysql.cj.jdbc.Driver`
#
`# utility_db data source configuration`
 - `app.datasource.utility.url=jdbc:mysql://localhost:3306/utility_db`
 - `app.datasource.utility.username=dummyuser`
 - `app.datasource.utility.password=password`
 - `app.datasource.utility.driver-class-name=com.mysql.cj.jdbc.Driver`

#
Change the Server Port if necessary

`server.port=8080`

Run the project as spring boot application

Main class
`AssignmentDemoApplication`


# Success Response
curl http://localhost:8080/api/employees/103

```
{
    "employeeId": 103,
    "name": "Ravi Perera",
    "departmentName": "Finance"
}
```

# Employer Not Found
curl http://localhost:8080/api/employees/3455

```
{
    "timestamp": "2026-09-16T09:07:41.797971200Z",
    "status": 404,
    "error": "EMPLOYEE_NOT_FOUND",
    "message": "Employee not found with the ID 3455"
}
```
# Department Not Found
curl http://localhost:8080/api/employees/104

```
{
    "timestamp": "2026-09-16T09:09:41.876365300Z",
    "status": 404,
    "error": "DEPARTMENT_NOT_FOUND",
    "message": "Department Not found"
}
```
