package diglol.encoding

import android.os.Build
import  android.util.Base64 as Base64Android
import java.util.Base64

// Base64 Std
actual fun ByteArray.encodeBase64(): ByteArray =
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    Base64.getEncoder().encode(this)
  } else {
    Base64Android.encode(this, Base64Android.DEFAULT)
      ?: throw NullPointerException("Base.decode() not mocked.")
  }

actual fun ByteArray.decodeBase64(): ByteArray? =
  try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      Base64.getDecoder().decode(selfOrCopyOf(sizeOfIgnoreTrailing()))
    } else {
      Base64Android.decode(this, Base64Android.DEFAULT)
        ?: throw NullPointerException("Base.decode() not mocked.")
    }
  } catch (e: IllegalArgumentException) {
    null
  }

actual fun ByteArray.encodeBase64ToString(): String = String(encodeBase64())
actual fun String.decodeBase64ToBytes(): ByteArray? = toByteArray().decodeBase64()

// Base64 Url
actual fun ByteArray.encodeBase64Url(): ByteArray =
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    Base64.getUrlEncoder().encode(this)
  } else {
    Base64Android.encode(this, Base64Android.URL_SAFE)
      ?: throw NullPointerException("Base.decode() not mocked.")
  }

actual fun ByteArray.decodeBase64Url(): ByteArray? =
  try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      Base64.getUrlDecoder().decode(selfOrCopyOf(sizeOfIgnoreTrailing()))
    } else {
      Base64Android.decode(this, Base64Android.URL_SAFE)
        ?: throw NullPointerException("Base.decode() not mocked.")
    }
  } catch (e: IllegalArgumentException) {
    null
  }

actual fun ByteArray.encodeBase64UrlToString(): String = String(encodeBase64Url())
actual fun String.decodeBase64UrlToBytes(): ByteArray? = toByteArray().decodeBase64Url()
