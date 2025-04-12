# Employee Management System

An application for managing employees, their data, and assigning them to different groups. Built using Spring Boot, JavaFX, and an in-memory database.

## Features:

- **Employee Management**: Add, remove, and edit employee details (name, surname, salary, year of birth, employee status).
- **CSV Reports Generation**: Export employee data to a CSV file.
- **Group Management**: Create groups, assign employees to groups, and delete groups.
- **Search and Filtering**: Ability to filter employees by surname and other criteria.
- **Graphical User Interface**: The app has a GUI for easy management of data.

## Technologies:

- **Spring Boot** – Backend API
- **JavaFX** – Frontend (GUI)
- **CSV Export** – Export data to a CSV file

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/YourRepo/employee-management-project.git
    ```

2. Build the project using Maven:

    ```bash
    mvn clean install
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

## API

- **GET /employee/export** – Export employee data to a CSV file
- **POST /api/groups** – Create a group
- **DELETE /api/groups/{name}** – Delete a group
- **POST /api/groups/{groupName}/employee** – Add an employee to a group
- **GET /api/groups/{groupName}/employee** – Retrieve employees in a group
