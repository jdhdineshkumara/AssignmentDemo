# Architecture, Design and Decisions

## 1. Module and Database Structure

The AssignmentDemo application is implemented as a single monolith spring boot application with two logically separated independent modules: `hr` and `utility`.

Each module owns its own database and persistence layer.

 - The HR module owns `hr_db` and accesses only the tables on `hr_db` database.
 - The Utility module owns `utility_db` and accesses only the tables on `utility_db` database.

A separate data source configurations available for each module. The HR module uses `HrDataSourceConfig` and the Utility module uses `UtilityDataSourceConfig`.
The datasource configured Separate Spring Data JPA repository packages, EntityManagerFactory instances, transaction managers, and DataSources for each module independently. 
This prevents cross-database access through the wrong repository configuration and keeps ownership explicit.

## 2. Module-to-Module Communication

We dont need to use REST/GRPC for communication between Utility and HR module because both modules are in same application and withing same JVM. 
We can use direct service invocations through service autowiring.
HR module communicates with Utility through the `DepartmentService` interface.

The connectivity of HR module to Utility module handle by `UtilityConnector` which centralize and isolate the communication and protocol.

In the future, if we separate utility module into a separate microservice, we can easily replace / restructure the UtilityConnector with REST / GRPC etc. this will protect/isolate the business logic modifications when utility module changed.


## 3. Error Handling

The API handles three required failure scenarios:

- Employee not found → HTTP 404 / `EMPLOYEE_NOT_FOUND`
- Department not found → HTTP 404 / `DEPARTMENT_NOT_FOUND`
- Utility/database unavailable → HTTP 404 / `UTILITY_UNAVAILABLE`

### And Internal Server Error for unexpected undefined, or unknown exceptions:
- Internal server error → HTTP 500 / `INTERNAL_SERVER_ERROR`

A global `@RestControllerAdvice` converts all the application exceptions into a common structured JSON responses format containing; 
 - timestamp - the timestamp, 
 - status  - HTTP status, 
 - error - error code, and 
 - message -  meaningful message.

