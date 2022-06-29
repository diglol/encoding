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
class HexBenchmark {
  @Param("1024", "10240", "102400")
  var dataSize = 0
  private lateinit var data: ByteArray
  private lateinit var hex: ByteArray
  private lateinit var hexString: String

  @Setup
  fun setup() {
    data = Random.nextBytes(dataSize)
    hex = data.encodeHex()
    hexString = data.encodeHexToString()
  }

  @Benchmark
  fun encodeHex() = data.encodeHex()

  @Benchmark
  fun decodeHex() = hex.decodeHex()

  @Benchmark
  fun encodeHexToString() = data.encodeHexToString()

  @Benchmark
  fun decodeHexToBytes() = hexString.decodeHexToBytes()
}
