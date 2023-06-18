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
class Base45Benchmark {
  @Param("1024", "10240", "102400")
  var dataSize = 0
  private lateinit var data: ByteArray
  private lateinit var base45: ByteArray
  private lateinit var base45String: String

  @Setup
  fun setup() {
    data = Random.nextBytes(dataSize)
    base45 = data.encodeBase45()
    base45String = data.encodeBase45ToString()
  }

  @Benchmark
  fun encodeBase45() = data.encodeBase45()

  @Benchmark
  fun decodeBase45() = base45.decodeBase45()

  @Benchmark
  fun encodeBase45ToString() = data.encodeBase45ToString()

  @Benchmark
  fun decodeBase45ToBytes() = base45String.decodeBase45ToBytes()
}
