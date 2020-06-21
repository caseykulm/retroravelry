# Releasing

TODO: Notes about setting up keys

We use the JCenter Bintray Gradle plugin which has an option to auto-release to Maven Central as well which we are using. So in order to release to both JCenter and Maven Central run:

```
./gradlew bintrayUpload
```

To test locally with Maven Local run:

```
./gradlew publishToMavenLocal
```

