# wirecard-technical-challenge

# How to Run the project

After cloning the project repository please access the project directory and run commands below:

	-> mvn clean package
	-> java -jar ./target/wirecard-techincal-challenge-0.0.1-SNAPSHOT.jar  	

# Architecture and Design applied

<p>We used SpringBoot to provide the backend api as a REST service taking advantage of its starter projects in order to decrease the effort to build an application.</p>

<p>In the REST api we're using application/json to communicate with the external world and we also applied HATEOAS to control / authorize the interaction with the resources the API provides.</p>

<p>The Service layer is abstracted as we used interfaces instead of instance. So in the future its easier to replace this logic by a new one.</p>

<p>In the persistence layer we used JPA and Hibernate provider as an ORM framework avoiding us to create all the SQLs related to the database. Moreover we used the Spring Boot Data JPA Starter project to take advantage of its Repository API avoiding us to create Data Access Objects - DAO.</p>  

<p>As DBMS is used H2 in memory database. Once the application is up and running is possible to access the database console using the URL below: </p>

	-> http://localhost:8080/h2-console

# Swagger API documentation

Once the application is up and running you can access the Swagger API documentation using the URL below:

	http://localhost:8080/swagger-ui.html