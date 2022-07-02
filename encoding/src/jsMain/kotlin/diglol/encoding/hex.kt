@file:OptIn(InternalApi::class)

package diglol.encoding

import diglol.encoding.internal.commonDecodeHex
import diglol.encoding.internal.commonEncodeHex
import diglol.encoding.internal.jsDecodeToString
import diglol.encoding.internal.jsEncodeToByteArray

actual fun ByteArray.encodeHex(): ByteArray = commonEncodeHex()
actual fun ByteArray.decodeHex(): ByteArray? = commonDecodeHex()

actual fun ByteArray.encodeHexToString(): String = commonEncodeHex().jsDecodeToString()
actual fun String.decodeHexToBytes(): ByteArray? = jsEncodeToByteArray().commonDecodeHex()
