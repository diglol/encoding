@file:OptIn(InternalApi::class)

package diglol.encoding

import diglol.encoding.internal.jsDecodeToString
import diglol.encoding.internal.jsEncodeToByteArray
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
class StringByteArrayBenchmark {
  @Param("1024", "10240", "102400")
  var dataSize = 0
  private lateinit var data: ByteArray
  private lateinit var dataString: String

  @Setup
  fun setup() {
    data = Random.nextBytes(dataSize)
    dataString = data.decodeToString()
  }

  @Benchmark
  fun encodeToByteArray() = dataString.encodeToByteArray()

  @Benchmark
  fun encodeToByteArrayJs() = dataString.jsEncodeToByteArray()

  @Benchmark
  fun decodeToString() = data.decodeToString()

  @Benchmark
  fun decodeToStringJs() = data.jsDecodeToString()
}
