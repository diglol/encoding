@file:OptIn(InternalApi::class)

package diglol.encoding

actual fun ByteArray.encodeHex(): ByteArray = commonEncodeHex()
actual fun ByteArray.decodeHex(): ByteArray? = commonDecodeHex()

actual fun ByteArray.encodeHexToString(): String = commonEncodeHex().jsDecodeToString()
actual fun String.decodeHexToBytes(): ByteArray? = jsEncodeToByteArray().commonDecodeHex()
