package diglol.encoding

import kotlin.test.Test
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

  @Test fun testEncodeBase64() {
    samples.forEach { (key, value) ->
      assertEquals(value, key.encodeToByteArray().encodeBase64())
    }
  }

  @Test fun testDecodeBase64() {
    samples.forEach { (key, value) ->
      assertEquals(key, value.decodeBase64()?.decodeToString())
    }
    assertNotNull("Zm9vYmF ".decodeBase64())
    assertNotNull("Zm9vYm+/".decodeBase64())
    assertNull("Zm9vYm-_".decodeBase64())
    assertNull("Zm9vYm**".decodeBase64())
  }

  @Test fun testEncodeBase64Url() {
    samples.forEach { (key, value) ->
      assertEquals(value, key.encodeToByteArray().encodeBase64Url())
    }
  }

  @Test fun testDecodeBase64Url() {
    samples.forEach { (key, value) ->
      assertEquals(key, value.decodeBase64Url()?.decodeToString())
    }
    assertNotNull("Zm9vYmF ".decodeBase64Url())
    assertNotNull("Zm9vYm-_".decodeBase64Url())
    assertNull("Zm9vYm+/".decodeBase64Url())
    assertNull("Zm9vYm**".decodeBase64Url())
  }
}
