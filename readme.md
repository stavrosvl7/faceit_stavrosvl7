# User management application

Simple user management application written in java with Spring framework

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
First you have to download and install zookeeper (https://zookeeper.apache.org/releases.html)
Then you have to download and install kafka (https://www.apache.org/dyn/closer.cgi?path=/kafka/2.7.0/kafka_2.13-2.7.0.tgz)
After installing both successfully and adding environment variables needed you have to run the following in your cmd:
1) zkserver
2) open cmd in kafka installation folder and execute .\bin\windows\kafka-server-start.bat .\config\server.properties
3) git clone https://github.com/stavrosvl7/faceit_stavrosvl7 in your intelliJ projects folder

Create a postgresql database and change the following at application.yml with your own credentials.

1) url: jdbc:postgresql://localhost:%YOUR_POSTGRESQL_PORT%/%YOUR_DATABASE_NAME%
2) username: %YOUR_POSTGRESQL_USERNAME%
3) password: %YOUR_POSTGRESQL_PASSWORD%

Copy & paste sql scripts in your database's query tool. You can find them in folder _scripts in this project.

Ιf all the above have been accomplished successfully you should be able to open faceit project from intellij.
Clean and install project through maven plugin and then run it as spring boot application.

### Prerequisites

What things you need to install the software and how to install them

```
Java installation (https://www.java.com/en/download/manual.jsp)
Zookeeper and Kafka installation (https://www.youtube.com/watch?v=OJKesEpO6ok&ab_channel=GopalTiwari)
IntelliJ installation + licence (alternative eclipse J2EE)
Postman installation and usage (swagger is also included)
```

## Running the tests
Available API Paths:
1) http://localhost:8081/api/users/add HttpMethod:POST (Adding a new user)
2) http://localhost:8081/api/users/all HttpMethod:GET (Fetching all users)
3) http://localhost:8081/api/users/all/by/country/{countryName} HttpMethod:GET (Fetching all users for this country name)
4) http://localhost:8081/api/users/modify/{userId} HttpMethod:PUT (Modifying an existing user)
5) http://localhost:8081/api/users/remove/{userId} HttpMethod:DELETE (Removing an existing user)

After everything is up and running there are two ways that you can test the application.
1) http://localhost:8081/swagger-ui.html and just try out the APIs
2) through postman send the appropriate requests to http://localhost:8081/api/%API_PATH%

In both ways you can see the results!

### Assumptions

For demonstration purposes , when a new user is added , the consumed event calls another microservice
by injecting our User controller. In a bigger application we could create separate services for every
Entity like UserService and microservices could communicate through this service and call each other.
which is fetching all users that exist in database and everything is working according to task.


```
This happens when you call http://localhost:8081/api/users/add
Automatically after this is called http://localhost:8081/api/users/all through event hanlding
```

A new user must have:

```
Valid and unique email address
Valid country Id which refers to dom_country table in database
```

I created an entity Country which consists of:

id,name,code,phone_international_prefix

```
Country must be a seperate entity with it's own fields
In this way database is more organized and optimized.
I did this because countries are something standard which never changes.
We should better not have a column of strings in our User entity
```

## Built With

* [Spring](https://spring.io/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Kafka](https://kafka.apache.org/) - Events and connection between microservices 
* [Swagger](https://swagger.io/) - API testing and documentation 

## Authors

* **Stavros Vlachakis** - *Personal work* - [Stavros Vlachakis](https://github.com/stavrosvl7)

