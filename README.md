# User Manager

## Overview
**User Manager** is a simple RESTful API built using Spring Boot for managing users. It includes the ability to create, read, update, and delete user data, with API documentation provided via Swagger. 🚀

## Live Demo
You can access the live version of the User Manager API here:  
👉 [Live API Documentation](https://user-manager-latest.onrender.com/swagger-ui/index.html) 👈

## Features
- ✏️ **Create, Update, Delete, and Retrieve** users
- 🔗 **RESTful API** endpoints with CRUD operations
- 🗄️ Integrated with a database (**PostgreSQL**)
- 🔒 Basic **Spring Security** for securing endpoints
- 📜 API documentation using **Swagger**

## Technologies Used
- ☕ **Java**
- 🚀 **Spring Boot**
- 🔐 **Spring Security**
- 📊 **Spring Data JPA**
- 🐘 **PostgreSQL**
- 📖 **Swagger** for API Documentation
- 🧪 **JUnit** and **Mockito** for unit testing
- 🔧 **Maven** for dependency management

## Prerequisites
Before you can run this project, ensure you have the following installed:
- **Java Development Kit (JDK):** Version 11 or higher. ☑️
- **Maven:** For building the project. ☑️
- **PostgreSQL:** Ensure you have a running database instance. ☑️

## Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/Nich0las2004/user-manager.git
   cd user-manager
   ```
2. **Configure database connection:**
   Update the **application.properties** file with your database connection details. 📝
3. **Build the project:**
    ```bash
    ./mvnw clean package
    ```
4. **Run the application:**
    ```bash
    java -jar target/user-manager.jar
    ```

## API Documentation
After running the application, you can access the API documentation at:
    ```
    http://localhost:8080/swagger-ui/index.html
    ```

## Usage
- Creating a User:
    - **POST /users/create** ✨
    - Creates a new user.
- Retrieving all Users:
    - **GET /users** 📥
    - Retrieves a list of all users.
- Finding a User:
    - **GET /users/find{id}** 📥
    - Retrieves details of a specific user by ID.
- Updating a User:
    - **PUT /users/update/{id}** ✏️
    - Updates the information of an existing user by ID.
- Deleting a User:
    - **DELETE /users/delete/{id}** ❌
    - Deletes a user using ID.

## Running Tests
To run the unit tests, use:
    ```
    ./mvnw test
    ```