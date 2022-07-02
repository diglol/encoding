package diglol.encoding.internal

import diglol.encoding.equalsByte
import diglol.encoding.selfOrCopyOf
import diglol.encoding.sizeOfIgnoreTrailing
import kotlin.native.concurrent.SharedImmutable

/** @author Alexander Y. Kleymenov */

// https://datatracker.ietf.org/doc/html/rfc4648
@SharedImmutable
internal val BASE64 = byteArrayOf(
  65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, // ABCDEFGHIJKLM
  78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, // NOPQRSTUVWXYZ
  98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, // abcdefghijklm
  110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, // nopqrstuvwxyz
  48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 // 0123456789+/
)

@SharedImmutable
internal val BASE64_URL_SAFE = byteArrayOf(
  65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, // ABCDEFGHIJKLM
  78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, // NOPQRSTUVWXYZ
  98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, // abcdefghijklm
  110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, // nopqrstuvwxyz
  48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 // 0123456789-_
)

internal fun ByteArray.commonEncodeBase64(map: ByteArray = BASE64): ByteArray {
  val length = (size + 2) / 3 * 4
  val out = ByteArray(length)
  var index = 0
  val end = size - size % 3
  var i = 0

  while (i < end) {
    val b0 = this[i++].toInt()
    val b1 = this[i++].toInt()
    val b2 = this[i++].toInt()
    out[index++] = map[(b0 and 0xff shr 2)]
    out[index++] = map[(b0 and 0x03 shl 4) or (b1 and 0xff shr 4)]
    out[index++] = map[(b1 and 0x0f shl 2) or (b2 and 0xff shr 6)]
    out[index++] = map[(b2 and 0x3f)]
  }

  when (size - end) {
    1 -> {
      val b0 = this[i].toInt()
      out[index++] = map[b0 and 0xff shr 2]
      out[index++] = map[b0 and 0x03 shl 4]
      out[index++] = equalsByte
      out[index] = equalsByte
    }
    2 -> {
      val b0 = this[i++].toInt()
      val b1 = this[i].toInt()
      out[index++] = map[(b0 and 0xff shr 2)]
      out[index++] = map[(b0 and 0x03 shl 4) or (b1 and 0xff shr 4)]
      out[index++] = map[(b1 and 0x0f shl 2)]
      out[index] = equalsByte
    }
  }
  return out
}

internal fun ByteArray.commonDecodeBase64(map: ByteArray = BASE64): ByteArray? {
  val isBase64 = map.last().toInt() == '/'.code
  val limit = sizeOfIgnoreTrailing()

  if (limit == 0) {
    return ByteArray(0)
  }

  // If the input includes whitespace, this output array will be longer than necessary.
  val out = ByteArray((limit * 6L / 8L).toInt())
  var outCount = 0
  var inCount = 0

  var word = 0
  for (pos in 0 until limit) {
    val c = this[pos].toInt()

    val bits: Int
    when (c) {
      in 'A'.code..'Z'.code -> {
        // char ASCII value
        //  A    65    0
        //  Z    90    25 (ASCII - 65)
        bits = c - 65
      }
      in 'a'.code..'z'.code -> {
        // char ASCII value
        //  a    97    26
        //  z    122   51 (ASCII - 71)
        bits = c - 71
      }
      in '0'.code..'9'.code -> {
        // char ASCII value
        //  0    48    52
        //  9    57    61 (ASCII + 4)
        bits = c + 4
      }
      '+'.code -> {
        if (!isBase64) return null
        bits = 62
      }
      '-'.code -> {
        if (isBase64) return null
        bits = 62
      }
      '/'.code -> {
        if (!isBase64) return null
        bits = 63
      }
      '_'.code -> {
        if (isBase64) return null
        bits = 63
      }
      else -> {
        return null
      }
    }

    // Append this char's 6 bits to the word.
    word = word shl 6 or bits

    // For every 4 chars of input, we accumulate 24 bits of output. Emit 3 bytes.
    inCount++
    if (inCount % 4 == 0) {
      out[outCount++] = (word shr 16).toByte()
      out[outCount++] = (word shr 8).toByte()
      out[outCount++] = word.toByte()
    }
  }

  when (inCount % 4) {
    1 -> {
      // We read 1 char followed by "===". But 6 bits is a truncated byte! Fail.
      return null
    }
    2 -> {
      // We read 2 chars followed by "==". Emit 1 byte with 8 of those 12 bits.
      word = word shl 12
      out[outCount++] = (word shr 16).toByte()
    }
    3 -> {
      // We read 3 chars, followed by "=". Emit 2 bytes for 16 of those 18 bits.
      word = word shl 6
      out[outCount++] = (word shr 16).toByte()
      out[outCount++] = (word shr 8).toByte()
    }
  }
  return out.selfOrCopyOf(outCount)
}

