package diglol.encoding

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Base45Test {

  private val sample = mapOf(
    "AB" to "BB8",
    "Hello!!" to "%69 VD92EX0",
    "base-45" to "UJCLQE7W581",
  )

  private val sampleBytes = sample.map { (origin, encoded) ->
    origin.encodeToByteArray() to encoded.encodeToByteArray()
  }.toMap()

  private val decodeSample = mapOf(
    "QED8WEX0" to "ietf!"
  )

  @Test
  fun testEncodeBase45() {
    sampleBytes.forEach { (origin, expectedEncoded) ->
      ignoreNpe {
        assertContentEquals(expectedEncoded, origin.encodeBase45())
      }
    }
  }

  @Test
  fun testEncodedBase45String() {
    sample.forEach { (origin, expected) ->
      ignoreNpe {
        assertEquals(expected, origin.encodeToByteArray().encodeBase45ToString())
      }
    }
  }
}
