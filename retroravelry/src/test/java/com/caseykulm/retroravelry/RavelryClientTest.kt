package com.caseykulm.retroravelry

import org.junit.Assert.assertNotNull
import org.junit.Test

class RavelryClientTest {
  val ravelryClient = LiveClient().ravelryClient

  @Test
  fun getMyLibraryShouldNotBeNull() {
    val libResp = ravelryClient.getMyDefaultLibrary("added", 0, 20).execute().body()
    println(libResp)
    assertNotNull(libResp)
  }
}