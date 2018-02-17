# Architecture

## Handling Errors

There are different types of errors, and we would like to abstract them away. 

```kotlin
enum class ErrorType {
  SERVER, 
  NO_NETWORK, 
}
```

```kotlin
enum class HttpCode {
  SUCCESS, 
  UNAUTHORIZED, 
  CLIENT_ERROR, 
  SERVER_ERROR  
}
``` 

```kotlin
interface ErrorData {
  fun getDeveloperMessage()
}
```

```kotlin
interface NetworkApi {
  fun fetchData(): Result<Body, Error>
}
```