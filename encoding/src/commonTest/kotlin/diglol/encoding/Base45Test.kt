package diglol.encoding

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Base45Test {

  private val sample = mapOf(
    "AB" to "BB8",
    "Hello!!" to "%69 VD92EX0",
    "base-45" to "UJCLQE7W581",
    "ietf!" to "QED8WEX0",
    "CQ CQ CQ DE BG8ANC" to "/M8O34NBA/M8P34BX82H8157A+9",
  )

  private val sampleBytes = sample.map { (origin, encoded) ->
    origin.encodeToByteArray() to encoded?.encodeToByteArray()
  }.toMap()


  private val invalidSample = mapOf(
    "|~{}" to null,
    0x0.toChar().toString() to null,
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

  @Test
  fun testDecodeBase45() {
    sampleBytes.forEach { (expectedOrigin, encoded) ->
      ignoreNpe {
        assertContentEquals(expectedOrigin, encoded?.decodeBase45())
      }
    }
  }

  @Test
  fun testDecodeBase45ToString() {
    sample.forEach { (expectedOrigin, encoded) ->
      ignoreNpe {
        assertEquals(expectedOrigin, encoded?.encodeToByteArray()?.decodeBase45ToString())
      }
    }
  }

  @Test
  fun testInvalidDecodeBase45() {
    invalidSample.forEach { (encoded, expectedOrigin) ->
      ignoreNpe {
        assertContentEquals(expectedOrigin, encoded.encodeToByteArray().decodeBase45())
      }
    }
  }
}
