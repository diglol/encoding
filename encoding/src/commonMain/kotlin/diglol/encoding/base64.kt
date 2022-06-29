package diglol.encoding

// Base64 Std
expect fun ByteArray.encodeBase64(): ByteArray
expect fun ByteArray.decodeBase64(): ByteArray?

expect fun ByteArray.encodeBase64ToString(): String
expect fun String.decodeBase64ToBytes(): ByteArray?

// Base64 Url
expect fun ByteArray.encodeBase64Url(): ByteArray
expect fun ByteArray.decodeBase64Url(): ByteArray?

expect fun ByteArray.encodeBase64UrlToString(): String
expect fun String.decodeBase64UrlToBytes(): ByteArray?
