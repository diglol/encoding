package diglol.encoding

import org.khronos.webgl.Int8Array
import org.khronos.webgl.Uint8Array

private val textEncode = js("new TextEncoder();")
private val textDecoder = js("new TextDecoder();")

@InternalApi
fun String.jsEncodeToByteArray(): ByteArray {
  val encoded = textEncode.encode(this)
  return Int8Array(encoded.unsafeCast<Uint8Array>().buffer).unsafeCast<ByteArray>()
}

@InternalApi
fun ByteArray.jsDecodeToString(): String =
  textDecoder.decode(this.unsafeCast<Int8Array>()).unsafeCast<String>()
