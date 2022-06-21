package diglol.encoding

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class Base32Test {
  private val base32Samples = mapOf(
    "" to "", // RFC 4648 examples
    "f" to "MY======",
    "fo" to "MZXQ====",
    "foo" to "MZXW6===",
    "foob" to "MZXW6YQ=",
    "fooba" to "MZXW6YTB",
    "foobar" to "MZXW6YTBOI======",
  )

  private val base32HexSamples = mapOf(
    "" to "", // RFC 4648 examples
    "f" to "CO======",
    "fo" to "CPNG====",
    "foo" to "CPNMU===",
    "foob" to "CPNMUOG=",
    "fooba" to "CPNMUOJ1",
    "foobar" to "CPNMUOJ1E8======",
  )

  @Test fun testEncodeBase32() {
    base32Samples.forEach { (key, value) ->
      assertEquals(value, key.encodeToByteArray().encodeBase32())
    }
  }

  @Test fun testDecodeBase32() {
    base32Samples.forEach { (key, value) ->
      assertEquals(key, value.decodeBase32()?.decodeToString())
    }
    assertNotNull("CPNMUOJ ".decodeBase32())
    assertNull("CPNMUOJ+".decodeBase32())
  }

  @Test fun testEncodeBase32Hex() {
    base32HexSamples.forEach { (key, value) ->
      assertEquals(value, key.encodeToByteArray().encodeBase32Hex())
    }
  }

  @Test fun testDecodeBase32Hex() {
    base32HexSamples.forEach { (key, value) ->
      assertEquals(key, value.decodeBase32Hex()?.decodeToString())
    }
    assertNotNull("CPNMUOJ ".decodeBase32())
    assertNull("CPNMUOJ+".decodeBase32())
  }
}
