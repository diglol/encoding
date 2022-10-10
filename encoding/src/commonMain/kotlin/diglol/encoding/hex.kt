@file:JvmName("Hex")

package diglol.encoding

import kotlin.jvm.JvmName

expect fun ByteArray.encodeHex(): ByteArray
expect fun ByteArray.decodeHex(): ByteArray?

expect fun ByteArray.encodeHexToString(): String
expect fun String.decodeHexToBytes(): ByteArray?
