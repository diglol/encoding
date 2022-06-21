import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

buildscript {
  repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
  }
  dependencies {
    classpath(libs.kotlin.gradle.plugin)
    classpath(libs.mavenPublish.gradle.plugin)
    classpath(libs.benchmark.gradle.plugin)
  }
}

allprojects {
  group = "com.diglol.encoding"
  version = "0.1.0-SNAPSHOT"

  repositories {
    mavenCentral()
    google()
  }
}

subprojects {
  tasks.withType(Test::class).configureEach {
    testLogging {
      if (System.getenv("CI") == "true") {
        events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.PASSED)
      }
      exceptionFormat = TestExceptionFormat.FULL
    }
  }
}

allprojects {
  plugins.withId("com.vanniktech.maven.publish.base") {
    configure<PublishingExtension> {
      repositories {
        maven {
          name = "testMaven"
          url = file("${rootProject.buildDir}/testMaven").toURI()
        }
      }
    }
    configure<MavenPublishBaseExtension> {
      publishToMavenCentral(SonatypeHost.DEFAULT)
      signAllPublications()
      pom {
        description.set("Diglol Encoding provides implementations of common encodings for Kotlin Multiplatform.")
        name.set(project.name)
        url.set("https://github.com/diglol/encoding/")
        licenses {
          license {
            name.set("The Apache Software License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            distribution.set("repo")
          }
        }
        developers {
          developer {
            id.set("diglol")
            name.set("Diglol")
            url.set("https://github.com/diglol/")
          }
        }
        scm {
          url.set("https://github.com/diglol/encoding/")
          connection.set("scm:git:https://github.com/diglol/encoding.git")
          developerConnection.set("scm:git:ssh://git@github.com/diglol/encoding.git")
        }
      }
    }
  }
}

