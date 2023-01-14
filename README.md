## UI Tests Examples

Used technologies: **Java**, **SLF4J**, **Selenium webDriver**, **TestNG**.

Tested functionalities:
1.	Deliver To 
2.	Search field 
3.	Add/Remove item into cart 
4.	Filtering and Searching 

Test scenarios and related test cases steps can be found [here](test_cases_description.pdf).

## Running tests and generating Allure report:

Use maven command:```mvn clean test```

## Accessing the report manually:

1. Generate the report by running: ```mvn allure:report```
2. You can access the report by opening ```target/site/allure-maven-plugin/index.html``` in a browser

## Accessing the report automatically:

To Generate and open the report in your default browser run: ```mvn allure:serve```




