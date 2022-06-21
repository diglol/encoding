plugins {
  kotlin("multiplatform")
  kotlin("plugin.allopen") version libs.versions.kotlin.get()
  id("org.jetbrains.kotlinx.benchmark")
}

allOpen {
  annotation("org.openjdk.jmh.annotations.State")
}

kotlin {
  jvm()
  js(IR) {
    browser()
    nodejs()
  }
  js { // BOTH error
    browser()
    nodejs()
  }

  mingwX64()
  linuxX64()

  sourceSets {
    val commonMain by sourceSets.getting {
      dependencies {
        implementation(projects.encoding)
        implementation(libs.benchmark.runtime)
      }
    }
    val commonTest by sourceSets.getting {
      dependencies {
        implementation(kotlin("test"))
      }
    }

    val jvmMain by sourceSets.getting {
      dependsOn(commonMain)
    }
    val jvmTest by sourceSets.getting {
      dependsOn(jvmMain)
      dependsOn(commonTest)
    }

    val jsMain by sourceSets.getting
    val jsTest by sourceSets.getting

    val nativeMain by sourceSets.creating
    nativeMain.dependsOn(commonMain)

    val darwinMain by sourceSets.creating {
      dependsOn(nativeMain)
    }

    val darwinTest by sourceSets.creating {
      dependsOn(commonTest)
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

      testSourceSet.dependsOn(
        if (konanTarget.family.isAppleFamily) {
          darwinTest
        } else {
          commonTest
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
      advanced("perBenchmark", "perBenchmark")
      reportFormat = "text"
    }

    targets {
      register("jvm")
      register("js")
      register("jsIr")
      register("iosArm64")
      register("macosX64")
      register("macosArm64")
      register("linuxX64")
      register("mingwX64")
    }
  }
}
