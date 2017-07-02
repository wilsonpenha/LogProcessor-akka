ActorSystem by AKKA API and Java

This ActorSystem code is to read a logs directory from filesystem, such as /tmp/app/logs

The FileScanner Actor receives a Scan message, and process all files in a given directory,

The FileParser Actor receives the scanned file as Parse message, then read the file and send three event messages to the Aggregator (Start, Line and End)

The Aggregator Actor receives the three event messages, from the FileParser actor such as Start, Line and End, then process it accordingly with the requirements:
	When receives Start event, just log the aggregator initiate.  
	When receives Line event, take a word count for each line and sum it for further need.
	When receives End event, print the words count for the file.
	
When all files get processed, finish the ActorSystem instance
 
Technology used in this project

JDK 1.8
Maven 4.0
Akka API 2.5.3
Log4j 1.2.17

how to deploy/execute:

1. setup your Maven repository and application
2. download and extract the LogProcessor-akka project
3. then go to the project folder LogProcessor-akka
4. run mvn clean package -> this will compile and build the project
5. to run the application do the follow:
   java -jar target/LogProcessor-akka-0.0.1-executable.jar src/test/logs -> the expected results should be similar to the below outputs,

20:29:04.737 INFO  [main][com.m800.akka.actors.FileScanner] Initiating the ActorSystem 'logProcessor'

20:29:05.826 INFO  [main][com.m800.akka.actors.FileScanner] Sending the path : src/test/logs to be scan by FileScanner actor!

20:29:05.828 INFO  [logProcessor-akka.actor.default-dispatcher-6][com.m800.akka.actors.FileScanner] Scanning files into directory :src\test\logs

20:29:05.838 INFO  [logProcessor-akka.actor.default-dispatcher-3][com.m800.akka.actors.FileParser] Parsing file from directory :src\test\logs\test1.log

20:29:05.839 INFO  [logProcessor-akka.actor.default-dispatcher-5][com.m800.akka.actors.FileParser] Parsing file from directory :src\test\logs\test3.log

20:29:05.839 INFO  [logProcessor-akka.actor.default-dispatcher-2][com.m800.akka.actors.FileParser] Parsing file from directory :src\test\logs\test2.log

20:29:05.841 INFO  [logProcessor-akka.actor.default-dispatcher-4][com.m800.akka.actors.FileParser] Parsing file from directory :src\test\logs\test4.log

Words count of file : src\test\logs\test1.log is :13

Words count of file : src\test\logs\test2.log is :26

Words count of file : src\test\logs\test3.log is :65

Words count of file : src\test\logs\test4.log is :117


Thanks, and enjoy!

Wilson Mendonca da Penha Junior

   
   
   
