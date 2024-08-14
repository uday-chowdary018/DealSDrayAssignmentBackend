Employee Management Backend->This project is a backend application for managing employee records. It provides RESTful APIs for user authentication, employee creation, updating, deletion, and retrieval. The application is built using Java Spring Boot and MySQL.
Technologies Used:
1)java
2)Spring boot
3)Mysql
Project Structure
src/main/java/com/example/dealsdraybackend:
config: Configuration files for security, CORS, etc.
controller: REST controllers for handling HTTP requests.
dto: Data Transfer Objects for transferring data between layers.
entity: Entity classes representing database tables.
repository: Interfaces for data access and persistence.
service: Business logic and service layer.
exception: Custom exceptions for error handling.
API Endpoint:
User Authentication
POST /api/auth/login - Login to the system.
POST /api/auth/register - Register a new user.
Employee Management:
POST /api/employees - Create a new employee.
PUT /api/employees/{id} - Update an existing employee.
DELETE /api/employees/{id} - Delete an employee by ID.
GET /api/employees - Retrieve a list of employees.
GET /api/employees/{id} - Get details of a specific employee by ID.
Database Schema
Tables
Login:
id (Primary Key)
username
password
employees:
id (Primary Key)
name
email
phone
designation (e.g., HR, Manager, Sales)
gender
course (stored as a JSON array)
image (Base64 encoded string)
createdate (Timestamp)
status (active/inactive)
Installation and Setup
Clone the repository:
git clone https://github.com/yourusername/employee-management-backend.git
cd employee-management-backend
Configure MySQL Database:

Update application.properties or application.yml with your MySQL database credentials.
Run the application:
run the project 

