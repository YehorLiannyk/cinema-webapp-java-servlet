# Cinema Web Application with Java Servlet
## The EPAM Course Final task (Java Servlete API) 
### Introduction
This project is a web application implementation of a cinema WebApp using Java Servlet API.
In a nutshell, there are movies and their sessions, which you can search for using filters, then select needed one, choose seats and buy tickets. But first, you should sign up/in. After purchase, you can find your tickets on the user ticket page and download them in PDF format. Admin can add/remove new movies and movie sessions as well.
### Content
- [Introduction](#introduction)

### Introduction
The system is an online showcase of a single-screen cinema.
##### There are 3 roles:
+ Unauthorized user (guest)
+ Authorized user
+ Administrator!

![configure-and-project](https://user-images.githubusercontent.com/67739980/217591027-94fbdcb5-4eef-490b-85d0-55db405832ce.gif)


##### The system includes:
+ Schedule current films for a week from 9:00 a.m. to 10:00 p.m. (beginning of the last film).
+ An unauthorized user can see the session schedule, and available seats for current movies, and has the opportunity to sign up. The user should be able to sort the movie sessions' schedule by 
    1) the movie name;
    2) the number of available seats; 
    3) the date/time of the session (by default); as well as filter available movies (due to empty seats) in the schedule.
+ A signed-up user has to be able to purchase a ticket for the selected movie session.
+ The administrator can add a new movie to the schedule, cancel the movie, and review the attendance of the hall.

### Technologies
+ **Back end:** Java, Java 8, Jakarta EE, Jakarta Servlet, Servlet Filters; JDBC, SQL; JUnit, Mockito; Log4j2 SLF4J; Google reCaptcha; iText PDF.
+ **Front end:** JSP, JSTL; HTML; CSS, Bootstrap 4.
+ **Database Management System:** MySQL

### How to install/run
Dillinger requires [Node.js](https://nodejs.org/) v10+ to run.

Install the dependencies and devDependencies and start the server.

```sh
cd dillinger
npm i
node app
```

For production environments...

```sh
npm install --production
NODE_ENV=production node app
```

### What was implemented:

+ Designed and built the application and database scheme
+ Used patterns such as **MVC, Front Controller, Factory Method, Singleton, Abstract Factory, DAO, PRG** (Post/Redirect/Get)
+ Implemented Database **ConnectionPool** with Apache Commons DBCP BasicDataSource using Singleton pattern
+ Implemented **logging** in Console and Files using Log4j2 SLF4J
+ Implemented **authentication and authorization** systems
+ Implemented user permissions and page access using **Servlet Filter**
+ Implemented back-end **data validation** (partially using front-end validation with HTML)
+ Added **Google reCaptcha** validation during user sign up
+ Implemented **password encryption** using a salt value for hashing
+ Implemented **JDBC transactions**
+ Implemented **pagination** and schedule **filtering/sorting** at the back-end side using SQL
+ Implemented Ukrainian and English **localizations** using ResourceBundle and JSTL
+ Implemented **Cookies** support for localization and user session
+ Added **PDF** ticket creation using iTextPDF
+ Implemented **exception handling** mechanism with the custom class handler redirecting to the error page
+ Covered the application with tests using **JUnit4** and **Mockito**
+ Created **custom JSP tag files** for header, menu, and footer
+ Created **custom JSP tags** for movie genres enumeration and pagination block

### Entity–relationship model of the database:
![View of the database schema](https://user-images.githubusercontent.com/67739980/177182931-d4df9038-435c-445c-b52c-86a86eb7b736.png)
