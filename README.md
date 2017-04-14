## Install Java

    Java 8

## To run the code
    Open the project
    CD: /Users/skmayer/Documents/Auto_Gravity/src/test/java
    Run: RunCukesTest.java

## Cucumber BDD

    Feature: Auto Gravity

    Scenario Outline: Complete Auto Gravity's Finance Application

      Given I open the site "https://apply.autogravity.com/vehicle"

        And I select vehicle make "<make>"

        And I select vehicle model "<model>"

        And I select vehicle trim "<trim>"
        And I select zip "<zip>" and dealership "<dealership>"
      Then I enter my Personal Information
        And I enter my Residence Information
        And I enter my Employment Information
      Then application should be closed
      Examples:
      | make          | model     | trim              | zip    | dealership     |
      | MERCEDES-BENZ | CLA       | CLA               | 90630  | Fletcher Jones |
      | AUDI          | A5        | Man Premium Plus  | 90210  | Beverly Hills  |
      | BMW           | 3 Series  | 330i Sedan        | 90401  | Santa Monica   |

## Folder Structure

    -Auto_Gravity
      -lib          #chromedriver & geckodriver
      -src
        -cucumber   #BDD feature file
        -main       #Page Objects classes
        -tests      #RunCukesTest, Step Definitions
  
