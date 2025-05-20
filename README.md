# LabSeq

## Quick Start

The application is containerized with Docker for easy deployment and consistent environments.

### Prerequisites

- [Docker](https://www.docker.com/get-started/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Running the Application

1. Clone the repository


2. Start the application
   ```bash
   docker-compose up --build
   ```

3. The application will now be running at:
   - Frontend: [http://localhost:4200/labseq](http://localhost:4200/labseq)
   - API: [http://localhost:8080/labseq/{n}](http://localhost:8080/labseq/10)

## API Documentation

Comprehensive API documentation is available via Swagger UI:

- [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)
