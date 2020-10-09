#! /bin/bash

set -e
# set -x # turn this on to debug

cat ./.circleci/art.txt
./gradlew ktlintCheck test jar
