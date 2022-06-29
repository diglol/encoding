package diglol.encoding

actual fun ByteArray.encodeHex(): ByteArray = commonEncodeHex()
actual fun ByteArray.decodeHex(): ByteArray? = commonDecodeHex()

actual fun ByteArray.encodeHexToString(): String = commonEncodeHex().decodeToString()
actual fun String.decodeHexToBytes(): ByteArray? = encodeToByteArray().commonDecodeHex()
