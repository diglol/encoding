package diglol.encoding

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
  private lateinit var base64: String
  private lateinit var base64Url: String

  @Setup
  fun setup() {
    data = Random.nextBytes(dataSize)
    base64 = data.encodeHex()
    base64Url = data.encodeHex()
  }

  @Benchmark
  fun encodeBase64() = data.encodeBase64()

  @Benchmark
  fun decodeBase64() = base64.decodeBase64()

  @Benchmark
  fun encodeBase64Url() = data.encodeBase64Url()

  @Benchmark
  fun decodeBase64Url() = base64Url.decodeBase64Url()
}
