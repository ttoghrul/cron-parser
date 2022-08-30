# Deliveroo Cron Parser task

A command line application which parses a cron string and expands each field
to show the times at which it will run.  
Project only considers the standard cron format with five time fields (minute, hour, day of
month, month, and day of week) plus a command, and you do not need to handle the special
time strings such as "@yearly".  
The input will be on a single line.

The cron string will be passed to the application as a single argument.
````
~$ your-program ＂*/15 0 1,15 * 1-5 /usr/bin/find＂
````

The output should be formatted as a table with the field name taking the first 14 columns and
the times as a space-separated list following it.
For example, the following input argument:
```
*/15 0 1,15 * 1-5 /usr/bin/find
```

Should yield the following output:

```
minute 0 15 30 45
hour 0
day of month 1 15
month 1 2 3 4 5 6 7 8 9 10 11 12
day of week 1 2 3 4 5
command /usr/bin/find
```

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Download and install Java 8

```
https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html
```

### Installing

To get project started one needs to run the script below in root folder of the project to build it

```
gradlew clean build
```

Jar package for the project could be found at *build/libs/* folder

## Running

Application could be run using command below
````
java -jar build/libs/cron-parser-<version>-SNAPSHOT.jar "*/15" 0 1,15 "*" 1-5 /usr/bin/find
````

Notes:
*   version is configurable in **build.gradle** file in root project folder (current version is **1.0-SNAPSHOT**)
*   When passing asterisk symbol as argument it needs to be surrounded by double-quotes (as in example above), as * is a shell wildcard
 
## Built With

* [Gradle](https://www.gradle.org/) - Gradle Building Tool
* [Java 8](https://www.java.com/en/) - Java Version 8

## Authors

* **Toghrul Taghiyev**
