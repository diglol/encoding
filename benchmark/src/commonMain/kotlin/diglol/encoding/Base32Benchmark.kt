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
class Base32Benchmark {
  @Param("1024", "10240", "102400")
  var dataSize = 0
  private lateinit var data: ByteArray
  private lateinit var base32: String
  private lateinit var base32Hex: String

  @Setup
  fun setup() {
    data = Random.nextBytes(dataSize)
    base32 = data.encodeHex()
    base32Hex = data.encodeHex()
  }

  @Benchmark
  fun encodeBase32() = data.encodeBase32()

  @Benchmark
  fun decodeBase32() = base32.decodeBase32()

  @Benchmark
  fun encodeBase32Hex() = data.encodeBase32Hex()

  @Benchmark
  fun decodeBase32Hex() = base32Hex.decodeBase32Hex()
}
