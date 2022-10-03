# Project Title

Order Report Generator

### Description

This application will take one input .csv file, read order details from it and generate 2 reports out of it.


## Getting Started

### Cloning Repository locally

* git clone https://github.com/ArshadDev/order-report.git


### Build/Compile the program

Execute below Maven command from root directory (a directory where POM.xml file is present), it will create jar file in target folder
* mvn clean install


### Executing program

Run Java Application 
* Right Click -> Run As -> Java Application on main class OrderReportGenerator.java

It will ask for input file as follow
* ---> Please provide the input CSV file path : 

Provide complete path of input .csv file 

Note: Added sample input file for reference in resource folder : 
/order-report/src/main/resources/sample-input.csv

### Test cases execution
When we executed below maven command, all test cases are executed as a part of maven build only
* mvn clean install

We can refer the Junit test cases coverage report at below localtion
* target/site/jacoco/service/index.html

## Authors

Arshad Imran Shaikh
