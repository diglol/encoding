@file:OptIn(InternalApi::class)

package diglol.encoding

import diglol.encoding.internal.BASE64_URL_SAFE
import diglol.encoding.internal.commonDecodeBase64
import diglol.encoding.internal.commonEncodeBase64
import diglol.encoding.internal.jsDecodeToString
import diglol.encoding.internal.jsEncodeToByteArray

// Base64 Std
actual fun ByteArray.encodeBase64(): ByteArray = commonEncodeBase64()
actual fun ByteArray.decodeBase64(): ByteArray? = commonDecodeBase64()

actual fun ByteArray.encodeBase64ToString(): String = commonEncodeBase64().jsDecodeToString()
actual fun String.decodeBase64ToBytes(): ByteArray? = jsEncodeToByteArray().commonDecodeBase64()

// Base64 Url
actual fun ByteArray.encodeBase64Url(): ByteArray = commonEncodeBase64(BASE64_URL_SAFE)
actual fun ByteArray.decodeBase64Url(): ByteArray? = commonDecodeBase64(BASE64_URL_SAFE)

actual fun ByteArray.encodeBase64UrlToString(): String =
  commonEncodeBase64(BASE64_URL_SAFE).jsDecodeToString()

actual fun String.decodeBase64UrlToBytes(): ByteArray? =
  jsEncodeToByteArray().commonDecodeBase64(BASE64_URL_SAFE)
