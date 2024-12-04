# Green Shadow Backend

## Overview
Green Shadow is a backend solution designed for managing agricultural equipment, fields, and staff. It is built using **Spring Boot** and provides a RESTful API for CRUD operations related to equipment, fields, staff, and more. The system supports features such as managing equipment availability, field details, and staff roles in the agricultural sector.

---

## Features

### Equipment Management:
- Create, read, update, and delete equipment.
- Track equipment status and assign them to fields and staff.

### Field Management:
- Manage fields with details like field codes, names, locations, and associated staff.
- Support for mapping equipment and staff to fields.

### Staff Management:
- Manage staff details such as name, gender, role, contact information, and their association with fields and equipment.

### Role-Based Access:
- Support for roles such as `Manager`, `Scientist`, and `Other`.

### Transactional Integrity:
- Ensures consistency in database operations, particularly when saving or updating fields, equipment, and staff.

### Custom Exception Handling:
- Provides detailed error messages for failed operations, making it easy to troubleshoot issues.

---

## Tech Stack
- **Backend Framework**: Spring Boot
- **Database**: MySQL
- **Persistence**: JPA (Jakarta Persistence API)
- **Security**: JWT-based Authentication and Authorization
- **API Documentation**: Postman
- **Build Tool**: Maven
- **Version Control**: Git (GitHub for code hosting)

---

## API Endpoints

### Authentication
- **POST** `/api/v1/auth/login`
  - Login endpoint to get JWT token.
  - Request body: `{ "username": "your-username", "password": "your-password" }`
  - Response: JWT token for authorized access.

---

### Staff Management
- **GET** `/api/v1/staff`
  - Get all staff members.
  - Response: List of all staff.

- **POST** `/api/v1/staff`
  - Add new staff.
  - Request body: 
    ```json
    { 
      "staffCode": "SC001", 
      "firstName": "John", 
      "lastName": "Doe", 
      "gender": "MALE", 
      "role": "MANAGER", 
      "contactNo": "123456789", 
      "email": "john.doe@example.com" 
    }
    ```

- **PUT** `/api/v1/staff/{staffCode}`
  - Update staff information by staff code.
  - Request body:
    ```json
    { 
      "firstName": "John", 
      "lastName": "Doe", 
      "contactNo": "987654321" 
    }
    ```

- **DELETE** `/api/v1/staff/{staffCode}`
  - Delete staff by staff code.

---

### Equipment Management
- **GET** `/api/v1/equipment`
  - Get all equipment.
  - Response: List of all equipment.

- **POST** `/api/v1/equipment`
  - Add new equipment.
  - Request body:
    ```json
    { 
      "name": "Tractor", 
      "type": "Vehicle", 
      "status": "Available" 
    }
    ```

- **PUT** `/api/v1/equipment/{equipmentId}`
  - Update equipment details by equipment ID.

- **DELETE** `/api/v1/equipment/{equipmentId}`
  - Delete equipment by ID.

---

### Field Management
- **GET** `/api/v1/field`
  - Get all fields.
  - Response: List of all fields.

- **POST** `/api/v1/field`
  - Add new field.
  - Request body:
    ```json
    { 
      "fieldCode": "F001", 
      "fieldName": "Farm A", 
      "location": "North Zone", 
      "size": "50 Acres" 
    }
    ```

- **PUT** `/api/v1/field/{fieldCode}`
  - Update field details by field code.

- **DELETE** `/api/v1/field/{fieldCode}`
  - Delete field by field code.

---

## Installation

To run the backend locally, follow these steps:

### Prerequisites
- **Java** 21 or above
- **MySQL** 8.0 or above
- **Maven** for building the project
- **Postman** or any API client for testing endpoints

---

1. Clone the Backend repository:
   ```bash
   git clone https://github.com/RandikaEdirisooriya/greenshadow-backend.git

1. Clone the Frontend repository:
   ```bash
   https://github.com/RandikaEdirisooriya/Green_Shadow_Frontend
