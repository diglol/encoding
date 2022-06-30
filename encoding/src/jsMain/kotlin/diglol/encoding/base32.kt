@file:OptIn(InternalApi::class)

package diglol.encoding

// Base32 Std
actual fun ByteArray.encodeBase32(): ByteArray = commonEncodeBase32(BASE32)
actual fun ByteArray.decodeBase32(): ByteArray? = commonDecodeBase32(BASE32_LOOKUP)
actual fun ByteArray.encodeBase32ToString(): String = commonEncodeBase32(BASE32).jsDecodeToString()
actual fun String.decodeBase32ToBytes(): ByteArray? =
  jsEncodeToByteArray().commonDecodeBase32(BASE32_LOOKUP)

// Base32 Hex
actual fun ByteArray.encodeBase32Hex(): ByteArray = commonEncodeBase32(BASE32_HEX)
actual fun ByteArray.decodeBase32Hex(): ByteArray? = commonDecodeBase32(BASE32_HEX_LOOKUP)
actual fun ByteArray.encodeBase32HexToString(): String =
  commonEncodeBase32(BASE32_HEX).jsDecodeToString()

actual fun String.decodeBase32HexToBytes(): ByteArray? =
  jsEncodeToByteArray().commonDecodeBase32(BASE32_HEX_LOOKUP)
