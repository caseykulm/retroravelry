import com.caseykulm.retroravelry.gradle.Deps

dependencies {
  implementation(Deps.Kotlin.core)
  implementation(Deps.Kotlin.reflect)
  implementation(Deps.Moshi.core)
  implementation(Deps.Moshi.kotlin)
  implementation(Deps.Moshi.adapters)
  implementation(Deps.OkHttp3.core)
  implementation(Deps.OkHttp3.loggingInterceptor)
  implementation(Deps.Retrofit.converterMoshi)
  implementation(Deps.Retrofit.core)
  implementation(Deps.Retrofit.rxJava2Adapter)
  implementation(Deps.rxjava2)

  testImplementation(Deps.junit4)
  testImplementation(Deps.mockito)
  testImplementation(Deps.OkHttp3.mockWebServer)
}
