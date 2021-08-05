#!/bin/sh

# pre-conditions
# 1. maven installed and on PATH
# 2. maven project is built (build jar exists in current directory, target subdirectory
# 3. this script is executable
# 4. current directory is on PATH (or run with ./isc-schedule.sh)

# Script isc-schedule
# dump the maven dependencies to classpath file
mvn -Dmdep.outputFile=classpath.out dependency:build-classpath

# set java classpath from maven dependency classpath
SCRIPT_CLASSPATH=`cat classpath.out` 
echo "isc-schedule classpath = " $SCRIPT_CLASSPATH

# add project classpath
SCRIPT_CLASSPATH=$SCRIPT_CLASSPATH:./target/isc-schedule-0.1.0.jar
echo "full isc-schedule classpath = " $SCRIPT_CLASSPATH

# remove the temp classpath file
rm classpath.out

# run the program
java -cp $SCRIPT_CLASSPATH org.issaquahsoccerclub.app.ISCTournamentScheduleMaker $@
