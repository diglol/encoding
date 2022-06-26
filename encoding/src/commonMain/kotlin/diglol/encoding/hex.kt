package diglol.encoding

fun ByteArray.encodeHex(): ByteArray = commonEncodeHex()
fun ByteArray.decodeHex(): ByteArray? = commonDecodeHex()

fun ByteArray.encodeHexToString(): String = commonEncodeHex().decodeToString()
fun String.decodeHexToBytes(): ByteArray? = encodeToByteArray().commonDecodeHex()
