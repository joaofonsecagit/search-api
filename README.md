# Document Indexing and Search API

This project implements a RESTful service for indexing and searching documents. It allows users to submit a local
directory URI to create an in-memory index of files and supports querying the index with single words or sentences.

### Getting Started

##### Prerequisites

    Java 17
    Gradle 6.x

#### Building the Project

To build the project, run the following command in the project root directory:

```
./gradlew clean build
```

#### Running the Application

After successfully building the project, you can run it using:

```
./gradlew bootRun
```

The service will start and be available at http://localhost:8080.

### Usage

After starting the service, you can use the following endpoints:

    POST /index: Endpoint to submit a directory for indexing.
    GET /search: Endpoint to search the indexed documents.

Additionally, there's also the default actuator endpoints.

#### Development Time

    Time spent on the task: Approximately 3h hours on implementation plus 1h for design.

#### Caveats and Future Improvements

- Error Handling: Currently, the service has basic error handling. In the future, more robust error handling can be
  implemented to manage different exception scenarios more effectively.
- Performance: The current in-memory indexing might not be suitable for very large datasets. Proper persistence would be required. 
- Api Configuration: The api configuration should be done dynamically and not hardcoded. Example is the maximum number
  of results returned (currently 10) should be easily configurable in the application yml.
- Containerization of this app would make running it a trivial process.

#### Unimplemented Functionality

- Authentication: The API currently lacks authentication and authorization. For production, integrating OAuth or
  JWT-based authentication would enhance security.
- File Type Support: The service primarily supports plain text documents. Adding support for other file types (like
  PDFs, Word documents) is a planned future enhancement.
- Testing Enhancements: This codebase is missing integration and business tests.