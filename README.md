# Cinema Web Application with Java Servlet API :movie_camera:
> The Final task of the EPAM external course

This project is a web application implementation of a cinema WebApp using Java Servlet API.
In a nutshell, there are movies and their sessions, which you can search for using filters, then select needed one, choose seats and buy tickets. But first, you should sign up/in. After purchase, you can find your tickets on the user ticket page and download them in PDF format. Admin can add/remove new movies and movie sessions as well.

### Content
- [Introduction](#introduction)
- [Technologies](#technologies)
- [How to install/run](#how-to-installrun)
- [What was implemented](#what-was-implemented)
- [Entity–relationship model of the database](#entity–relationship-model-of-the-database)
- [Demonstration](#demonstration)
- [Additional info](#additional-info)

## Introduction :wave:
The system is an online showcase of a single-screen cinema.
##### There are 3 roles:
+ Unauthorized user (guest)
+ Authorized user (customer)
+ Administrator

##### The system includes:
+ Schedule current films for a week from 9:00 a.m. to 10:00 p.m. (beginning of the last film).
+ An unauthorized user can see the session schedule, and available seats for current movies, and has the opportunity to sign up. The user should be able to sort the movie sessions' schedule by 
    1) the movie name;
    2) the number of available seats; 
    3) the date/time of the session (by default); as well as filter available movies (due to empty seats) in the schedule.
+ A signed-up user has to be able to purchase a ticket for the selected movie session.
+ The administrator can add a new movie to the schedule, cancel the movie, and review the attendance of the hall.

## Technologies :computer:
+ **Back end:** Java, Java 8, Jakarta EE, Jakarta Servlet, Servlet Filters; JDBC, SQL; JUnit, Mockito; Log4j2 SLF4J; Google reCaptcha; iText PDF.
+ **Front end:** JSP, JSTL; HTML; CSS, Bootstrap 4.
+ **Database Management System:** MySQL

## How to install/run :gear:

1. [Fork](https://github.com/YehorLiannyk/cinema-webapp-java/fork) this repository.
2. Clone the repository on your machine
3. Make sure you have at least v.10.0.26 of Apache Tomcat Server (may be downloaded from [this link](https://tomcat.apache.org/download-10.cgi)) and MySQL Server at least v.8.0.29
4. Using MySQL Workbench (or another tool), import the database schema and data dump from file `cinema-webapp-java/src/sql/cinema_db_{DATE}.sql`. **IMPORTANT**: if the dates and times of the sessions are outdated they will no longer be shown on the session page, so in such case, you need to change the dates of the sessions manually.
5. If needed, change the Database name and credentials in file `cinema-webapp-java/src/main/resources/db.properties`
6. Also be aware of the log files will be saved to `E:\\logs` folder, so you can change the path in file `cinema-webapp-java-servlet/src/main/resources/log4j.properties`
4. Add Tomcat Configuration and Run the app. How to run the project right after downloading:
![](https://user-images.githubusercontent.com/67739980/217591027-94fbdcb5-4eef-490b-85d0-55db405832ce.gif)
6. After running, point your browser to [http://localhost:8090/cinema-webapp-java-servlet/](http://localhost:8090/cinema-webapp-java-servlet/).

## What was implemented :wrench:

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

## Entity–relationship model of the database :triangular_flag_on_post:
There are entities: **Film, Genre, Session, Seat, Ticket, User, and its Role.**

![View of the database schema](https://github.com/YehorLiannyk/cinema-webapp-java-servlet/blob/master/.github/docs/pics/erm.png?raw=true)

## Demonstration :tv:

Firstly, the site is **multilingual**: Ukrainian and English versions. Everything has been translated, except errors and database data. The user lang option is stored in a **cookie file**.

![Multilang demo](https://github.com/YehorLiannyk/cinema-webapp-java-servlet/blob/master/.github/docs/pics/multilang.gif?raw=true)

This is the main page where you can see a list of current films. Films list is **paginated** just like sessions and user tickets are.

![Main page demo](https://github.com/YehorLiannyk/cinema-webapp-java-servlet/blob/master/.github/docs/pics/main-page.gif?raw=true)

The next stop is the Schedule page, where you can see a session schedule and choose which one to buy. **Sorting and filtering** options help you with that, which are implemented using **SQL queries.**

![Schedule page demo](https://github.com/YehorLiannyk/cinema-webapp-java-servlet/blob/master/.github/docs/pics/sessions-page.gif?raw=true)

But only authorized users can buy a ticket, so you have to sign up first. There are backend and frontend (partly) **validations**, and **Google reCaptcha** is also used. All fields, except phone number, are required. 

![Sign up demo](https://github.com/YehorLiannyk/cinema-webapp-java-servlet/blob/master/.github/docs/pics/signup.gif?raw=true)

![Google reCaptcha demo](https://github.com/YehorLiannyk/cinema-webapp-java-servlet/blob/master/.github/docs/pics/recaptcha.gif?raw=true)

Let's try to log out and sign in again. Cookie files save the user role and user id what lets the user stay signed in.

![Login and logout demo](https://github.com/YehorLiannyk/cinema-webapp-java-servlet/blob/master/.github/docs/pics/logout-login.gif?raw=true)

After choosing a session, you pick wanted remained seats and then confirm your purchase. 

![Buy ticket demo](https://github.com/YehorLiannyk/cinema-webapp-java-servlet/blob/master/.github/docs/pics/buy-ticket.gif?raw=true)

You also may **download the ticket in PDF** format. (Sending via email wasn't implemented)

![Download PDF ticket demo](https://github.com/YehorLiannyk/cinema-webapp-java-servlet/blob/master/.github/docs/pics/download-ticket.gif?raw=true)


## Tests :hammer:
Tests were written using the Mockito and JUnit frameworks. More than 50% of code lines are covered.

![test coverage](https://github.com/YehorLiannyk/cinema-webapp-java-servlet/blob/master/.github/docs/pics/test-coverage.png?raw=true)

## Author :raising_hand_man:
You can get in touch with me on my LinkedIn Profile: [![LinkedIn Link](https://img.shields.io/badge/Connect-YehorLiannyk-blue.svg?logo=linkedin&longCache=true&style=social&label=Follow)](https://www.linkedin.com/in/yehor-liannyk/)

You can also follow my GitHub Profile to stay updated about my latest projects: [![GitHub Follow](https://img.shields.io/badge/Connect-YehorLiannyk-blue.svg?logo=Github&longCache=true&style=social&label=Follow)](https://github.com/YehorLiannyk)

If you liked the repo then kindly support it by giving it a star ⭐ 
