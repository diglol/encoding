package diglol.encoding

import kotlin.native.concurrent.SharedImmutable

// https://datatracker.ietf.org/doc/html/rfc4648
@SharedImmutable
internal val HEX = byteArrayOf(
  48, 49, 50, 51, 52, 53, 54, 55, 56, 57, // 0123456789
  65, 66, 67, 68, 69, 70 // ABCDEF
)

internal fun ByteArray.commonEncodeHex(): ByteArray {
  val out = ByteArray(size * 2)
  var i = 0

  for (b in this) {
    val b1 = b.toInt()
    out[i++] = HEX[b1 and 0xf0 ushr 4]
    out[i++] = HEX[b1 and 0xf]
  }
  return out
}

internal fun ByteArray.commonDecodeHex(): ByteArray? {
  val limit = sizeOfIgnoreTrailing()
  if (limit == 0) {
    return ByteArray(0)
  }

  val out = ByteArray(size / 2)
  for (i in out.indices) {
    val d1 = this[i * 2].decodeToInt()?.shl(4) ?: return null
    val d2 = this[i * 2 + 1].decodeToInt() ?: return null
    out[i] = (d1 + d2).toByte()
  }

  return out
}

@Suppress("NOTHING_TO_INLINE")
private inline fun Byte.decodeToInt(): Int? {
  return when (this) {
    in '0'.code..'9'.code -> this - '0'.code
    in 'a'.code..'f'.code -> this - 'a'.code + 10
    in 'A'.code..'F'.code -> this - 'A'.code + 10
    else -> null
  }
}
