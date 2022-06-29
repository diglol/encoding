package diglol.encoding

expect fun ByteArray.encodeHex(): ByteArray
expect fun ByteArray.decodeHex(): ByteArray?

expect fun ByteArray.encodeHexToString(): String
expect fun String.decodeHexToBytes(): ByteArray?
