OVERVIEW:
=========
This is the code base for the Java User Group application presented on February 1, 2011. 

REQUIREMENTS:
-------------

* Java: 1.6 or above
* Grails 1.3.7

SETUP:
------
The project consists of 2 applications and one plugin.

* moviestore - Grails application which represents the UI
* movie-rest - Grails application which exposes a REST API
* movie-domain - Grails plugin which consists of the domain model

BEFORE RUNNING:
---------------
Before running either moviestore or movie-rest, you must do the following:

1. CD into the movie-domain directory
2. Run the command, **grails package-plugin**
3. CD into moviestore application
4. Run the command, **grails install-plugin ../movie-domain/grails-movie-domain-0.1.zip**
5. CD into movie-rest
6. Run the command, **grails install-plugin ../movie-domain/grails-movie-domain-0.1.zip**

RUNNING APPLICATIONS LOCALLY:
-----------------------------
The moviestore and movie-rest applications are setup to work on one machine by default.  In order to run both applications do the following:

1. CD into moviestore directory
2. Run the command, **grails run-app**
3. Open new terminal
4. CD into movie-rest directory
5. Run the command, **grails -Dserver.port=8081 run-app**

RUNNING MOVIE-REST APPLICATION ON SEPARATE MACHINE:
---------------------------------------------------
Since the application was designed to run on a single machine, you will need to change one setting first. 

1. Open *moviestore* application in your favorite editory.
2. Modify the line **static restEndpoint = "http://localhost:8081/movie-rest/"** to the ip address and port you have the movie-rest application on

*Note:* Typically, endpoints are stored in Config.groovy or other config file.  We have the endpoint configured in class for demo purposes.