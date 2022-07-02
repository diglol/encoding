package diglol.encoding.internal

import diglol.encoding.InternalApi

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
