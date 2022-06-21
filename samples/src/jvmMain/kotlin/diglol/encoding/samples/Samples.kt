@file:JvmName("Samples")

package diglol.encoding.samples

import diglol.encoding.decodeBase32
import diglol.encoding.decodeBase32Hex
import diglol.encoding.decodeBase64
import diglol.encoding.decodeBase64Url
import diglol.encoding.decodeHex
import diglol.encoding.encodeBase32
import diglol.encoding.encodeBase32Hex
import diglol.encoding.encodeBase64
import diglol.encoding.encodeBase64Url
import diglol.encoding.encodeHex

fun main() {
  val data = "foobar".toByteArray()
  val hex = data.encodeHex()
  println(hex)
  // 打印：666F6F626172
  assert(data.contentEquals(hex.decodeHex()))

  val base32 = data.encodeBase32()
  println(base32)
  // 打印：MZXW6YTBOI======
  assert(data.contentEquals(base32.decodeBase32()))

  val base32Hex = data.encodeBase32Hex()
  println(base32Hex)
  // 打印：CPNMUOJ1E8======
  assert(data.contentEquals(base32Hex.decodeBase32Hex()))

  val base64 = data.encodeBase64()
  println(base64)
  // 打印：Zm9vYmFy
  assert(data.contentEquals(base64.decodeBase64()))

  val base64Url = data.encodeBase64Url()
  println(base64Url)
  // 打印：Zm9vYmFy
  assert(data.contentEquals(base64Url.decodeBase64Url()))
}
