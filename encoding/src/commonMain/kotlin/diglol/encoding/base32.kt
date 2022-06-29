package diglol.encoding

// Base32 Std
expect fun ByteArray.encodeBase32(): ByteArray
expect fun ByteArray.decodeBase32(): ByteArray?
expect fun ByteArray.encodeBase32ToString(): String
expect fun String.decodeBase32ToBytes(): ByteArray?

// Base32 Hex
expect fun ByteArray.encodeBase32Hex(): ByteArray
expect fun ByteArray.decodeBase32Hex(): ByteArray?
expect fun ByteArray.encodeBase32HexToString(): String
expect fun String.decodeBase32HexToBytes(): ByteArray?
