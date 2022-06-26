package diglol.encoding

import kotlin.test.Test
import kotlin.test.assertContentEquals
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

  private val sampleBytes = samples.map { (key, value) ->
    key.encodeToByteArray() to value.encodeToByteArray()
  }

  @Test fun encodeHex() {
    sampleBytes.forEach { (key, value) ->
      assertContentEquals(value, key.encodeHex())
    }
  }

  @Test fun decodeHex() {
    sampleBytes.forEach { (key, value) ->
      assertContentEquals(key, value.decodeHex())
    }
  }

  @Test fun encodeHexToString() {
    samples.forEach { (key, value) ->
      assertEquals(value, key.encodeToByteArray().encodeHexToString())
    }
  }

  @Test fun decodeHexToBytes() {
    samples.forEach { (key, value) ->
      assertEquals(key, value.decodeHexToBytes()?.decodeToString())
    }
    assertNull("666F6F62617G".decodeHexToBytes())
  }
}
