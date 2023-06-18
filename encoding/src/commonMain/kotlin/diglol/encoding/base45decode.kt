package diglol.encoding

import diglol.encoding.internal.commonDecodeBase45

fun ByteArray.decodeBase45(): ByteArray? = commonDecodeBase45()

fun ByteArray.decodeBase45ToString(): String? = decodeBase45()?.decodeToString()
