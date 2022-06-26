package diglol.encoding

import kotlin.native.concurrent.SharedImmutable

/** @author Matthew Nelson */

// https://datatracker.ietf.org/doc/html/rfc4648
@SharedImmutable
internal val BASE32 = byteArrayOf(
  65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, // ABCDEFGHIJKLM
  78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, // NOPQRSTUVWXYZ
  50, 51, 52, 53, 54, 55 // 234567
)

@SharedImmutable
internal val BASE32_HEX = byteArrayOf(
  48, 49, 50, 51, 52, 53, 54, 55, 56, 57,  // 0123456789
  65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, // ABCDEFGHIJK
  76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86  // LMNOPQRSTUV
)

internal fun ByteArray.commonEncodeBase32(map: ByteArray = BASE32): ByteArray {
  val out = ByteArray((size + 4) / 5 * 8)
  var index = 0
  val end = size - size % 5
  var i = 0

  while (i < end) {
    var b0 = 0L
    repeat(5) {
      b0 = (b0 shl 8) + this[i++].retrieveBits()
    }

    out[index++] = map[(b0 shr 35 and 0x1fL).toInt()] // 40-1*5 = 35
    out[index++] = map[(b0 shr 30 and 0x1fL).toInt()] // 40-2*5 = 30
    out[index++] = map[(b0 shr 25 and 0x1fL).toInt()] // 40-3*5 = 25
    out[index++] = map[(b0 shr 20 and 0x1fL).toInt()] // 40-4*5 = 20
    out[index++] = map[(b0 shr 15 and 0x1fL).toInt()] // 40-5*5 = 15
    out[index++] = map[(b0 shr 10 and 0x1fL).toInt()] // 40-6*5 = 10
    out[index++] = map[(b0 shr 5 and 0x1fL).toInt()] // 40-7*5 = 5
    out[index++] = map[(b0 and 0x1fL).toInt()] // 40-8*5 = 0
  }

  when (size - end) {
    1 -> { // 8*1 = 8 bits
      val b0 = this[i].retrieveBits()
      out[index++] = map[(b0 shr 3 and 0x1fL).toInt()] // 8-1*5 = 3
      out[index++] = map[(b0 shl 2 and 0x1fL).toInt()] // 5-3 = 2
      out[index++] = '='.code.toByte()
      out[index++] = '='.code.toByte()
      out[index++] = '='.code.toByte()
      out[index++] = '='.code.toByte()
      out[index++] = '='.code.toByte()
      out[index] = '='.code.toByte()
    }
    2 -> { // 8*2 = 16 bits
      val b0 = this[i++].retrieveBits()
      val b1 = (b0 shl 8) + this[i].retrieveBits()
      out[index++] = map[(b1 shr 11 and 0x1fL).toInt()] // 16-1*5 = 11
      out[index++] = map[(b1 shr 6 and 0x1fL).toInt()] // 16-2*5 = 6
      out[index++] = map[(b1 shr 1 and 0x1fL).toInt()] // 16-3*5 = 1
      out[index++] = map[(b1 shl 4 and 0x1fL).toInt()] // 5-1 = 4
      out[index++] = '='.code.toByte()
      out[index++] = '='.code.toByte()
      out[index++] = '='.code.toByte()
      out[index] = '='.code.toByte()
    }
    3 -> { // 8*3 = 24 bits
      val b0 = this[i++].retrieveBits()
      val b1 = (b0 shl 8) + this[i++].retrieveBits()
      val b2 = (b1 shl 8) + this[i].retrieveBits()
      out[index++] = map[(b2 shr 19 and 0x1fL).toInt()] // 24-1*5 = 19
      out[index++] = map[(b2 shr 14 and 0x1fL).toInt()] // 24-2*5 = 14
      out[index++] = map[(b2 shr 9 and 0x1fL).toInt()] // 24-3*5 = 9
      out[index++] = map[(b2 shr 4 and 0x1fL).toInt()] // 24-4*5 = 4
      out[index++] = map[(b2 shl 1 and 0x1fL).toInt()] // 5-4 = 1
      out[index++] = '='.code.toByte()
      out[index++] = '='.code.toByte()
      out[index] = '='.code.toByte()
    }
    4 -> { // 8*4 = 32 bits
      val b0 = this[i++].retrieveBits()
      val b1 = (b0 shl 8) + this[i++].retrieveBits()
      val b2 = (b1 shl 8) + this[i++].retrieveBits()
      val b3 = (b2 shl 8) + this[i].retrieveBits()
      out[index++] = map[(b3 shr 27 and 0x1fL).toInt()] // 32-1*5 = 27
      out[index++] = map[(b3 shr 22 and 0x1fL).toInt()] // 32-2*5 = 22
      out[index++] = map[(b3 shr 17 and 0x1fL).toInt()] // 32-3*5 = 17
      out[index++] = map[(b3 shr 12 and 0x1fL).toInt()] // 32-4*5 = 12
      out[index++] = map[(b3 shr 7 and 0x1fL).toInt()] // 32-5*5 = 7
      out[index++] = map[(b3 shr 2 and 0x1fL).toInt()] // 32-6*5 = 2
      out[index++] = map[(b3 shl 3 and 0x1fL).toInt()] // 5-2 = 3
      out[index] = '='.code.toByte()
    }
  }
  return out
}

internal fun ByteArray.commonDecodeBase32(map: ByteArray = BASE32): ByteArray? {
  val isBase32 = map.last().toInt() == '7'.code
  val limit = ignoreTrailingLength()

  if (limit == 0) {
    return ByteArray(0)
  }

  val out = ByteArray((limit * 5L / 8L).toInt())
  var outCount = 0
  var inCount = 0

  var word = 0L
  for (i in 0 until limit) {
    val bits: Long =
      when (val c = this[i]) {
        in 'A'.code..'Z'.code -> {
          if (isBase32) {
            // char ASCII value
            //  A    65    0
            //  Z    90    25 (ASCII - 65)
            c - 65L
          } else {
            // base32Hex uses A-V only
            if (c in 'W'.code..'Z'.code) {
              return null
            }

            // char ASCII value
            //  A    65    10
            //  V    86    31 (ASCII - 55)
            c - 55L
          }
        }
        in '0'.code..'9'.code -> {
          if (isBase32) {
            // Default base32 uses 2-7 only
            if (c in '0'.code..'1'.code || c in '8'.code..'9'.code) {
              return null
            }

            // char ASCII value
            //  2    50    26
            //  7    55    31 (ASCII - 24)
            c - 24L
          } else {
            // char ASCII value
            //  0    48    0
            //  9    57    9 (ASCII - 48)
            c - 48L
          }
        }
        '\n'.code.toByte(), '\r'.code.toByte(), ' '.code.toByte(), '\t'.code.toByte() -> {
          continue
        }
        else -> {
          return null
        }
      }

    // Append this char's 5 bits to the buffer
    word = word shl 5 or bits

    // For every 8 chars of input, we accumulate 40 bits of output data. Emit 5 bytes
    inCount++
    if (inCount % 8 == 0) {
      out[outCount++] = (word shr 32).toByte()
      out[outCount++] = (word shr 24).toByte()
      out[outCount++] = (word shr 16).toByte()
      out[outCount++] = (word shr 8).toByte()
      out[outCount++] = (word).toByte()
    }
  }

  when (inCount % 8) {
    1, 3, 6 -> {
      // 5*1 = 5 bits.  Truncated, fail.
      // 5*3 = 15 bits. Truncated, fail.
      // 5*6 = 30 bits. Truncated, fail.
      return null
    }
    2 -> { // 5*2 = 10 bits. Drop 2
      word = word shr 2
      out[outCount++] = word.toByte()
    }
    4 -> { // 5*4 = 20 bits. Drop 4
      word = word shr 4
      out[outCount++] = (word shr 8).toByte()
      out[outCount++] = (word).toByte()
    }
    5 -> { // 5*5 = 25 bits. Drop 1
      word = word shr 1
      out[outCount++] = (word shr 16).toByte()
      out[outCount++] = (word shr 8).toByte()
      out[outCount++] = (word).toByte()
    }
    7 -> { // 5*7 = 35 bits. Drop 3
      word = word shr 3
      out[outCount++] = (word shr 24).toByte()
      out[outCount++] = (word shr 16).toByte()
      out[outCount++] = (word shr 8).toByte()
      out[outCount++] = (word).toByte()
    }
  }
  return out.selfOrCopyOf(outCount)
}

@Suppress("NOTHING_TO_INLINE")
private inline fun Byte.retrieveBits(): Long {
  val bits = toLong()
  return if (bits < 0) bits + 256L else bits
}
