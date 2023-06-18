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
    """the quick brown fox jumps over the lazy dog THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG 1234567890-=[];',./\!@#$%^&*()_+{}|:"<>?""" to
      """AWEDZCKFEOEDJOD2KC54EM-DX.CH8FSKDQ${'$'}D.OE44E5${'$'}CS44+8DK44OEC3EFGVCU1DLTABX8VCAZB9HM9DH8G1AK*9:*8F6B*H9${'$'}Y9+MAF1AGY8-34669X34ZB81CBRS8S:8*96DL6WW66:6FA7GW5YOBNL7FQ5J:5794-J4QW45${'$'}4L35I1CNRFWVFYE45*7""",
  )

  private val sampleBytes = sample.map { (origin, encoded) ->
    origin.encodeToByteArray() to encoded.encodeToByteArray()
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
        assertContentEquals(expectedOrigin, encoded.decodeBase45())
      }
    }
  }

  @Test
  fun testDecodeBase45ToBytes() {
    sample.forEach { (expectedOrigin, encoded) ->
      ignoreNpe {
        assertContentEquals(expectedOrigin.encodeToByteArray(), encoded.decodeBase45ToBytes())
      }
    }
  }

  @Test
  fun testInvalidDecodeBase45() {
    invalidSample.forEach { (encoded, expectedOrigin) ->
      ignoreNpe {
        assertContentEquals(expectedOrigin, encoded.decodeBase45ToBytes())
      }
    }
  }
}
