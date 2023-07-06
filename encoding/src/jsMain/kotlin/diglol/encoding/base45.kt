@file:OptIn(InternalApi::class)

package diglol.encoding

import diglol.encoding.internal.commonDecodeBase45
import diglol.encoding.internal.commonEncodeBase45
import diglol.encoding.internal.jsDecodeToString
import diglol.encoding.internal.jsEncodeToByteArray

actual fun ByteArray.encodeBase45(): ByteArray = commonEncodeBase45()

actual fun ByteArray.encodeBase45ToString(): String = commonEncodeBase45().jsDecodeToString()

actual fun ByteArray.decodeBase45(): ByteArray? = commonDecodeBase45()
actual fun String.decodeBase45ToBytes(): ByteArray? = jsEncodeToByteArray().decodeBase45()
