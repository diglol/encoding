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
