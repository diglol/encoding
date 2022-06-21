package diglol.encoding

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class HexTest {
  private val samples = mapOf(
    "" to "", // RFC 4648 examples
    "f" to "66",
    "fo" to "666F",
    "foo" to "666F6F",
    "foob" to "666F6F62",
    "fooba" to "666F6F6261",
    "foobar" to "666F6F626172",
  )

  @Test fun testEncodeHex() {
    samples.forEach { (key, value) ->
      assertEquals(value, key.encodeToByteArray().encodeHex())
    }
  }

  @Test fun testDecodeHex() {
    samples.forEach { (key, value) ->
      assertEquals(key, value.decodeHex()?.decodeToString())
    }
    assertNull("666F6F62617G".decodeBase32())
  }
}
