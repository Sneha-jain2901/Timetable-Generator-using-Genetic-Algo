# Time Table Scheduler using Genetic Algorithm

## 📌 Overview
This project is a **Time Table Scheduler** developed in **Java, Swing, and MySQL**, utilizing a **Genetic Algorithm** for optimization. It automates the scheduling process, ensuring minimal clashes and an optimal distribution of lectures.

## 🚀 Features
- Generates a timetable using **Genetic Algorithm**.
- GUI built with **Java Swing** for user-friendly interaction.
- Collision-free scheduling ensuring efficient resource allocation.
- Configurable mutation and crossover rates for GA tuning.

## 🛠️ Setup and Installation

### Prerequisites
- **Java (JDK 8 or higher)**
- **MySQL Database**
- **JDBC Driver** for MySQL

### Steps to Run
1. **Clone the repository**:
   ```sh
   git clone https://github.com/Sneha-jain2901/Timetable-Generator-using-Genetic-Algo.git
   ```
2. **Navigate to the project directory**:
   ```sh
   cd Timetable-Generator-using-Genetic-Algo
   ```

4. **Compile and run the Application**:
   ```sh
   cd "e:\time\src\" ; if ($?) { javac App.java } ; if ($?) { java App }
   ```
   Ensure that **App.java** is the entry point of the project.


## 🧬 Genetic Algorithm Implementation
The Genetic Algorithm is used to generate an optimal timetable by:
- **Encoding** the timetable as a chromosome.
- **Applying selection, crossover, and mutation**.
- **Evaluating fitness** based on constraints like room availability and class conflicts.
- **Iterating through generations** to find the best solution.


---

