# Code Fellowship -- Login, Logout, Post, Error Handling,

The Code Fellowship application is a social application that allows users to view and make posts. The app uses Spring Security to handle user sessions with a log in/out system. The application features conditional rendering that is dependant on the user being logged in.

## Instructions

- Build from the command line inside the `codefellowship` directory with `./gradlew bootRun`
    - Set up a Postgres server with a database named "codefellowship".
    - The `src/main/resources/application.properties` file contains generic user/name password resources change these to match your Postgres server username and password.
    - While the app is running direct your web browser to [localhost:8080](http://localhost:8080). This will load the main login page.

### App Use

Follow the link to create an account and enter a username and password, you'll be redirected to your profile page and be automatically logged in. On the profile page create new posts that will be publicly viewable. View other member's posts following the `/profile/{id}` path.

## Features
