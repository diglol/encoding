plugins {
  kotlin("multiplatform")
  application
}

application {
  mainClass.set("diglol.encoding.samples.Samples")
}

kotlin {
  jvm {
    withJava()
  }

  sourceSets {
    commonMain {
      dependencies {
        implementation(projects.encoding)
      }
    }
  }
}
