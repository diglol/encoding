# Diglol Encoding

Diglol Encoding 为 Kotlin Multiplatform 提供常用的编码实现。

当前支持的编码:

- Hex (Base16)
- Base32 [Std, Hex]
- Base64 [Std, Url]

### 发布

我们的 [change log](CHANGELOG.md) 有发布历史。

```gradle
implementation("com.diglol.encoding:encoding:0.1.0")
```

### 使用

##### [Hex][rfc4648]

```kotlin
val data = "foobar".toByteArray()

val hex = data.encodeHex()
println(hex)
// 输出：666F6F626172
assert(data.contentEquals(hex.decodeHex()))
```

##### [Base32][rfc4648]

```kotlin
val data = "foobar".toByteArray()

val base32 = data.encodeBase32()
println(base32)
// 输出：MZXW6YTBOI======
assert(data.contentEquals(base32.decodeBase32()))

val base32Hex = data.encodeBase32Hex()
println(base32Hex)
// 输出：CPNMUOJ1E8======
assert(data.contentEquals(base32Hex.decodeBase32Hex()))
```

##### [Base64][rfc4648]

```kotlin
val data = "foobar".toByteArray()

val base64 = data.encodeBase64()
println(base64)
// 输出：Zm9vYmFy
assert(data.contentEquals(base64.decodeBase64()))

val base64Url = data.encodeBase64Url()
println(base64Url)
// 输出：Zm9vYmFy
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
