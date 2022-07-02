package diglol.encoding

internal const val equalsByte = '='.code.toByte()

@Suppress("NOTHING_TO_INLINE")
internal inline fun ByteArray.selfOrCopyOf(newSize: Int): ByteArray =
  if (size == newSize) this else copyOf(newSize)

// Was all padding, whitespace, or otherwise ignorable characters
@Suppress("NOTHING_TO_INLINE")
internal inline fun ByteArray.sizeOfIgnoreTrailing(): Int {
  var limit = size
  while (limit > 0) {
    val c = this[limit - 1].toInt()
    if (c != '='.code && c != '\n'.code && c != '\r'.code && c != ' '.code && c != '\t'.code) {
      break
    }
    limit--
  }
  return limit
}
