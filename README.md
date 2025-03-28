# MotoPay - Vehicle Leasing Backend System

MotoPay is a robust back-end system developed with **Java Spring Boot** designed to streamline the vehicle leasing process. The system offers a seamless solution for managing leasing operations, including vehicle management, lease contracts, and customer data. It is built to support REST APIs for integration with front-end systems or mobile applications.

## Features:
- **Vehicle Management**: Add, update, and manage vehicle details for leasing.
- **Customer Management**: Track customer information, leasing history, and payments.
- **Lease Contracts**: Create, manage, and view lease contracts.
- **Payment Tracking**: Record and manage payments, including outstanding balances.
- **REST APIs**: Exposes several RESTful endpoints for seamless communication with front-end systems.

## Technologies:
- **Backend**: 
  - Java 11+
  - Spring Boot
  - Spring Data JPA for database access
  -  MySQL (for production)
  - Spring Security for authentication and authorization
- **REST APIs**: 
  - Exposed using Spring Boot's `@RestController` and `@RequestMapping` annotations.

## Setup and Installation:

### Prerequisites:
- **Java 8+** for running the backend.
- **Maven** for dependency management and building the application.
- **MySQL** (for production) for data storage.
