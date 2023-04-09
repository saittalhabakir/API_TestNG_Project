# README
Hi!
this is the readme file of the *Trendyol QA Engineer Task - test automation challenge.*  I have automated the https://www.trendyol.com page as UI and also automated some API tests.
## Overview
This project is designed for UI and API automation testing. The framework is setup on Maven build tool, therefore all the dependency and libraries are managed through pom.xml file. I used Java as a programming language, Selenium as a Webdriver, TestNG for Assertions, TestNG Extent Report. All the details of the Framework will be explained below.
## Short Intro and Benefits
I can describe my framework as **easy to maintain, scalable and highly reusable** because of the following reasons:
- I am using **Page Object Model**, which separates test scripts and web element locators. By that way even if there is a change in a web element which is used in many places, I am changing it in a central one place.
- I am using **Configuration Properties** file, so that all the system variable are in central place. Thus, changing an url or credential in overall system is quite easy.
- Thanks to the **singleton pattern** structure that I used in my framework, I can run *regular tests, as well as cross browser/cross platform tests (including web)*
- By adding necessary dependencies, I can test **Front-end** and **Back-end** simultaneously (i.e. comparison of all UI).
## Install to Local and Run
Its a **maven** project. Therefore, you need to pull it to your machine and run either through maven lifecycle (test) or from terminal. After locating terminal to the project directory, type the following:
`> mvn test `
#### Alternatively:
You can run projects from maven lifecycle: edit the browser from configuration.properties file (see available drivers in Driver class file) and then you can run test suite by clicking *maven lifecycle test*.
## Framework and Patterns
In a maven project I have the following structure:
### Root Files
- **pom.xml file:** to manage java version, dependency and libraries.
- **configuration.properties file:** I use this file to centralize system-wide variables such as urls, connection strings, test environment addresses, browser names etc. By this way any change in any system variable can be effective in overall framework.
### Library
- **pages package:** this package contains a base page for the *whole application* which contains the ***Page Object Model Design Pattern***,  common menus, web elements and methods. Moreover, the package contains Page class for every page in the web app, which centralizes the locators and methods related to the page.
- **Tests package:** this package contains the test script methods. Test scripts uses objects created from the page classes.
- **utilities package:** this page contains general important tools.
- ***Driver class:*** it has ***Singleton Pattern*** therefore supplies only one driver per thread so that the same driver is used.
- ***ConfigurationReader class:*** I use this class to read data from configuration.properties file.
- ***BrowserUtils class:*** This class is my toolkit for commonly used actions and methods, during the time it becomes more crowded. Here I only wrote necessary tools.
- ***TestBase class:*** The main purpose of using a Test Base Class is to take advantage of inheritance, encapsulation and clean code. These concepts are standard programming principles, these are used for all code environments, not just with test automation.
- ***APITestCase class:*** The main purpose using  a APITestCase Class is to variable contains the API root we should use to access the API.
### Final Say
I hope this task has shown my interest in Trendyol and the opportunities given by them and my desire to learn and develop more.
