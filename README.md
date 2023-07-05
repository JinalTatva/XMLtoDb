**IApps task**

**Requirements**

For building and running the application you need: Java 17, Postgres, Eclipse/STS/CMD/TERMINAL

Running the application locally
Changes your username and Password for Postgres from the application.properties file.
Before starting an application you must need to build the project using the following command.
_mvnw clean install_
Command to execute:-

_java -jar target/XMLtoDb-0.0.1-SNAPSHOT.jar_
or

_mvn spring-boot:run_
For testing test cases:- In STS/Eclipse Run XmLtoDbTests.java with JUnit Test or run "_mvnw test_"

**API endpoints:**

**Post API**: URL:- http://localhost:8080/api/upload-file  Body form-data file fileName(select xml file) //Also added valid and invalid XML files for testing in the resources folder.

**Get API with filters**: (All paramaters are non mandatory.) Url:- http://localhost:8080/api/epaperList 

params are:-

pageNo (starting with 0)
pageSize (no of records you want in result)
sortBy (specific column name on which you need to sort)
asc (true for ascending, false for descending approach on sortBy column)
search (keyword for searching)
