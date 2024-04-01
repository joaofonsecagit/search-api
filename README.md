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