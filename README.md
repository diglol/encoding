# Diglol Encoding

Diglol Encoding provides implementations of common encodings for Kotlin Multiplatform.
Translations: [中文](README-zh.md)

Currently supported encodings:

- Hex(Base16)
- Base32 (Std, Hex)
- Base64 (Std, Url)

### Releases

Our [change log](CHANGELOG.md) has release history.

```gradle
implementation("com.diglol.encoding:encoding:0.1.0")
```

### Usage

##### [Hex][rfc4648]

```kotlin
val data = "foobar".toByteArray()

val hex = data.encodeHex()
println(hex)
// 打印：666F6F626172
assert(data.contentEquals(hex.decodeHex()))
```

##### [Base32][rfc4648]

```kotlin
val data = "foobar".toByteArray()

val base32 = data.encodeBase32()
println(base32)
// 打印：MZXW6YTBOI======
assert(data.contentEquals(base32.decodeBase32()))

val base32Hex = data.encodeBase32Hex()
println(base32Hex)
// 打印：CPNMUOJ1E8======
assert(data.contentEquals(base32Hex.decodeBase32Hex()))
```

##### [Base64][rfc4648]

```kotlin
val data = "foobar".toByteArray()

val base64 = data.encodeBase64()
println(base64)
// 打印：Zm9vYmFy
assert(data.contentEquals(base64.decodeBase64()))

val base64Url = data.encodeBase64Url()
println(base64Url)
// 打印：Zm9vYmFy
assert(data.contentEquals(base64Url.decodeBase64Url()))
```

### License

    Copyright 2022 Diglol

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[rfc4648]: https://datatracker.ietf.org/doc/html/rfc4648
