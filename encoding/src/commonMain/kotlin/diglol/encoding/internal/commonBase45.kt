package diglol.encoding.internal

import kotlin.native.concurrent.SharedImmutable


@SharedImmutable
internal val BASE45 = byteArrayOf(
  48, 49, 50, 51, 52, 53, 54, 55, 56, 57, // 0-9
  65, 66, 67, 68, 69, 70, 71, 72, 73, 74, // A-J
  75, 76, 77, 78, 79, 80, 81, 82, 83, 84, // K-T
  85, 86, 87, 88, 89, 90, // U-Z
  // SP, $, %, *, +, -, ., /, :
  32, 36, 37, 42, 43, 45, 46, 47, 58
)


internal fun ByteArray.commonEncodeBase45(map: ByteArray = BASE45): ByteArray {
  val outSize = sizeOfEncodedBase45()
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

    outBytes[j] = map[c]
    outBytes[j + 1] = map[d]
    outBytes[j + 2] = map[e]

    i += 2
    j += 3
  }

  if (limit < size) {
    val a = this[limit].toInt() and 0xff
    val c = a % 45
    val d = (a / 45) % 45
    outBytes[j] = map[c]
    outBytes[j + 1] = map[d]
  }

  return outBytes
}

private fun ByteArray.sizeOfEncodedBase45(): Int {
  if (isEmpty()) {
    return 0
  }

  return if (size % 2 == 0) {
    size * 3 / 2
  } else {
    (size - 1) * 3 / 2 + 2
  }
}

