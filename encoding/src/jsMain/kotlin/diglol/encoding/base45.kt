@file:OptIn(InternalApi::class)

package diglol.encoding

import diglol.encoding.internal.BASE45
import diglol.encoding.internal.commonEncodeBase45
import diglol.encoding.internal.jsDecodeToString

actual fun ByteArray.encodeBase45(): ByteArray = commonEncodeBase45(BASE45)

actual fun ByteArray.encodeBase45ToString(): String = commonEncodeBase45(BASE45).jsDecodeToString()

