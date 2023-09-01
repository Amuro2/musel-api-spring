
# MuSeL Spring API

This project is the server-side API of the MuSeL app (paired with [a client app made using React](https://github.com/Amuro2/musel-app-react)).

MuSeL is a small application that enables listening to music on a continuous loop seamlessly.

## Setup

- Clone the repository. (Suggesting using IntelliJ.)

- (Solve potential problem: `Unsupported class file major version 64`)
  - Gradle Settings > Gradle JVM: corretto-17

- Add the application-local.properties file:
  - Download [application-local.properties](https://github.com/Amuro2/musel-api-spring/files/12494955/application-local.properties.txt).
  - Add application-local.properties to the `/src/main/resources/` directory.

- Setup the MySQL Database:
  - Download [dump-empty.sql](https://github.com/Amuro2/musel-api-spring/files/12494981/dump-empty.sql.txt).
  - Create the MySQL database: `mysql -e "CREATE DATABASE musel"`.
  - Import the MySQL database dump: `mysql --force musel < dump-empty.sql`.

- Create a `/files` directory at the root of the project.

## Run the API

- Ensure the MySQL server is running.
  - Run `mysqld`.

- Run `src/main/java/com/amtak/muselspringapi/MuselspringapiApplication.java`.
