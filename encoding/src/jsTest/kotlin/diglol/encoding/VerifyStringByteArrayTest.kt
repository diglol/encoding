@file:OptIn(InternalApi::class)

package diglol.encoding

import diglol.encoding.internal.jsDecodeToString
import diglol.encoding.internal.jsEncodeToByteArray
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class VerifyStringByteArrayTest {
  @Test
  fun verifyStringByteArray() {
    val sample1 = Random.nextBytes(1024)
    val sample1String = sample1.decodeToString()

    assertContentEquals(
      sample1String.encodeToByteArray(),
      sample1String.jsEncodeToByteArray()
    )
    assertEquals(
      sample1String.encodeToByteArray().jsDecodeToString(),
      sample1String.jsEncodeToByteArray().decodeToString()
    )
  }
}
