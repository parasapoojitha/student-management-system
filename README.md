# Student Management System

A console-based Java application to manage student records with full CRUD operations and file storage.

## Features
- Add, view, search, update, and delete student records
- Duplicate ID check
- Input validation (numbers, empty values, CGPA range 0-10)
- Data saved automatically to `students.csv` and loaded on startup

## Tech Used
- Java (OOP: classes, encapsulation)
- Collections (ArrayList)
- File handling (FileReader, FileWriter)
- Exception handling

## Project Structure
- `Student.java` - Student model (fields, getters/setters, CSV conversion)
- `StudentManager.java` - CRUD logic and file storage
- `Main.java` - Menu-driven user interface

## How to Run
javac *.java
java Main

## Future Improvements
- Connect to MySQL using JDBC
- Add sorting by CGPA and search by name
- Build a GUI with JavaFX or Swing
