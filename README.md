# SIET Placement Portal

A robust console-based Java application designed to streamline campus placement workflows for students and teachers at SIET. This system follows the Model-View-Presenter (MVP) architecture to ensure a clean separation of concerns and maintainable code.

## 🚀 Features

### 👨‍🎓 Student Portal
- **Company List:** Browse through the list of companies visiting for placements.
- **Placement Status:** Track your current placement progress and status.
- **Interview History:** View details of past and upcoming interview schedules, including status and remarks.
- **Skill Management:** Update and manage your professional skill set.
- **Profile Management:** View and verify personal and academic details.

### 👨‍🏫 Teacher Portal
- **Student Management:** View registered students, add new students, or remove existing ones.
- **Company Management:** Maintain the list of participating companies by adding new company profiles.
- **Interview Scheduling:** Coordinate interviews by scheduling sessions between students and companies.
- **Profile Management:** Manage personal teacher profile information.

## 🛠️ Tech Stack

- **Language:** Java (JDK 17+)
- **Architecture:** MVP (Model-View-Presenter)
- **Database:** MySQL
- **Utility Libraries:** Custom console UI and input parsing utilities.

## 📋 Prerequisites

- **Java Development Kit (JDK):** Version 17 or higher.
- **MySQL Server:** Installed and running locally.
- **MySQL Connector/J:** Required for JDBC connectivity (ensure it's in your classpath).

## ⚙️ Database Setup

1. Create a MySQL database named `placementDB`:
   ```sql
   CREATE DATABASE placementDB;
   ```
2. The application is designed to automatically create the necessary tables (`students`, `teachers`, `companies`, `interviews`, etc.) upon the first run, provided the connection details are correct.
3. Update the database credentials in `src/com/gokulrajvel/placementportal/data/repository/PlacementJDBCConnection.java` if necessary:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/placementDB";
   private static final String USER = "root";
   private static final String PASSWORD = "your_password";
   ```

## 🏃 How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/SIET_Placement_Monitoring.git
   ```
2. **Compile the project:**
   Navigate to the `src` directory and compile the main class along with its dependencies.
   ```bash
   javac -d ../bin -cp ".:../lib/*" PlacementPortalSIET.java
   ```
3. **Run the application:**
   ```bash
   java -cp "../bin:../lib/*" PlacementPortalSIET
   ```

## 📁 Project Structure

- `src/`: Contains the Java source files.
  - `com/gokulrajvel/placementportal/features/`: UI features organized by role (Signin, Signup, StudentPortal, TeacherPortal).
  - `com/gokulrajvel/placementportal/data/`: Data Transfer Objects (DTOs) and Database Repositories.
  - `util/`: Common utility classes for console interactions and parsing.
- `lib/`: Directory for external JAR files (e.g., MySQL JDBC Driver).
- `README.md`: Project documentation.
