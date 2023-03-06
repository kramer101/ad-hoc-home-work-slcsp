## Requirements
In order to run the homework, the following is required to be installed on the machine where code is executed:

* Unix based OS such as MacOS

* `OpenJDK 19 64-Bit`

* `Apache Maven 3.9.0`

## Compile instructions
* Unzip the `ad-hoc-home-work-slcsp.zip` archive
* Open Command Prompt (such as Terminal)
* Navigate (change directory) to the unzipped folder `ad-hoc-home-work-slcsp`
* Execute the command `mvn clean install`. The `BUILD SUCCESS` message would indicate a successful build

## Execute instructions
* Run the following command in the root folder containing the successful build:
`java -cp ./target/ad-hoc-home-work-slcsp-1.0-SNAPSHOT-jar-with-dependencies.jar com.adhoc.homework.slcsp.SlcspApplication`

Upon successful execution, the zipcode and rate area output is produced in `stdout` between the 


`------------------RESULTS START------------------` 

and 

`------------------RESULTS END------------------` 

lines

## Assumptions and limitations of this POC

* Input data file may not be specified as an input parameter. <br/>Instead, input data will be read from the <code>./target/classes/data/slcsp.csv</code> file
* Using Lombok for POJO auto generation because I haven't used the Java 14 "record" yet, so staying on a safe side
* Number of written JUnit tests is limited due to time constraint
* Any "TODO" annotation indicates my vision of possible improvement


## Approach to solution

Task is to find and print out the price of the second lowest cost silver plan (SLCSP) for each zip code provided in the slcsp.csv file.

I've started with analyzing the data in both additional files - Plans and Zip codes.

The Plans file contains the final piece of data we need to output - the price of the plan.
There is a mapping to STATE and RATE AREA in the plans.csv file for each of the Rates.
By correlating the combination of STATE+RATE AREA against the data in the zips.csv file, we should be able to map the rate to a zip code 
(or to multiple zip codes to that matter).


**Note**: I did not see how County Code or County Name in zips.csv could be of any use to me since plans are 
specified based on State and Rate Area, each serving multiple Zip Codes in a state regardless of county.
Hence, there is no reference in my source code to County code, or County Name

### High level steps (pseudo code)

1. Load the zip codes in scope from slcsp.csv as list of Integers 
2. Load the plans/rates data **for Silver plans only** from plan.csv into a hash map
   3. Key: a STATE+RATE Area tuple
   4. Value: a set of plan rates
4. Load the zip codes data from zips.csv into a hash map 
   5. Key: a ZIP Code
   6. Value: a unique set of STATE+RATE Area tuples 
6. For each zip code in scope from slcsp.csv (preserving the original order):
   7. Lookup the zip code from the zip codes hash map (step 4 above) , and if found:
   8. Lookup all the rates from the plans/rates hash map by STATE+RATE Area tuple and if found:
   9. Combine all the rates mapped to a zip code into a hash set, sort in the ascending order and if `size > 1`:
   10. Obtain the element at position 1 from the hash set and print it out next to the zip code - that should be the second lowest silver plan for the zip code

    
### Technical approach and solution

I'm using Java to implement this homework assignment as a choice of Programming Language.

The Spring Boot framework with `CommandLineRunner` is used to support the execution of the application.

I deliberately did not go the route of building the Data Layer and a DB for the sake of time and simplicity. 
Instead, I'm simply loading the data from the csv files into hash maps and collections to perform the lookup operations.

#### The `SlcspResolver` class
All the orchestrating logic is located in the `SlcspResolver` class. 
It calls all the necessary services (from the `service` layer of the application)
to perform the necessary calculations and returns the results to the caller (the `Main` class). 

#### Service layer
The Service layer contains the business logic 

#### Mappers
Mappers are the classes responsible for taking one format as an input and producing an output of in the desired different format.

#### The 'resources' Java Package
Classes that are responsible for holding data.

#### Spring Boot Application
`SlcspApplication` is the Spring Boot Application class

`Main` is the command line implementation class that is called by Spring Boot and the `run` method is an entry point.
It calls `SlcspApplication`, obtains the results and prints them out into `stdout`.
