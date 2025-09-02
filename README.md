# Pharmacy Management System

## Overview

The Pharmacy Management System is a comprehensive Java-based desktop application designed to streamline pharmacy operations. It provides a dual-interface system catering to both customers and employees, enabling efficient management of sales, inventory, and user data. The system features a graphical user interface (GUI) built with Java Swing and connects to a MySQL database for robust data handling.

This project demonstrates a practical application of object-oriented programming, database management, and GUI development to solve a real-world business need.

## Key Features

- **Dual User Roles:** Separate login and functionality for Employees and Customers.
    - **Employee Panel:**
        - Secure login authentication against the `employees` table.
        - Ability to add new employee accounts.
    - **Customer Portal:**
        - Secure customer login against the `customers` table.
        - User-friendly registration for new customers.
        - Visually browse available medicines with images, names, and prices retrieved from the database.
        - Add medicines to a shopping queue by specifying the product ID and quantity.
- **Inventory & Sales Management:**
    - The system dynamically displays medicines from the database.
    - Real-time inventory updates upon customer purchase by modifying the quantity in the database.
    - Prevents the sale of medicines if the requested quantity is not available.
- **Invoice Generation:**
    - Customers can generate a detailed invoice for their purchases.
    - The invoice displays the name, price, and quantity for each item, along with the total cost.
- **Graphical User Interface (GUI):**
    - The entire application is built using the **Java Swing** library, offering a responsive and interactive user experience.
    - Custom panels with background images are used to create a visually appealing interface.
- **Database Connectivity:**
    - Robust integration with a **MySQL** database using JDBC.
    - Securely manages employee, customer, and medicine data.

## Technologies Used

* **Programming Language:** Java
* **GUI Framework:** Java Swing
* **Database:** MySQL
* **Database Connectivity:** JDBC (Java Database Connectivity)

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

* **Java Development Kit (JDK):** Version 8 or higher.
* **MySQL Server:** A running instance of a MySQL server.
* **MySQL JDBC Driver:** The Connector/J driver for MySQL.
* **IDE (Optional):** An Integrated Development Environment like IntelliJ IDEA, Eclipse, or NetBeans.

