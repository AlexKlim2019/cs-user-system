# CS User System

### Description:
This service provides a some endpoint to manage user data from Postgres databases.

### Installation:
#### Prerequisites:
Your system should hava the following installed programs:
* Git
* Java Development Kit (JDK) version 17 or higher
* PostgreSQL version 13.14 or higher
* Maven version 3.9.6 or higher
#### Steps
1. Clone the repository
```bash
  git clone git@github.com:AlexKlim2019/cs-user-system.git
```
2. Navigate to project directory:
```bash
  cd cs-user-system
```
3. Build the project:
```bash
  mvn clean install
```
4. Run the application:
```bash
  cd .\user-service\container\
  mvn spring-boot:run
```
5. Access the application:
   Once the application is running, you can access it at the following URL:
```http
  http://localhost:8081/
```

### Documentation: [OpenAPI documentation](http://localhost:8081/swagger-ui/index.html)

### Contact:
<a href="mailto:alex.klim.dev@gmail.com?"><img src="https://img.shields.io/badge/gmail-%23DD0031.svg?&style=for-the-badge&logo=gmail&logoColor=white"/></a>