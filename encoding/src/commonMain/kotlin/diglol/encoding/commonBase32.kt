package diglol.encoding

import kotlin.native.concurrent.SharedImmutable

// https://datatracker.ietf.org/doc/html/rfc4648
@SharedImmutable
internal val BASE32 = byteArrayOf(
  65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, // ABCDEFGHIJKLM
  78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, // NOPQRSTUVWXYZ
  50, 51, 52, 53, 54, 55 // 234567
)

@SharedImmutable
internal val BASE32_LOOKUP = intArrayOf(
  255, 255, 26, 27, 28, 29, 30, 31, 255, 255, 255, 255, 255, 255, 255, 255, // 0123456789:;<=>?
  255, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, // @ABCDEFGHIJKLMNO
  15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 255, 255, 255, 255, 255, // PQRSTUVWXYZ[\]^_
  255, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, // `abcdefghijklmno
  15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 255, 255, 255, 255, 255 // pqrstuvwxyz{|}~
)

@SharedImmutable
internal val BASE32_HEX = byteArrayOf(
  48, 49, 50, 51, 52, 53, 54, 55, 56, 57,  // 0123456789
  65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, // ABCDEFGHIJK
  76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86  // LMNOPQRSTUV
)

@SharedImmutable
internal val BASE32_HEX_LOOKUP = intArrayOf(
  0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 255, 255, 255, 255, 255, 255,  // 0123456789:;<=>?
  255, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, // @ABCDEFGHIJKLMNO
  25, 26, 27, 28, 29, 30, 31, 255, 255, 255, 255, 255, 255, 255, 255, 255, // PQRSTUVWXYZ[\]^_
  255, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, // `abcdefghijklmno
  25, 26, 27, 28, 29, 30, 31, 255, 255, 255, 255, 255, 255, 255, 255 // pqrstuvwxyz{|}~
)

internal fun ByteArray.commonEncodeBase32(map: ByteArray = BASE32): ByteArray {
  val out = ByteArray((size + 4) / 5 * 8)
  var outIndex = 0
  var i = 0
  var n = 0

  while (i < size) {
    val inV = this[i].retrieve()
    var mapIndex: Int
    if (n > 3) {
      val nextByte = if (i + 1 < size) this[i + 1].retrieve() else 0
      mapIndex = inV and (0xff shr n)
      n = (n + 5) % 8
      mapIndex = mapIndex shl n
      mapIndex = mapIndex or (nextByte shr 8 - n)
      i++
    } else {
      mapIndex = inV shr 8 - (n + 5) and 0x1f
      n = (n + 5) % 8
      if (n == 0) {
        i++
      }
    }
    out[outIndex++] = map[mapIndex]
  }

  val padSize = out.size - outIndex
  if (padSize > 0) {
    repeat(padSize) {
      out[outIndex++] = equalsByte
    }
  }

  return out
}

internal fun ByteArray.commonDecodeBase32(lookup: IntArray = BASE32_LOOKUP): ByteArray? {
  val limit = sizeOfIgnoreTrailing()
  if (limit == 0) {
    return ByteArray(0)
  }

  val out = ByteArray(limit * 5 / 8)
  var outIndex = 0
  var n = 0

  for (i in 0 until limit) {
    val lookupIndex = this[i] - '0'.code
    if (lookupIndex < 0 || lookupIndex >= lookup.size) {
      continue
    }

    val lookupV = lookup[lookupIndex]
    if (lookupV == 0xff) {
      return null
    }

    if (n <= 3) {
      n = (n + 5) % 8

      if (n == 0) {
        out[outIndex] = (out[outIndex].toInt() or lookupV).toByte()
        outIndex++

        if (outIndex >= out.size) {
          break
        }
      } else {
        out[outIndex] = (out[outIndex].toInt() or (lookupV shl (8 - n))).toByte()
      }
    } else {
      n = (n + 5) % 8
      out[outIndex] = (out[outIndex].toInt() or (lookupV ushr n)).toByte()
      outIndex++

      if (outIndex >= out.size) {
        break
      }
      out[outIndex] = (out[outIndex].toInt() or (lookupV shl (8 - n))).toByte()
    }
  }

  return out.selfOrCopyOf(outIndex)
}

@Suppress("NOTHING_TO_INLINE")
private inline fun Byte.retrieve(): Int {
  val bits = toInt()
  return if (bits < 0) bits + 256 else bits
}
