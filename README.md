# LockerCloud v2

This project demonstrates a simple client/server file synchronization system written in Java 17.

After packaging, the application jar can be run directly. The Dockerfiles use the
classpath to launch either the server or client main classes.

## Build

```
mvn package
```

## Docker

A minimal docker-compose configuration is provided. Build and run using:

```
docker-compose build
docker-compose up
```
