# Spring Ecommerce Application

A full-featured e-commerce web application built with Spring Framework and Spring Security.


## Tech Stack

- **Backend Framework**: Spring Framework 6.2.11
- **Security**: Spring Security 6.3.5
- **ORM**: Hibernate 6.4.4.Final
- **Database**: Postgres (configured via Hibernate)
- **Frontend**: JSP(Java Server Pages)
- **Build Tool**: Maven
- **Java Version**: 21

## Features

- **User Management**
  - Registration and login
  - Secure authentication with Spring Security
  - User account management

- **Product Catalog**
  - Browse all products
  - Product details view
  - Admin product management (add/update/delete products)

- **Shopping Cart**
  - Add/remove products
  - Cart management
  - Order processing

- **Order Management**
  - Order creation and tracking
  - Order history view

- **Security**
  - Role-based access control
  - Admin and user roles


## Getting Started

### Prerequisites
- Java 21
- Maven 3.6+

### Installation

1. Clone the repository
   ```bash
   git clone https://github.com/MaheshGudooru/Spring_Ecommerce.git
   cd Spring_Ecommerce
   ```

2. Build the project
   ```bash
   mvn clean install
   ```

3. Configure database connection in `src/main/resources/application.properties`

4. Deploy to a servlet container (Tomcat)


## Project Pages

- **Home** - Main landing page
- **Products** - Browse all products
- **Product Details** - View individual product information
- **Login** - User authentication
- **Register** - New user registration
- **Account** - User profile and account management
- **Cart** - Shopping cart management
- **Orders** - Order history and management
- **Admin Panel** - Product management for administrators

## File Structure

- `pom.xml` - Maven configuration
- `src/main/resources/` - Application properties and logging configuration
- `src/main/java/com/techouts/ecommerce/config` - Entire poject configurations
- `src/main/webapp/static/` - Static assets (CSS, JavaScript)
- `src/main/webapp/WEB-INF/views/` - JSP view templates


