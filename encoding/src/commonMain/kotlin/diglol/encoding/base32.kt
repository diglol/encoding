package diglol.encoding

// Base32 Std
fun ByteArray.encodeBase32(): ByteArray = commonEncodeBase32(BASE32)
fun ByteArray.decodeBase32(): ByteArray? = commonDecodeBase32(BASE32)
fun ByteArray.encodeBase32ToString(): String = commonEncodeBase32(BASE32).decodeToString()
fun String.decodeBase32ToBytes(): ByteArray? = encodeToByteArray().commonDecodeBase32(BASE32)

// Base32 Hex
fun ByteArray.encodeBase32Hex(): ByteArray = commonEncodeBase32(BASE32_HEX)
fun ByteArray.decodeBase32Hex(): ByteArray? = commonDecodeBase32(BASE32_HEX)
fun ByteArray.encodeBase32HexToString(): String = commonEncodeBase32(BASE32_HEX).decodeToString()
fun String.decodeBase32HexToBytes(): ByteArray? = encodeToByteArray().commonDecodeBase32(BASE32_HEX)
