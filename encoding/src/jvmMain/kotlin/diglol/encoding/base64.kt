package diglol.encoding

import java.util.Base64

// Base64 Std
actual fun ByteArray.encodeBase64(): ByteArray = Base64.getEncoder().encode(this)
actual fun ByteArray.decodeBase64(): ByteArray? =
  try {
    Base64.getDecoder().decode(selfOrCopyOf(sizeOfIgnoreTrailing()))
  } catch (e: IllegalArgumentException) {
    null
  }

actual fun ByteArray.encodeBase64ToString(): String = Base64.getEncoder().encodeToString(this)
actual fun String.decodeBase64ToBytes(): ByteArray? = toByteArray().decodeBase64()

// Base64 Url
actual fun ByteArray.encodeBase64Url(): ByteArray = Base64.getUrlEncoder().encode(this)
actual fun ByteArray.decodeBase64Url(): ByteArray? =
  try {
    Base64.getUrlDecoder().decode(selfOrCopyOf(sizeOfIgnoreTrailing()))
  } catch (e: IllegalArgumentException) {
    null
  }

actual fun ByteArray.encodeBase64UrlToString(): String =
  Base64.getUrlEncoder().encodeToString(this)

actual fun String.decodeBase64UrlToBytes(): ByteArray? = toByteArray().decodeBase64Url()

// Base64 Std
@InternalApi
fun ByteArray.commonEncodeBase64(): ByteArray = commonEncodeBase64(BASE64)

@InternalApi
fun ByteArray.commonDecodeBase64(): ByteArray? = commonDecodeBase64(BASE64)

@InternalApi
fun ByteArray.commonEncodeBase64ToString(): String = commonEncodeBase64(BASE64).decodeToString()

@InternalApi
fun String.commonDecodeBase64ToBytes(): ByteArray? = encodeToByteArray().commonDecodeBase64(BASE64)

// Base64 Url
@InternalApi
fun ByteArray.commonEncodeBase64Url(): ByteArray = commonEncodeBase64(BASE64_URL_SAFE)

@InternalApi
fun ByteArray.commonDecodeBase64Url(): ByteArray? = commonDecodeBase64(BASE64_URL_SAFE)

@InternalApi
fun ByteArray.commonEncodeBase64UrlToString(): String =
  commonEncodeBase64(BASE64_URL_SAFE).decodeToString()

@InternalApi
fun String.commonDecodeBase64UrlToBytes(): ByteArray? =
  encodeToByteArray().commonDecodeBase64(BASE64_URL_SAFE)
