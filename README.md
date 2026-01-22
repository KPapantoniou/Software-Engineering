# Diploma Projects Management App

## Authors

- **Konstantis Papantoniou** (ID: 4769)
- **Vasileios Somakos** (ID: 4806)

## Overview

This application manages diploma projects for students and professors, allowing them to handle project assignments, applications, and grading.

## Prerequisites

- Java Development Kit (JDK) installed
- Apache Maven installed
- MySQL Server installed

## Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/diploma-project-management-app.git
   cd diploma-project-management-app

2. **Database Setup**:

- Create a MySQL database named diploma_project_db.
- Import the database schema using the provided SQL script (schema.sql) 
3. **Configure Database Connection**:

- Open src/main/resources/application.properties.
- Modify the database configuration
  ```
   spring.datasource.url=jdbc:mysql://localhost:3306/diploma_project_db
   spring.datasource.username=root
   spring.datasource.password=password

4. **Run the Application**:

- Navigate to the root directory of the project.
- Build the application using Maven and run.

5. **Access the Application**:

Open a web browser and go to http://localhost:8080.

