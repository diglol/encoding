package diglol.encoding

import diglol.encoding.internal.commonEncodeBase45

fun ByteArray.encodeBase45(): ByteArray = commonEncodeBase45()

fun ByteArray.encodeBase45ToString(): String = commonEncodeBase45().decodeToString()

