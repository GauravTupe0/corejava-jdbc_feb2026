# 🚦 Traffic Resolution System (Java + JDBC)

![Java](https://img.shields.io/badge/Java-17+-orange)
![Database](https://img.shields.io/badge/Database-MySQL-blue)
![JDBC](https://img.shields.io/badge/Connectivity-JDBC-green)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

A **console-based Traffic Resolution System** built using **Java and JDBC** that manages drivers, vehicles, violations, and fines.
The application connects to a **MySQL database** and allows administrators to maintain traffic violation records efficiently.

---

# 📌 Features

* Register **Drivers**
* Register **Vehicles**
* Record **Traffic Violations**
* Automatically **Generate Fines**
* Update **Fine Payment Status**
* View **Traffic Violation Reports**

---

# 🛠 Tech Stack

| Technology | Purpose                |
| ---------- | ---------------------- |
| Java       | Core application logic |
| JDBC       | Database connectivity  |
| MySQL      | Data storage           |
| SQL        | Query execution        |
| Scanner    | User input handling    |

---

# 📂 Project Structure

```
Traffic-Resolution-System
│
├── Connectivity
│   └── javab1.java
│
└── README.md
```

---

# 🗄 Database Schema

## Drivers

| Column    | Type    |
| --------- | ------- |
| DriverId  | INT     |
| Name      | VARCHAR |
| LicenseNo | VARCHAR |
| Address   | VARCHAR |

## Vehicles

| Column    | Type    |
| --------- | ------- |
| VehicleId | INT     |
| RegNo     | VARCHAR |
| DriverId  | INT     |
| Type      | VARCHAR |

## Violations

| Column      | Type    |
| ----------- | ------- |
| ViolationId | INT     |
| VehicleId   | INT     |
| Type        | VARCHAR |
| Date        | DATE    |

## Fines

| Column      | Type    |
| ----------- | ------- |
| FineId      | INT     |
| ViolationId | INT     |
| Amount      | DOUBLE  |
| Status      | VARCHAR |

---

# ⚙️ Setup & Installation

## 1️⃣ Clone the Repository

```bash
git clone https://github.com/yourusername/traffic-resolution-system.git
cd traffic-resolution-system
```

---

## 2️⃣ Install Requirements

Make sure the following are installed:

* Java JDK (17 or later)
* MySQL Server
* MySQL JDBC Driver

---

## 3️⃣ Create Database

```sql
CREATE DATABASE trafficdb;
```

Create required tables:

```sql
CREATE TABLE Drivers (
DriverId INT PRIMARY KEY,
Name VARCHAR(100),
LicenseNo VARCHAR(50),
Address VARCHAR(200)
);

CREATE TABLE Vehicles (
VehicleId INT PRIMARY KEY,
RegNo VARCHAR(50),
DriverId INT,
Type VARCHAR(50)
);

CREATE TABLE Violations (
ViolationId INT PRIMARY KEY,
VehicleId INT,
Type VARCHAR(100),
Date DATE
);

CREATE TABLE Fines (
FineId INT AUTO_INCREMENT PRIMARY KEY,
ViolationId INT,
Amount DOUBLE,
Status VARCHAR(20)
);
```

---

## 4️⃣ Configure Database Connection

Update database credentials in the Java file:

```java
static String url = "jdbc:mysql://localhost:8080/trafficdb";
static String username = "root";
static String password = "your_password";
```

---

## ▶️ Running the Application

### Compile

```bash
javac javab1.java
```

### Run

```bash
java javab1
```

---

# 📋 Application Menu

```
Traffic Resolution System

1. Add Driver
2. Add Vehicle
3. Add Violation
4. Update Fine Status
5. View Reports
6. Exit
```

---

# 📊 Example Report Output

```
---- Traffic Reports ----

FineID: 101
Violation: Speeding
Date: 2026-02-20
Amount: 500
Status: Unpaid
```

---

# 🚀 Future Improvements

* GUI using **Java Swing or JavaFX**
* Web-based dashboard
* Fine payment gateway integration
* Admin authentication system
* Automated violation detection

---

# 👨‍💻 Author

**Gaurav Tupe**

* GitHub: https://github.com/GauravTupe0

---

# ⭐ Support

If you like this project, consider giving it a ⭐ on GitHub.
