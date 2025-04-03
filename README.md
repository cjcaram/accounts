# Accounts Microservice

## Requirements

To build and run this microservice, ensure you have the following installed:

- **Java Development Kit (JDK):** Version 17 or higher.
- **Apache Maven:** Version 3.8.1 or higher.

## Building and Running the Microservice

Follow these steps to build and run the Accounts Microservice:

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/cjcaram/accounts.git
   ```

2. **Navigate to the Project Directory:**

   ```bash
   cd accounts
   ```

3. **Build the Project:**

   Use Maven to build the project:

   ```bash
   mvn clean package
   ```

   This command will compile the project, run tests, and package the application into a JAR file.

4. **Run the Application:**

   Instead of running the JAR file directly, use Maven to start the application:

   ```bash
   mvn spring-boot:run
   ```

   The application will start, and by default, it listens on port **8081**.

## Accessing the API Documentation

The Accounts Microservice uses Swagger for API documentation. Once the application is running, you can access the Swagger UI to explore and test the endpoints.

- **Swagger UI:** [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

Through the Swagger UI, you can view all available endpoints, their request and response structures, and interact with them directly from your browser.

## Endpoints Overview

For detailed information on each endpoint, including request parameters and response structures, refer to the Swagger UI.

---

