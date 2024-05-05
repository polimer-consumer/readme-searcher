# Spring Boot GitHub API App

This is a simple Spring Boot application that allows users to input a GitHub organization name and API token. After clicking the "Go" button, it shows all repositories of organization. Ones containing the word 'Hello' in their README.md file are highlighted.

## How to Run

1. Clone repo
2. Open a terminal or command prompt and navigate to the project directory.
3. Build the project:`./gradlew build`
4. Once the build is successful, run the application:`./gradlew bootRun`
5. After the application starts, open [http://localhost:8080/](http://localhost:8080/).

## Usage

1. Enter the GitHub organization link/name and GitHub access token.
2. Click "Go".
3. The application will display all repositories of the specified organization. Repositories containing the word 'Hello' in their README.md file will be highlighted.

## Tools Used

- Spring Boot
- Kotlin
- Thymeleaf
- Spring WebClient
