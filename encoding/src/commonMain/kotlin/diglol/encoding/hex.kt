package diglol.encoding

import kotlin.native.concurrent.SharedImmutable

// https://datatracker.ietf.org/doc/html/rfc4648
@SharedImmutable
private val HEX = byteArrayOf(
  48, 49, 50, 51, 52, 53, 54, 55, 56, 57, // 0123456789
  65, 66, 67, 68, 69, 70 // ABCDEF
)

fun ByteArray.encodeHex(): String {
  val out = ByteArray(size * 2)
  var i = 0
  for (b in this) {
    val b1 = b.toInt() and 0xff
    out[i++] = HEX[b1 shr 4 and 0xf]
    out[i++] = HEX[b1 and 0xf]
  }
  return out.decodeToString()
}

fun String.decodeHex(): ByteArray? {
  if (length % 2 != 0) {
    return null
  }

  val out = ByteArray(length / 2)
  for (i in out.indices) {
    val d1 = decodeToInt(this[i * 2])?.shl(4) ?: return null
    val d2 = decodeToInt(this[i * 2 + 1]) ?: return null
    out[i] = (d1 + d2).toByte()
  }

  return out
}

private fun decodeToInt(c: Char): Int? {
  return when (c) {
    in '0'..'9' -> c - '0'
    in 'a'..'f' -> c - 'a' + 10
    in 'A'..'F' -> c - 'A' + 10
    else -> null
  }
}
