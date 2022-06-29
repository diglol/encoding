package diglol.encoding

// Base32 Std
actual fun ByteArray.encodeBase32(): ByteArray = commonEncodeBase32(BASE32)
actual fun ByteArray.decodeBase32(): ByteArray? = commonDecodeBase32(BASE32)
actual fun ByteArray.encodeBase32ToString(): String = commonEncodeBase32(BASE32).decodeToString()
actual fun String.decodeBase32ToBytes(): ByteArray? = encodeToByteArray().commonDecodeBase32(BASE32)

// Base32 Hex
actual fun ByteArray.encodeBase32Hex(): ByteArray = commonEncodeBase32(BASE32_HEX)
actual fun ByteArray.decodeBase32Hex(): ByteArray? = commonDecodeBase32(BASE32_HEX)
actual fun ByteArray.encodeBase32HexToString(): String =
  commonEncodeBase32(BASE32_HEX).decodeToString()

actual fun String.decodeBase32HexToBytes(): ByteArray? =
  encodeToByteArray().commonDecodeBase32(BASE32_HEX)
