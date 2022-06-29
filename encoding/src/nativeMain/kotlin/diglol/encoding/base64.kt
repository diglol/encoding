package diglol.encoding

// Base64 Std
actual fun ByteArray.encodeBase64(): ByteArray = commonEncodeBase64()
actual fun ByteArray.decodeBase64(): ByteArray? = commonDecodeBase64()

actual fun ByteArray.encodeBase64ToString(): String = commonEncodeBase64().decodeToString()
actual fun String.decodeBase64ToBytes(): ByteArray? = encodeToByteArray().commonDecodeBase64()

// Base64 Url
actual fun ByteArray.encodeBase64Url(): ByteArray = commonEncodeBase64(BASE64_URL_SAFE)
actual fun ByteArray.decodeBase64Url(): ByteArray? = commonDecodeBase64(BASE64_URL_SAFE)

actual fun ByteArray.encodeBase64UrlToString(): String =
  commonEncodeBase64(BASE64_URL_SAFE).decodeToString()

actual fun String.decodeBase64UrlToBytes(): ByteArray? =
  encodeToByteArray().commonDecodeBase64(BASE64_URL_SAFE)
