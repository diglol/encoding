package diglol.encoding

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class Base64Test {
  private val samples = mapOf(
    "" to "", // RFC 4648 examples
    "f" to "Zg==",
    "fo" to "Zm8=",
    "foo" to "Zm9v",
    "foob" to "Zm9vYg==",
    "fooba" to "Zm9vYmE=",
    "foobar" to "Zm9vYmFy",
  )

  private val sampleBytes = samples.map { (key, value) ->
    key.encodeToByteArray() to value.encodeToByteArray()
  }

  @Test fun encodeBase64() {
    sampleBytes.forEach { (key, value) ->
      assertContentEquals(value, key.encodeBase64())
    }
  }

  @Test fun decodeBase64() {
    sampleBytes.forEach { (key, value) ->
      assertContentEquals(key, value.decodeBase64())
    }
  }

  @Test fun encodeBase64ToString() {
    samples.forEach { (key, value) ->
      assertEquals(value, key.encodeToByteArray().encodeBase64ToString())
    }
  }

  @Test fun decodeBase64ToBytes() {
    samples.forEach { (key, value) ->
      assertEquals(key, value.decodeBase64ToBytes()?.decodeToString())
    }
    assertNotNull("Zm9vYmF ".decodeBase64ToBytes())
    assertNotNull("Zm9vYm+/".decodeBase64ToBytes())
    assertNull("Zm9vYm-_".decodeBase64ToBytes())
    assertNull("Zm9vYm**".decodeBase64ToBytes())
  }

  @Test fun encodeBase64Url() {
    sampleBytes.forEach { (key, value) ->
      assertContentEquals(value, key.encodeBase64Url())
    }
  }

  @Test fun decodeBase64Url() {
    sampleBytes.forEach { (key, value) ->
      assertContentEquals(key, value.decodeBase64Url())
    }
  }

  @Test fun encodeBase64UrlToString() {
    samples.forEach { (key, value) ->
      assertEquals(value, key.encodeToByteArray().encodeBase64UrlToString())
    }
  }

  @Test fun decodeBase64UrlToBytes() {
    samples.forEach { (key, value) ->
      assertEquals(key, value.decodeBase64UrlToBytes()?.decodeToString())
    }
    assertNotNull("Zm9vYmF ".decodeBase64UrlToBytes())
    assertNotNull("Zm9vYm-_".decodeBase64UrlToBytes())
    assertNull("Zm9vYm+/".decodeBase64UrlToBytes())
    assertNull("Zm9vYm**".decodeBase64UrlToBytes())
  }
}
