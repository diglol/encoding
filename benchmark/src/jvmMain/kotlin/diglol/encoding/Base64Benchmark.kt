@file:OptIn(InternalApi::class)

package diglol.encoding

import diglol.encoding.internal.commonDecodeBase64
import diglol.encoding.internal.commonDecodeBase64ToBytes
import diglol.encoding.internal.commonDecodeBase64Url
import diglol.encoding.internal.commonDecodeBase64UrlToBytes
import diglol.encoding.internal.commonEncodeBase64
import diglol.encoding.internal.commonEncodeBase64ToString
import diglol.encoding.internal.commonEncodeBase64Url
import diglol.encoding.internal.commonEncodeBase64UrlToString
import kotlin.random.Random
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.BenchmarkTimeUnit
import kotlinx.benchmark.Measurement
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Param
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State

@State(Scope.Benchmark)
@Measurement(iterations = 5, time = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.MICROSECONDS)
class Base64Benchmark {
  @Param("1024", "10240", "102400")
  var dataSize = 0
  private lateinit var data: ByteArray
  private lateinit var base64: ByteArray
  private lateinit var base64String: String
  private lateinit var base64Url: ByteArray
  private lateinit var base64UrlString: String

  @Setup
  fun setup() {
    data = Random.nextBytes(dataSize)
    base64 = data.encodeBase64()
    base64String = data.encodeBase64ToString()
    base64Url = data.encodeBase64Url()
    base64UrlString = data.encodeBase64UrlToString()
  }

  @Benchmark
  fun encodeBase64() = data.encodeBase64()

  @Benchmark
  fun decodeBase64() = base64.decodeBase64()

  @Benchmark
  fun encodeBase64ToString() = data.encodeBase64ToString()

  @Benchmark
  fun decodeBase64ToBytes() = base64String.decodeBase64ToBytes()

  @Benchmark
  fun encodeBase64Url() = data.encodeBase64Url()

  @Benchmark
  fun decodeBase64Url() = base64Url.decodeBase64Url()

  @Benchmark
  fun encodeBase64UrlToString() = data.encodeBase64UrlToString()

  @Benchmark
  fun decodeBase64UrlToBytes() = base64UrlString.decodeBase64UrlToBytes()

  @Benchmark
  fun encodeBase64Common() = data.commonEncodeBase64()

  @Benchmark
  fun decodeBase64Common() = base64.commonDecodeBase64()

  @Benchmark
  fun encodeBase64ToStringCommon() = data.commonEncodeBase64ToString()

  @Benchmark
  fun decodeBase64ToBytesCommon() = base64String.commonDecodeBase64ToBytes()

  @Benchmark
  fun encodeBase64UrlCommon() = data.commonEncodeBase64Url()

  @Benchmark
  fun decodeBase64UrlCommon() = base64Url.commonDecodeBase64Url()

  @Benchmark
  fun encodeBase64UrlToStringCommon() = data.commonEncodeBase64UrlToString()

  @Benchmark
  fun decodeBase64UrlToBytesCommon() = base64UrlString.commonDecodeBase64UrlToBytes()
}
