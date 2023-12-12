package diglol.encoding.internal

/**
 * Implementation of Base45 encoding/decoding.
 *
 * Basically, Base45 is just encode 2 bytes based on 256 into 3 bytes based on 45.
 *
 * It will result in a encoded string which will generate a compat QR code when using alphanumeric mode.
 *
 * @see [RFC 9285](https://datatracker.ietf.org/doc/rfc9285/)
 */

private val BASE45 = byteArrayOf(
  48, 49, 50, 51, 52, 53, 54, 55, 56, 57, // 0-9
  65, 66, 67, 68, 69, 70, 71, 72, 73, 74, // A-J
  75, 76, 77, 78, 79, 80, 81, 82, 83, 84, // K-T
  85, 86, 87, 88, 89, 90, // U-Z
  // SP, $, %, *, +, -, ., /, :
  32, 36, 37, 42, 43, 45, 46, 47, 58
)

internal fun ByteArray.commonEncodeBase45(): ByteArray {
  val outSize = when {
    isEmpty() -> 0
    size % 2 == 0 -> size * 3 / 2
    else -> (size - 1) * 3 / 2 + 2
  }
  val outBytes = ByteArray(outSize)
  val limit = if (size % 2 == 0) size else size - 1

  var i = 0
  var j = 0

  while (i < limit) {
    val a = this[i].toInt() and 0xff
    val b = this[i + 1].toInt() and 0xff

    val n = (a shl 8) + b

    val c = n % 45
    val d = (n / 45) % 45
    val e = ((n / 45) / 45) % 45

    outBytes[j] = BASE45[c]
    outBytes[j + 1] = BASE45[d]
    outBytes[j + 2] = BASE45[e]

    i += 2
    j += 3
  }

  if (limit < size) {
    val a = this[limit].toInt() and 0xff
    val c = a % 45
    val d = (a / 45) % 45
    outBytes[j] = BASE45[c]
    outBytes[j + 1] = BASE45[d]
  }

  return outBytes
}

internal fun ByteArray.commonDecodeBase45(): ByteArray? {
  if (size % 3 == 1) {
    return null
  }

  val limit = if (size % 3 == 0) size else size - 2
  val outSize = if (size % 3 == 0) size / 3 * 2 else (size - 2) / 3 * 2 + 1

  val outBytes = ByteArray(outSize)

  var i = 0
  var j = 0

  while (i < limit) {

    val c = this[i].toBase45Code() ?: return null
    val d = this[i + 1].toBase45Code() ?: return null
    val e = this[i + 2].toBase45Code() ?: return null

    val n = c + d * 45 + e * 45 * 45

    val a = n ushr 8
    val b = n and 0xff

    outBytes[j] = a.toByte()
    outBytes[j + 1] = b.toByte()

    i += 3
    j += 2
  }

  if (limit < size) {
    val c = this[limit].toBase45Code() ?: return null
    val d = this[limit + 1].toBase45Code() ?: return null
    val a = c + d * 45
    outBytes[j] = a.toByte()
  }

  return outBytes
}

/**
 * Converts a char's byte code to a Base45 lookup table index.
 */
private fun Byte.toBase45Code(): Int? {
  return when (this.toInt() and 0xff) {
    32 -> 36
    58 -> 44
    36, 37 -> this + 1
    42, 43 -> this - 3
    45, 46, 47 -> this - 4
    in '0'.code..'9'.code -> this - '0'.code
    in 'A'.code..'Z'.code -> this - 'A'.code + 10
    else -> null
  }
}
