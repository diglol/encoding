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
  private lateinit var base32: ByteArray
  private lateinit var base32String: String
  private lateinit var base32Hex: ByteArray
  private lateinit var base32HexString: String

  @Setup
  fun setup() {
    data = Random.nextBytes(dataSize)
    base32 = data.encodeBase32()
    base32String = data.encodeBase32ToString()
    base32Hex = data.encodeBase32Hex()
    base32HexString = data.encodeBase32HexToString()
  }

  @Benchmark
  fun encodeBase32() = data.encodeBase32()

  @Benchmark
  fun decodeBase32() = base32.decodeBase32()

  @Benchmark
  fun encodeBase32ToString() = data.encodeBase32ToString()

  @Benchmark
  fun decodeBase32ToBytes() = base32String.decodeBase32ToBytes()

  @Benchmark
  fun encodeBase32Hex() = data.encodeBase32Hex()

  @Benchmark
  fun decodeBase32Hex() = base32Hex.decodeBase32Hex()

  @Benchmark
  fun encodeBase32HexToString() = data.encodeBase32HexToString()

  @Benchmark
  fun decodeBase32HexToBytes() = base32HexString.decodeBase32HexToBytes()
}
