# Find the nearest car parks

## Feature
- find the nearest car parks by providing the current location in latitude and longitude
- sync the car park data from the [data.gov.sg](https://beta.data.gov.sg/collections/85/datasets/d_ca933a644e55d34fe21f28b8052fac63/view)

## Tech Stack
- Spring Boot
- Java 17
- Maven
- MySQL

## How to run

### Docker
```shell
docker-compose up
```

### Local

You might need to update the environment variables in the `application-dev.properties` file.

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/we-go?createDatabaseIfNotExist=true
    username: root
    password: root
```

#### Prerequisites
- MySQL
- Java 17
- Maven

Run the following command to start the application.
```shell
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

Or run the `DemoApplication` class in the IDE, but need to update the `application.properties` file.

```yml
spring:
  profiles:
   active: dev
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: 'false'
    properties:
      hibernate:
        format_sql: 'true'
    open-in-view: false
    dialect: org.hibernate.dialect.MySQL5Dialect
```

## API

### Visit the Swagger UI
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


### Manually sync the latest car park data
```shell
curl --location 'http://localhost:8080/carparks/update-availability'
```

### Find the nearest car parks
```shell
curl --location 'http://localhost:8080/carparks/nearest?latitude=100.89188186642683&longitude=0.34776178103640865&page=1&per_page=10' \
--header 'latitude: 26367.5806' \
--header 'longitude: 26367.5806'
```

Example response
```json
{
    "content": [
        {
            "address": "BLK 269A PASIR RIS STREET 21",
            "latitude": 100.89188186642683,
            "longitude": 0.34776178103640865,
            "totalLots": 227,
            "availableLots": 161,
            "kmDistance": 0.0
        },
        {
            "address": "BLK 257A PASIR RIS STREET 21",
            "latitude": 100.8916739292079,
            "longitude": 0.35052001571179203,
            "totalLots": 239,
            "availableLots": 155,
            "kmDistance": 0.06239488396048562
        },
        {
            "address": "BLK 149A PASIR RIS STREET 13",
            "latitude": 100.8911484360812,
            "longitude": 0.3453321852408804,
            "totalLots": 245,
            "availableLots": 177,
            "kmDistance": 0.0962120417780919
        },
        {
            "address": "BLK 204A PASIR RIS STREET 21",
            "latitude": 100.89090026914312,
            "longitude": 0.3497101837319567,
            "totalLots": 355,
            "availableLots": 230,
            "kmDistance": 0.11657266013196697
        },
        {
            "address": "BLK 245A PASIR RIS STREET 21",
            "latitude": 100.89183444113847,
            "longitude": 0.353775375206712,
            "totalLots": 412,
            "availableLots": 269,
            "kmDistance": 0.12646122904116636
        },
        {
            "address": "BLK 258 PASIR RIS ST 21",
            "latitude": 100.89297128780426,
            "longitude": 0.34989037599743616,
            "totalLots": 222,
            "availableLots": 116,
            "kmDistance": 0.12913120028480518
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 6,
        "sort": {
            "empty": true,
            "unsorted": true,
            "sorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalPages": 308,
    "totalElements": 1845,
    "first": true,
    "size": 6,
    "number": 0,
    "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
    },
    "numberOfElements": 6,
    "empty": false
}
```