package diglol.encoding

import kotlin.test.Test
import kotlin.test.assertContentEquals
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

  private val base32SampleBytes = base32Samples.map { (key, value) ->
    key.encodeToByteArray() to value.encodeToByteArray()
  }

  private val base32HexSamples = mapOf(
    "" to "", // RFC 4648 examples
    "f" to "CO======",
    "fo" to "CPNG====",
    "foo" to "CPNMU===",
    "foob" to "CPNMUOG=",
    "fooba" to "CPNMUOJ1",
    "foobar" to "CPNMUOJ1E8======",
  )

  private val base32HexSampleBytes = base32HexSamples.map { (key, value) ->
    key.encodeToByteArray() to value.encodeToByteArray()
  }

  @Test fun encodeBase32() {
    base32SampleBytes.forEach { (key, value) ->
      assertContentEquals(value, key.encodeBase32())
    }
  }

  @Test fun decodeBase32() {
    base32SampleBytes.forEach { (key, value) ->
      assertContentEquals(key, value.decodeBase32())
    }
  }

  @Test fun encodeBase32ToString() {
    base32Samples.forEach { (key, value) ->
      assertEquals(value, key.encodeToByteArray().encodeBase32ToString())
    }
  }

  @Test fun decodeBase32ToBytes() {
    base32Samples.forEach { (key, value) ->
      assertEquals(key, value.decodeBase32ToBytes()?.decodeToString())
    }
    assertNotNull("CPNMUOJ ".decodeBase32ToBytes())
    assertNull("CPNMUOJ0".decodeBase32ToBytes())
  }

  @Test fun encodeBase32Hex() {
    base32HexSampleBytes.forEach { (key, value) ->
      assertContentEquals(value, key.encodeBase32Hex())
    }
  }

  @Test fun decodeBase32Hex() {
    base32HexSampleBytes.forEach { (key, value) ->
      assertContentEquals(key, value.decodeBase32Hex())
    }
  }

  @Test fun encodeBase32HexToString() {
    base32HexSamples.forEach { (key, value) ->
      assertEquals(value, key.encodeToByteArray().encodeBase32HexToString())
    }
  }

  @Test fun decodeBase32HexToBytes() {
    base32HexSamples.forEach { (key, value) ->
      assertEquals(key, value.decodeBase32HexToBytes()?.decodeToString())
    }
    assertNotNull("CPNMUOJ ".decodeBase32HexToBytes())
    assertNull("CPNMUOJZ".decodeBase32HexToBytes())
  }
}
