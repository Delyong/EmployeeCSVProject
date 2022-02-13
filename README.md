# EmployeeCSVProject

## Table of Contents
> - [Summary](#Summary)
> - [Dependencies](#Dependencies)
> - [How to Set up the Project](#How-to-set-up-the-project)
> - [How to Use the Project](#How-to-use-the-project)
> - [Branches and Phases](#Branches-and-Phases)
> - [Testing and Outcome](#Testing-and-Outcome)
> - [What is logged](#What-is-logged)
> - [Timings](#Timings)
> - [Project Management](#Project-Management)

## Summary
> This is an Employee CSV Project where users are able to enter a file name of their choice (In the console) 
> which they wish to read and write to the database 
> using `MySQL` queries whilst utilising `Buffered FileReader & Lambda expressions`. This will then display the
> number of corrupted records, number of employee records, number and list of duplicated records

> Users are also able to enter the number of `threads` they wish to use to execute the program at the start (In the console)

> Practices applied that are worth mentioning:
>> + MVC Project Structure
>> + Logging
>> + System Timings
>> + Project Management
>> + Version Control

## Dependencies
> + JUnit (For test cases)
> + MySQL (For Querying the database, both reading from and writing to)
> + Log4j2 (For logging user actions and system status)
```xml
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.17.1</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
        </dependency>
        <dependency>
            <groupId>commons-collections</groupId>
            <artifactId>commons-collections</artifactId>
            <version>3.2.1</version>
        </dependency>
    </dependencies>
```

## How to set up the project
> + Download the source code from this GitHub link
> + Open the project with an IDE of your choice
> + In the `pom.xml` file, insert the dependencies listed above
> + Refresh Maven
> + In the root folder, create `mysql.properties` file and write the following details to connect to the database
>> + dburl=
>> + dbuserid=
>> + dbpassword=
> + Create a `mylogfile.log` file in the root folder to log user actions and system status

## How to use the project
> Run `CSVMain` Java class and enter the number of threads that you wish to run the program with

![thread-prompt](https://cdn.discordapp.com/attachments/935470190127353868/941815873704960090/unknown.png)

> Enter the file name that you wish to be read and written to the database
> i.e. "EmployeeRecords.csv" | "EmployeeRecordsLarge.csv"

![filename](https://cdn.discordapp.com/attachments/935470190127353868/941816896137867354/unknown.png)

> Once you hit the start button, the following details will be printed:

![filedetails](https://cdn.discordapp.com/attachments/935470190127353868/941817637615317002/unknown.png)

> You can then refresh the `employeerecord` database in the link you provided in `mysql.properties`
> to see if the employee records has been inserted in the database.
> Once that's been done, transfer the view to the console to select query
> the Database, either printing all or specific records by EmployeeID
> There is also an option to exit the program if the user does not want to
> query the database

![single](https://cdn.discordapp.com/attachments/935470190127353868/942161876614271067/unknown.png)
![exit](https://cdn.discordapp.com/attachments/935470190127353868/942162539687604306/unknown.png)

> This last part of the program reruns until the exit option is selected

## Branches and Phases
> Phase 1: Initial Reading and Cleaning
>> This phase entailed reading data from a CSV file which involved using TDD on JUnit test cases
>
> Phase 2: Persist to Database
>> This phase entailed connecting the program to the database and querying the database
> 
> Phase 3: Add Multithreading
>> This phase entailed using the larger CSV file and utilising multithreading in order to
>> improve the program's efficiency in terms of time complexity
> 
> Phase 4: Add streams and Lambdas
>> This phase entailed using functional programming concepts, such as
>> lambda expressions and streams for reading the CSV file

## Testing and Outcome
> Automated tests:
>> + CSV Controller Test
>> + Employee Database Test
>> + Employee File Reader Test
>> + Employee Parser Test
>> + Employee Test

> Manual test:
>> + Display Manager
>> + CSV Main
>> + Database Connection
>> + Multithreading

> Outcome:
>> All manual and automated tests passed.
>> The program has met all the requirements and works as intended

## Timings

> Here are the following reading from and writing to the database timings,
> including the number of threads of:
>> + 1 thread
>> + 2 threads
>> + 4 threads
>> + 8 threads
>> + 64 threads
>> + 100 threads

![1thread](https://cdn.discordapp.com/attachments/935470190127353868/942384815976620062/unknown.png)

![2thread](https://cdn.discordapp.com/attachments/935470190127353868/942385028099346463/unknown.png)

![4thread](https://cdn.discordapp.com/attachments/935470190127353868/942385169392893962/unknown.png)

![8thread](https://cdn.discordapp.com/attachments/935470190127353868/942385917551857694/unknown.png)

![64thread](https://cdn.discordapp.com/attachments/935470190127353868/942385408803749968/unknown.png)

![100thread](https://cdn.discordapp.com/attachments/935470190127353868/942385615771697182/unknown.png)

> Conclusion: 8 threads was the fastest of them all in average

> Confirmation that threading is actually writing to the database:

![sqlcount](https://cdn.discordapp.com/attachments/935470190127353868/942391935824461844/unknown.png)

## Project Management

> Throughout the development of the project, the team has been keeping track of the project requirements
> and tasks on Trello by using lists and cards. Setting each card to the member who
> is the most confident on implementing the requirement
>> Before
![trello-before](https://cdn.discordapp.com/attachments/935470190127353868/942179699201806376/unknown.png)
>> After
![trello-after](https://cdn.discordapp.com/attachments/935470190127353868/942179569102889010/unknown.png)

## What is logged
> + Database status | including database exceptions
> + User actions | including invalid user inputs
> + Program status | including reading file exceptions
> + Information displayed to the user | including reading from and writing to database timings