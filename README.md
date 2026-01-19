# JDBC Student Management Application

A simple Java JDBC application for managing student records in a MySQL database. This project demonstrates core concepts of database connectivity, CRUD operations, and design patterns in Java.

## Overview

This is a beginner-to-intermediate level project that showcases:
- **JDBC (Java Database Connectivity)** for database operations
- **DAO Pattern (Data Access Object)** for separation of concerns
- **Singleton Pattern** for database connection management
- **Builder Pattern** for dynamic SQL query construction

## Project Structure

```
jdbc_app2/
├── src/
│   ├── App.java                          # Main application entry point
│   ├── Dao/
│   │   ├── StudentDao.java              # Interface for student operations
│   │   └── StudentDaoImplement.java     # Implementation of StudentDao
│   ├── Helper/
│   │   ├── DBconfig.java                # Database configuration constants
│   │   ├── DBconnect.java               # Singleton database connection manager
│   │   └── QueryBuilder.java            # Dynamic SQL query builder
│   └── model/
│       └── Student.java                 # Student entity class
├── jdbc_app2.iml                        # IntelliJ IDEA project configuration
├── README.md                            # Project documentation
└── LICENSE                              # MIT License
```

## Features

- **Student Management**: Create, retrieve, update, and delete student records
- **Dynamic Query Building**: Generate SQL INSERT statements dynamically
- **Database Connection Pooling**: Singleton pattern for efficient connection management
- **DAO Pattern**: Clean separation between business logic and database access

## Database Setup

### Prerequisites
- MySQL Server (version 5.7 or higher)
- Java JDK 8 or higher
- MySQL JDBC Driver (mysql-connector-java)

### Database Configuration

1. **Create Environment File**
   
   Copy `.env.example` to `.env`:
   ```bash
   cp .env.example .env
   ```

2. **Update Credentials in `.env`**
   
   Edit the `.env` file with your database credentials:
   ```
   DB_URL=jdbc:mysql://localhost:3310/java_college_db
   DB_USER=root
   DB_PASSWORD=your_password
   DB_DRIVER=com.mysql.cj.jdbc.Driver
   ```

   **Important**: Never commit the `.env` file to GitHub. It's already in `.gitignore`.

### Create Database Schema

```sql
CREATE DATABASE java_college_db;

USE java_college_db;

CREATE TABLE Student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    marks INT
);

CREATE TABLE Product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    product_qty INT,
    category VARCHAR(50),
    price DECIMAL(10, 2),
    brand VARCHAR(50),
    discount INT
);
```

## Usage

### Basic Example

```java
import Helper.QueryBuilder;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        HashMap<String, String> studentData = new HashMap<>();
        studentData.put("name", "Awnish");
        studentData.put("age", "28");
        studentData.put("marks", "100");

        QueryBuilder qb = new QueryBuilder();
        System.out.println(qb.setTable("Student").insert(studentData));
    }
}
```

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/jdbc_app2.git
   cd jdbc_app2
   ```

2. **Setup Environment Variables**
   ```bash
   cp .env.example .env
   # Edit .env with your database credentials
   ```

3. **Add MySQL JDBC Driver**
   - Download from [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
   - Add to your project's classpath

4. **Configure Database**
   - Update credentials in `.env` file
   - Run the SQL schema creation commands (see Database Schema section)

5. **Compile and Run**
   ```bash
   javac src/App.java
   java App
   ```

## Classes Overview

### Student.java
Entity class representing a student with properties: `id`, `name`, `age`.

### StudentDao.java
Interface defining CRUD operations:
- `createStudent(String name, int age)`
- `getStudentById(int id)`
- `updateStudent(int id, String name, int age)`
- `deleteStudent(int id)`
- `getAllStudent()`

### DBconfig.java
Configuration class that securely loads database connection details from the `.env` file. Supports fallback to system environment variables and default values.

**Security**: Credentials are no longer hardcoded in the source code.

### DBconnect.java
Singleton class managing database connections. Ensures only one database connection instance exists throughout the application lifecycle.

### QueryBuilder.java
Builder pattern implementation for constructing dynamic SQL INSERT queries. Supports method chaining for fluent API.

## Dependencies

- **Java**: JDK 8+
- **MySQL**: 5.7+
- **MySQL JDBC Driver**: mysql-connector-java 8.0+

## License
See the [LICENSE](./LICENSE) file for full details.
## Author

Created as part of a Java learning course covering Spring Boot and Microservices.

## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for bugs and feature requests.

## Troubleshooting

### Connection Issues
- Verify MySQL server is running
- Check database URL, username, and password in `DBconfig.java`
- Ensure MySQL JDBC driver is in the classpath

### Driver Not Found
- Add MySQL JDBC connector to your project dependencies
- Ensure the correct driver class name: `com.mysql.cj.jdbc.Driver`

## Resources

- [JDBC Tutorial - Oracle](https://docs.oracle.com/javase/tutorial/jdbc/)
- [MySQL Connector/J Documentation](https://dev.mysql.com/doc/connector-j/en/)
- [DAO Pattern](https://www.baeldung.com/java-dao-pattern)
- [Singleton Pattern](https://refactoring.guru/design-patterns/singleton)

---

**Note**: This is a learning project. For production use, consider using established frameworks like Spring Boot with JPA/Hibernate.
