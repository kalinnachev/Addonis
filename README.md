# Addonis

## Description
**Addonis** is a web application that gives users the opportunity to manage add-ons. The Aplication is with three types of users: 
- anonymous;
- regular;
- administrator.

Anonymous users are able to filter addons by name or IDE and sort by the name, number of downloads, upload date, last commit date. They can also download a plugin, directly from the landing page.

Registered users have private area in the web application accessible after successful login, where they could see all extensions that are owned by the currently logged user. Additionally, the registered users are able to delete, update, create their own addons and rate all addons. Once addon is created the it is "pending" state until the administrator approves it. The extension is visible in the landing page only if it is approved.

System administrators can administer all major information objects in the system. On top of the regular user capabilities, the administrators have the following capabilities: 
- to approve new addons;
- delete or edit all addons;
- disable users accounts.


## Technologies
- Spring MVC
- Spring Security
- Hibernate
- Spring Data JPA
- Thymeleaf
- HTML
- CSS
- JUnit

## Database
![picture](imag/database.png)


