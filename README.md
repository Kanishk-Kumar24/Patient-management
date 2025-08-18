# Patient Management System

This repository contains a patient management system composed of two Spring Boot microservices: `patient-service` and `billing-service`.

## Services

### 1. Patient Service

The `patient-service` is responsible for managing patient information. It provides a RESTful API for interacting with patient data and uses Spring Data JPA for persistence. It also interacts with the `billing-service` via gRPC.

**Key Technologies:**
- Spring Boot
- Spring Data JPA
- H2 Database (for local development/testing, can be configured for PostgreSQL)
- gRPC
- Lombok
- Springdoc OpenAPI (Swagger UI)

**API Endpoints:**
- REST API on port `4000`

**Database:**
- Configured to use an in-memory H2 database by default for local development. The `data.sql` file initializes the `patient` table with sample data.
- Can be configured to use PostgreSQL.

### 2. Billing Service

The `billing-service` is a gRPC service responsible for handling billing-related operations. It exposes a gRPC API that can be consumed by other services, such as the `patient-service`.

**Key Technologies:**
- Spring Boot
- gRPC

**API Endpoints:**
- gRPC service on port `9001`
- Web service on port `4001`

## Project Structure

```
patient-management/
├── api-requests/             # HTTP request files for patient-service
├── billing-service/          # Spring Boot application for billing operations
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/         # Java source code
│   │   │   ├── proto/        # Protocol Buffer definitions (billing-service.proto)
│   │   │   └── resources/    # Application properties (application.properties)
│   ├── pom.xml               # Maven build file
├── grpc-requests/            # gRPC request files
├── patient-service/          # Spring Boot application for patient management
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/         # Java source code
│   │   │   ├── proto/        # Protocol Buffer definitions (billing-service.proto)
│   │   │   └── resources/    # Application properties (application.properties, data.sql)
│   ├── pom.xml               # Maven build file
└── README.md                 # This README file
```

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven
- Docker (optional, for containerized deployment)

### Building the Services

Navigate to each service directory (`billing-service` and `patient-service`) and run the following Maven command:

```bash
mvn clean install
```

This will compile the code, run tests, and package the applications into JAR files.

### Running the Services

#### Option 1: Run Natively

After building, you can run each service from its respective directory:

```bash
java -jar target/<service-name>-0.0.1-SNAPSHOT.jar
```

Replace `<service-name>` with `billing-service` or `patient-service`.

Ensure the `billing-service` is running before starting the `patient-service` if the `patient-service` depends on it.

#### Option 2: Run with Docker (if Dockerfiles are provided)

If Dockerfiles are available in each service directory, you can build and run Docker images:

```bash
# In billing-service directory
docker build -t billing-service .
docker run -p 4001:4001 -p 9001:9001 billing-service

# In patient-service directory
docker build -t patient-service .
docker run -p 4000:4000 patient-service
```

## API Documentation

For the `patient-service`, API documentation (Swagger UI) is available at `http://localhost:4000/swagger-ui.html` when the service is running.

## Contributing

Feel free to contribute to this project by submitting issues or pull requests.

## License

(Add your license information here)
