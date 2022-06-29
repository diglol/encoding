@file:JvmName("Samples")

package diglol.encoding.samples

import diglol.encoding.decodeBase32HexToBytes
import diglol.encoding.decodeBase32ToBytes
import diglol.encoding.decodeBase64ToBytes
import diglol.encoding.decodeBase64UrlToBytes
import diglol.encoding.decodeHexToBytes
import diglol.encoding.encodeBase32HexToString
import diglol.encoding.encodeBase32ToString
import diglol.encoding.encodeBase64ToString
import diglol.encoding.encodeBase64UrlToString
import diglol.encoding.encodeHexToString

fun main() {
  val data = "foobar".toByteArray()
  val hexString = data.encodeHexToString()
  println(hexString)
  // 打印：666F6F626172
  assert(data.contentEquals(hexString.decodeHexToBytes()))

  val base32String = data.encodeBase32ToString()
  println(base32String)
  // 打印：MZXW6YTBOI======
  assert(data.contentEquals(base32String.decodeBase32ToBytes()))

  val base32HexString = data.encodeBase32HexToString()
  println(base32HexString)
  // 打印：CPNMUOJ1E8======
  assert(data.contentEquals(base32HexString.decodeBase32HexToBytes()))

  val base64String = data.encodeBase64ToString()
  println(base64String)
  // 打印：Zm9vYmFy
  assert(data.contentEquals(base64String.decodeBase64ToBytes()))

  val base64UrlString = data.encodeBase64UrlToString()
  println(base64UrlString)
  // 打印：Zm9vYmFy
  assert(data.contentEquals(base64UrlString.decodeBase64UrlToBytes()))
}
