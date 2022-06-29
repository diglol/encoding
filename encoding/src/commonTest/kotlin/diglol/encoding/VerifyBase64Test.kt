@file:OptIn(InternalApi::class)

package diglol.encoding

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class VerifyBase64Test {
  private val sample1 = Random.nextBytes(1024)

  @Test
  fun verifyBase64() {
    ignoreNpe {
      assertContentEquals(
        sample1.commonEncodeBase64(),
        sample1.encodeBase64(),
      )
      assertEquals(
        sample1.commonEncodeBase64().decodeToString(),
        sample1.encodeBase64ToString()
      )

      assertContentEquals(
        sample1.commonEncodeBase64(),
        sample1.encodeBase64ToString().encodeToByteArray()
      )

      assertContentEquals(sample1.commonEncodeBase64(), sample1.encodeBase64())
      assertContentEquals(sample1.commonEncodeBase64(), sample1.encodeBase64())

      assertContentEquals(sample1, sample1.commonEncodeBase64().decodeBase64())
      assertContentEquals(sample1, sample1.encodeBase64().commonDecodeBase64())
    }
  }
}
