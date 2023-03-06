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

