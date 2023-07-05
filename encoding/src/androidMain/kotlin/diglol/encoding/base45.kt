@file:JvmName("Base45")

package diglol.encoding

import diglol.encoding.internal.BASE45
import diglol.encoding.internal.commonDecodeBase45
import diglol.encoding.internal.commonEncodeBase45

actual fun ByteArray.encodeBase45(): ByteArray = commonEncodeBase45(BASE45)
actual fun ByteArray.decodeBase45(): ByteArray? = commonDecodeBase45()

actual fun ByteArray.encodeBase45ToString(): String = commonEncodeBase45(BASE45).decodeToString()
actual fun String.decodeBase45ToBytes(): ByteArray? = encodeToByteArray().decodeBase45()
