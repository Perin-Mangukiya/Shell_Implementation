[![progress-banner](https://backend.codecrafters.io/progress/shell/77ec2dee-019f-4960-95a1-ae0144f40b55)]()
# Shell Implementation Project

## Overview
This project is a Linux-based shell developed in Java that implements basic command-line functionalities such as `echo`, `exit`, and `cd`. Additionally, it features advanced capabilities like REPL (Read-Eval-Print Loop) functionality, quoting mechanisms, and seamless directory navigation.

## Features
- **Command Execution**: Supports basic Unix commands like `echo`, `exit`, and `cd`.
- **REPL (Read-Eval-Print Loop)**: Provides an interactive loop to input commands, evaluate them, and display the results.
- **Quoting Mechanisms**: Handles single and double quotes to manage complex command inputs.
- **Directory Navigation**: Allows users to navigate the file system using relative and absolute paths.
- **Input/Output Redirection**: Supports redirection of input (`<`) and output (`>`).
- **Custom Commands**: Implements additional commands specific to this shell.
- **Error Handling**: Provides user-friendly error messages for invalid commands or arguments.

## Technology Stack
- **Programming Language**: Java
- **Development Tools**: Java SE Development Kit (JDK), IDE/Text Editor (e.g., IntelliJ IDEA, Eclipse, or VS Code)
- **Operating System**: Linux-based (tested on various distributions)

## Installation
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```bash
   cd shell-implementation
   ```
3. Compile the Java files:
   ```bash
   javac *.java
   ```
4. Run the shell:
   ```bash
   java Shell
   ```

## Usage
1. Start the shell by running the project.
2. Type commands at the prompt and press `Enter` to execute.
3. Use `exit` to terminate the shell.

### Example Commands
- Print a message:
  ```bash
  echo "Hello, World!"
  ```
- Change the working directory:
  ```bash
  cd /path/to/directory
  ```
- Exit the shell:
  ```bash
  exit
  ```
- Use quotes to manage complex inputs:
  ```bash
  echo "This is a 'quoted' message."
  ```

