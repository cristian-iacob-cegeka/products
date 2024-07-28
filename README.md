# Products Service

## Project Overview

The Products Service is a Spring Boot application that provides RESTful endpoints to manage products. It includes functionalities for creating, updating, and retrieving products with security features such as user authentication and authorization.

## Prerequisites

- Java 21 or later
- Maven 3.8.1 or later
- A running instance of a MySQL database

## Setup and Installation

1. **Clone the repository:**

   ```bash
   git clone git@github.com:cristian-iacob-cegeka/products.git

2. **Configure MySQL database:**

   The database can be configured with the properties available in application.properties

3. **Build and run the project**
    
    ```bash
   mvn clean install
   ```

    ```bash
   mvn spring-boot:run
   
4. **API endpoints**

   Public Endpoints

   `GET /products` - Retrieve all products

   Secured Endpoints

   `POST /products` - Create a new product (Role: USER, ADMIN)

   `PATCH /products/{id}/update-price/{price}` - Update the price of a product (Role: ADMIN)