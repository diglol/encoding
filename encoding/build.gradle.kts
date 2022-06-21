plugins {
  kotlin("multiplatform")
  id("com.vanniktech.maven.publish.base")
}

kotlin {
  jvm()
  js(BOTH) {
    browser()
    nodejs()
  }

  macosX64()
  macosArm64()
  iosX64()
  iosArm64()
  iosArm32()
  iosSimulatorArm64()
  watchosArm32()
  watchosArm64()
  watchosSimulatorArm64()
  watchosX86()
  watchosX64()
  tvosArm64()
  tvosSimulatorArm64()
  tvosX64()

  mingwX64()
  mingwX86()
  linuxX64()
  linuxArm32Hfp()

  sourceSets {
    val commonMain by sourceSets.getting
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
