#!/bin/bash
sleep ${DELAY:-10}
/usr/local/openjdk-8/bin/java -jar /tmp/SeleniumProjectGradle-1.0-SNAPSHOT.jar /tmp/$1 $2