package diglol.encoding

// Base64 Std
fun ByteArray.encodeBase64(): ByteArray = commonEncodeBase64(BASE64)
fun ByteArray.decodeBase64(): ByteArray? = commonDecodeBase64(BASE64)

fun ByteArray.encodeBase64ToString(): String = commonEncodeBase64(BASE64).decodeToString()
fun String.decodeBase64ToBytes(): ByteArray? = encodeToByteArray().commonDecodeBase64(BASE64)

// Base64 Url
fun ByteArray.encodeBase64Url(): ByteArray = commonEncodeBase64(BASE64_URL_SAFE)
fun ByteArray.decodeBase64Url(): ByteArray? = commonDecodeBase64(BASE64_URL_SAFE)

fun ByteArray.encodeBase64UrlToString(): String =
  commonEncodeBase64(BASE64_URL_SAFE).decodeToString()

fun String.decodeBase64UrlToBytes(): ByteArray? =
  encodeToByteArray().commonDecodeBase64(BASE64_URL_SAFE)
