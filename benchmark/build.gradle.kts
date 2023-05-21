plugins {
  kotlin("multiplatform")
  kotlin("plugin.allopen")
  id("org.jetbrains.kotlinx.benchmark")
}

allOpen {
  annotation("org.openjdk.jmh.annotations.State")
}

kotlin {
  jvm()
  js(IR) {
    nodejs()
  }

  macosX64()
  macosArm64()
  mingwX64()
  linuxX64()

  sourceSets {
    all {
      languageSettings.optIn("kotlin.RequiresOptIn")
    }

    val commonMain by sourceSets.getting {
      dependencies {
        implementation(projects.encoding)
        implementation(libs.benchmark.runtime)
      }
    }

    val jvmMain by sourceSets.getting {
      dependsOn(commonMain)
      dependencies {
        api(libs.jmh.core)
      }
    }

    val jsMain by sourceSets.getting

    val nativeMain by sourceSets.creating {
      dependsOn(commonMain)
    }

    val darwinMain by sourceSets.creating {
      dependsOn(nativeMain)
    }

    val linuxMain by sourceSets.creating {
      dependsOn(nativeMain)
    }

    val mingwMain by sourceSets.creating {
      dependsOn(nativeMain)
    }

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>().all {
      val mainSourceSet = compilations.getByName("main").defaultSourceSet
      val testSourceSet = compilations.getByName("test").defaultSourceSet

      mainSourceSet.dependsOn(
        when {
          konanTarget.family.isAppleFamily -> darwinMain
          konanTarget.family == org.jetbrains.kotlin.konan.target.Family.LINUX -> linuxMain
          konanTarget.family == org.jetbrains.kotlin.konan.target.Family.MINGW -> mingwMain
          else -> nativeMain
        }
      )
    }
  }
}

benchmark {
  configurations {
    getByName("main") {
      iterations = 5
      iterationTime = 1
      advanced("jvmForks", 1)
      advanced("nativeGCAfterIteration", true)
      advanced("nativeFork", "perBenchmark")
      reportFormat = "text"
    }
  }

  targets {
    register("jvm")
    register("js")
    register("macosX64")
    register("macosArm64")
    register("linuxX64")
    register("mingwX64")
  }
}
