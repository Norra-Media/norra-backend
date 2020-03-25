**Project setup**

you need to import the project in eclipse/intellij or any other editor as existing maven project and build it. It will download the dependencies as per the pom.xml file in the project folder from maven repository.

**Required softwares**<br><br>
Maven (Latest version)<br>
Java 1.8<br>
Postgress<br>
Redis<br>


**Building .jar file**

Following commands should be executed post installing above mentioned softwares:

mvn clean
mvn package -DskipTests=true


**.jar file location**

**Project folder**/target/

**API Documentation link**

after starting the project from any editor or after deploying on server, api documentation can be accessed on following link:

**Context Path**/swagger-ui.html




