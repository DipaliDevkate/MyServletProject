# Java Servlet CRUD Project - RegistrationForm

## Project Overview
This is a **Java Servlet-based web application** demonstrating **CRUD operations** (Create, Read, Update, Delete) using **HTML/JSP frontend** and **MySQL database**.  
The application includes **user registration, login, update, delete, and logout functionality**, making it a complete user management system.

---

## Features
- **User Registration**: Username, Email, Phone, Address, Gender, Password  
- **Login & Authentication**: Secure login for registered users  
- **Display Users**: View all users in a dynamic HTML table  
- **Update User**: Edit email and password  
- **Delete User**: Remove user records  
- **Logout**: Securely ends the session  
- **CSS Styling**: Clean and professional UI  

---

## Technologies Used
- **Java Servlet (Jakarta Servlet)**
- **HTML / CSS**
- **MySQL Database**
- **Eclipse IDE**
- **Tomcat Server**
- **Git & GitHub**

---

## Project Structure
RegistrationForm/
├── src/                       # Java source code
│   └── com/org/               # Package containing servlets
│       ├── UserDataServlet.java       # Displays all users
│       ├── UpdateUserServlet.java     # Handles updating user data
│       ├── DeleteServlet.java         # Handles deletion of user
│       ├── LogoutServlet.java         # Handles user logout
│       └── loginRe.java               # Handles user login
│
├── WebContent/ or webapp/     # Frontend files
│   ├── index.html             # Home page / Registration page
│   ├── login.html             # Login page
│   ├── update.html            # Update user form
│   ├── UserDataServlet.jsp    # Optional JSP table (if using JSP instead of pure servlet HTML)
│   └── style.css              # Stylesheet for UI
│
├── lib/                       # Any external JAR dependencies (if required)
│   └── mysql-connector-java-<version>.jar  # MySQL JDBC driver
│
├── .gitignore                 # Git ignore file (exclude IDE metadata, logs)
├── README.md                  # Project description and instructions
└── build/                     # (Optional) compiled classes if using manual compilation
