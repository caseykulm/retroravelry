# Retro-Ravelry

[![Maven Central](https://img.shields.io/maven-central/v/com.caseykulm.retroravelry/retroravelry.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.caseykulm.retroravelry%22%20AND%20a:%22retroravelry%22)

Kotlin wrapper for the [Ravelry API][1] using [Retrofit][2] and [Kotlin Coroutines][3].

## Download 

With Gradle:

```kotlin
dependencies {
    implementation("com.caseykulm.retroravelry:retroravelry:0.11.0")
}
```

## License

    Copyright 2016 Casey Kulm
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: http://www.ravelry.com/groups/ravelry-api
[2]: https://github.com/square/retrofit
[3]: https://kotlinlang.org/docs/reference/coroutines-overview.html

## Setup

1. Get the `secrets.properties` file from 1Password.
2. Add it to the root of the project

## Checks

### Quick start

Run the following to mimic what CI is running:

`./.circleci/checks.sh`

Note: Make sure you mark this file executable with `chmod +x ./.circleci/checks.sh`

### More info

We are running our CI on Circle CI with the following checks:

* ktlint
* unit tests
* build a JAR successfully

### ktlint

Ktlint may report errors. If it does you can run:

`./gradlew ktlintFormat`

to attempt to fix it. It can fix most things, but sometimes it will tell you that 
you need to resolve something by hand.

## Browsing API

There is a [ravelry_postman_collection.json](./ravelry_postman_collection.json) file available 
at the root of this repo that can be imported into the [Postman](https://www.postman.com/) 
application to browse the REST API via a collection. This in turn can be used to generate an 
OAuth2 access token which you can use in this project to test your changes. 