# EmployeeCSVProject

## Table of Contents
> - [Summary](#Summary)
> - [Dependencies](#Dependencies)
> - [How to Set up the Project](#How-to-set-up-the-project)
> - [How to Use the Project](#How-to-use-the-project)
> - [Branches and Phases](#Branches-and-Phases)
> - [Testing and Outcome](#Testing-and-Outcome)
> - [Timings](#Timings)
> - [Project Management](#Project-Management)
> - [What is logged](#What-is-logged)



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
>> + Singleton Database Connection (ConnectionFactory class)
>> + Database Access Object (EmployeeDatabase class)
>> + Multithreading (InsertEmployeeThread class)

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
> + In the `pom.xml` file, refresh Maven if needed.
> + In the root folder, create `mysql.properties` file and write the following with details to connect to your own mySQL database:
>> + dburl=
>> + dbuserid=
>> + dbpassword=
> + Run CSVMain and enjoy!

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
>> This phase entailed reading data from a CSV file which involved using TDD on JUnit test cases.
>
> Phase 2: Persist to Database
>> This phase entailed connecting the program to the database and querying the database.
> 
> Phase 3: Add Multithreading
>> This phase entailed using the larger CSV file and utilising multithreading in order to
>> improve the program's efficiency in terms of time complexity.
> 
> Phase 4: Add streams and Lambdas
>> This phase entailed using functional programming concepts, such as
>> lambda expressions and streams for reading the CSV file.

## Testing and Outcome
> Automated tests:
>> + CSVControllerTest
>> + EmployeeDatabaseTest
>> + EmployeeFileReaderTest
>> + EmployeeParserTest
>> + EmployeeTest

> Manual test:
>> + DisplayManager
>> + CSVMain
>> + Database Connection (Connection Factory)
>> + Multithreading (Insert Employee Thread)

> Outcome:
>> All manual and automated tests passed.
>> The program has met all the requirements and works as intended.
>>
>> ![Csvcontrollertest](https://user-images.githubusercontent.com/48356710/153762843-bfaa9098-b5bc-4cc8-87cd-9d85e991d4ca.png)
>>
>> ![employeedbtests](https://user-images.githubusercontent.com/48356710/153762844-445f0137-41b4-4455-8f3c-9033277fe0fb.png)
>> 
>> ![employeefilerecordtest](https://user-images.githubusercontent.com/48356710/153762845-8176c40f-f9f5-40f6-b242-15df36d06be9.png)
>>
>> ![employeeparse](https://user-images.githubusercontent.com/48356710/153762840-cec5dd23-8a3a-4d85-903a-55498a29a55e.png)
>>
>> ![employeetests](https://user-images.githubusercontent.com/48356710/153762842-c06adbde-8090-4fbe-8689-bd4e44094577.png)

## Timings

> General Performance:
> 
>> During the process of development we started writing at around 3 minutes for 65,499 records.
>> After implementing multithreading this time went down to around 30 seconds. After refactoring
>> the multithreading we managed around 10-20 seconds. 
>> Then finally after combining multiple SQL inserts we managed around 1-2 seconds.
>> 
>> We were very happy as at team at this write speed. We did the most we could to make 
>> the application write as quick as possible. The only other idea we had for improving
>> the applications was improving the hardware it ran on.
>
> Optimal Thread Count:
>
>> Here are the following reading from and writing to the database timings,
>> including the number of threads of:
>> + 1 thread
>> + 2 threads
>> + 4 threads
>> + 8 threads
>> + 64 threads
>> + 100 threads
>>
>> ![1thread](https://cdn.discordapp.com/attachments/935470190127353868/942384815976620062/unknown.png)
>>
>> ![2thread](https://cdn.discordapp.com/attachments/935470190127353868/942385028099346463/unknown.png)
>>
>> ![4thread](https://cdn.discordapp.com/attachments/935470190127353868/942385169392893962/unknown.png)
>>
>> ![8thread](https://cdn.discordapp.com/attachments/935470190127353868/942385917551857694/unknown.png)
>>
>> ![64thread](https://cdn.discordapp.com/attachments/935470190127353868/942385408803749968/unknown.png)
>>
>> ![100thread](https://cdn.discordapp.com/attachments/935470190127353868/942385615771697182/unknown.png)
>
> Optimal Thread Count Conclusion:
> 
>> After 8 threads the performance dropped noticeably. Before 8 performance was similar however, overall 8 threads was the fastest of them all.
>>
>> Confirmation that threading is actually writing to the database:
>>
>>![sqlcount](https://cdn.discordapp.com/attachments/935470190127353868/942391935824461844/unknown.png)

## Project Management

> Throughout the development of the project, the team has been keeping track of the project requirements
> and tasks on Trello by using lists and cards. Setting each card to the member who
> is the most confident on implementing the requirement
>> Before
![trello-before](https://cdn.discordapp.com/attachments/935470190127353868/942179699201806376/unknown.png)
>> After
![trello-after](https://cdn.discordapp.com/attachments/935470190127353868/942179569102889010/unknown.png)

## Logging
> The levels of logging utilized where debug, info, warn, error, fatal.
> 
> Examples of where logging levels are used:
> 
>> + Debug - logs when the database connection is null and needs creating.
>> + Info - logs when trying to get the database connection.
>> + Warn - logs when the user input an invalid option.
>> + Error - logs when the file couldn't be read or path was invalid.
>> + Fatal - logs when the database table couldn't be created.
> 
> You can find more examples throughout the program :)

![GitCloneImage](https://user-images.githubusercontent.com/48356710/153765447-c5a5538d-3732-49d5-a227-06d4d117110f.png)
![OpenProjectImage](https://user-images.githubusercontent.com/48356710/153765449-280241da-cc21-424d-8fce-f96119b02262.png)
![OpenProjectLocationImage](https://user-images.githubusercontent.com/48356710/153765450-f84933c2-4d42-4f09-91c6-77b2c45bbc50.png)
![CreateMysqlProperties](https://user-images.githubusercontent.com/48356710/153765451-7d2fcb1e-ecb2-4011-b209-853ecf0e7cb9.png)
![NameTheFileImage](https://user-images.githubusercontent.com/48356710/153765453-7ff6cc41-2223-416d-970f-b17e3d2de9a7.png)
![MySqlDetailsImage](https://user-images.githubusercontent.com/48356710/153765454-5a7bfc0f-59b4-4dbd-a79b-581f214ec7a7.png)
![CodeButtonImage](https://user-images.githubusercontent.com/48356710/153765457-2be6978a-fe0b-4391-817a-3b19fd3eff89.png)
![CopyButtonImage](https://user-images.githubusercontent.com/48356710/153765459-763745e0-73b9-47e5-924d-6077921928e5.png)
![CSVMainRunImage](https://user-images.githubusercontent.com/48356710/153766035-61fab0c3-f974-4285-8798-b27e449514b9.png)


