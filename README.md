# Movie Review API

[![Build Status](https://travis-ci.org/khwilo/movie-api.svg?branch=master)](https://travis-ci.org/khwilo/movie-api)

Movie review API where users can review various movies and series that one has watched.

## API ENDPOINTS

- _POST_ `/api/v1/auth/register` - **Register a user**
- _POST_ `/api/v1/auth/login` - **Login a user**
- _GET_ `/api/v1/users` - **Fetch all existing users**
- _GET_ `/api/v1/users/{id}` - **Fetch one existing user**

## USAGE

1. Clone the repository
2. `cd` into the project
2. Run these commands on your terminal:

```bash
$ mvn clean install
$ java -jar target/movie-api-0.0.1-SNAPSHOT.jar
```