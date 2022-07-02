package diglol.encoding

import diglol.encoding.internal.commonDecodeHex
import diglol.encoding.internal.commonEncodeHex

actual fun ByteArray.encodeHex(): ByteArray = commonEncodeHex()
actual fun ByteArray.decodeHex(): ByteArray? = commonDecodeHex()

actual fun ByteArray.encodeHexToString(): String = commonEncodeHex().decodeToString()
actual fun String.decodeHexToBytes(): ByteArray? = encodeToByteArray().commonDecodeHex()
