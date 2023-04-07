# Longest Common Substring (LCS) Server

#### Solve the Longest Common Substring problem via HTTP POST

#### By Zhibin Liang

## Description

A user should be able to request the LCS of a Set of Strings by sending a POST request to the server at http://localhost:8080/lcs.

## Setup/Installation Requirements

### Getting the repo

- Clone the repo to your desktop or any directory of your choice

  - Run the `git clone https://github.com/zbl14/spring-boot-lcs-server.git` command to clone the remote repository to the local device

- Or download as a zip file
  - Click the green code button on the repository page
  - At the bottom of the popup window select `Download ZIP`
  - Extract the downloaded .zip folder

### Run the server

- Run the server with IDE

- Or run the server with the teminal
  - Navigate to the root directory of the project using the `cd` command in the terminal.
  - Use the `mvn clean install` command to build your project and generate a JAR file in the target directory.
  - Once the build is successful, navigate to the target directory using the `cd` command.
  - Use the `java -jar spring-boot-lcs-server-0.0.1-SNAPSHOT.jar` command to run the JAR file

### Solve the Longest Common Substring problem

- Open `http://localhost:8080/` in your browser, enter strings (one per line) in the text area, and click `Find Longest Common Substring(s)`

- Or use Postman
  - In the request tab, enter the following URL `http://localhost:8080/lcs`
  - Select the HTTP method as "POST"
  - Under the "Body" tab, select JSON as the type of data
    ```
    {
      "setOfStrings": [
        {"value": "comcast"},
        {"value": "communicate"},
        {"value": "commutation"}
      ]
    }
    ```

## Contact Information

Zhibin Liang [Email](zhibin.ben.liang@gmail.com) || [GitHub](https://github.com/zbl14) || [LinkedIn](https://www.linkedin.com/in/zhibin-liang/)

Copyright &copy; 2023 Zhibin Liang
