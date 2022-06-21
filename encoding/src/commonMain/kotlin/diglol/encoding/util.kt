package diglol.encoding

fun ByteArray.selfOrCopyOf(newSize: Int): ByteArray = if (size == newSize) this else copyOf(newSize)

internal fun String.ignoreTrailingLength(): Int {
  var limit = length
  while (limit > 0) {
    val c = this[limit - 1]
    if (c != '=' && c != '\n' && c != '\r' && c != ' ' && c != '\t') {
      break
    }
    limit--
  }
  return limit
}
