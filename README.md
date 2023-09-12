# BiblioSystem

This README provides instructions on how to set up and use the BiblioSystem app. The app is designed to manage a library's database and is built using Java with JDBC and Apache Commons DbUtils.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Running the App](#running-the-app)
- [Usage](#usage)

## Prerequisites

Before using the BiblioSystem, ensure you have the following prerequisites installed on your system:

1. **Java Development Kit V20 (JDK20):** The app is written in Java, so you need to have the JDK installed. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html).

2. **MySQL Database:** You will need a MySQL database server. You can download and install MySQL from the [official MySQL website](https://www.mysql.com/).

3. **JDBC Driver:** Download and include the MySQL JDBC driver in your project. You can download it from the [MySQL Connector/J download page](https://dev.mysql.com/downloads/connector/j/).

4. **Apache Commons DbUtils:** This library simplifies database access in Java. You can download it from the [Apache Commons DbUtils website](https://commons.apache.org/proper/commons-dbutils/).

## Installation

1. Clone the repository or download the source code of the BiblioSystem App to your local machine.

```shell
git clone git@github.com:Library-Management-sytem/Application.git
```

2. Open the project in your preferred Java development environment (e.g., IntelliJ IDEA, Eclipse).

3. Include the MySQL JDBC driver and Apache Commons DbUtils library in your project's build path.

## Database Setup

1. Run the provided MySQL script to create the necessary database and tables and populate them with dummy data. You can use a tool like MySQL Workbench or run the script from the command line.

```shell
mysql -u yourusername -p < library_system_database.sql
```

Make sure to replace `yourusername` with your MySQL username.

2. Verify that the database and tables have been created successfully by checking your MySQL server.

## Running the App

1. Open the BiblioSystem project in your development environment.

2. Configure the database connection parameters in the app code. Copy the db.properties.example and rename it db.properties and adapt the content to your credentials (Change the username and password).

3. Test the database connectivity to ensure that the app can connect to your MySQL database successfully. You can do this by running a test connection method or executing a sample query.

```java
// Example test connection code
try {
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "yourusername", "yourpassword");
    System.out.println("Database connection successful.");
    connection.close();
} catch (SQLException e) {
    System.err.println("Database connection error: " + e.getMessage());
}
```

4. Once the database connectivity is confirmed, you can run the Java Library System App. This should launch the application and provide access to its features for managing your library's data.

## Usage

- Use the app's console to perform actions such as adding books, managing prints, checking out and returning books, and other library-related tasks.

- Follow the on-screen instructions and prompts to navigate through the app's functionality.

- For any issues or questions, refer to the app's documentation or contact the app's developer for support.

That's it! You should now have the Java Library System App up and running, allowing you to manage your library's database and perform various library management tasks. Enjoy using the app!