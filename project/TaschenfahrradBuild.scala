import sbt._
import Keys._

object TaschenfahrradBuild extends Build {

  // Constant values
  object D {

    val version = "1.0-SNAPSHOT"

    val scalaVersion = "2.11.8"
  }

  // Settings
  object S {

    lazy val settings =
        Seq(
          version := D.version,
          scalaVersion := D.scalaVersion,
          organization := "net.entelijan",
		      libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.5" % "test",
          libraryDependencies += "org.apache.poi" % "poi-ooxml" % "3.15",
          libraryDependencies += "org.planet42" %% "laika-core" % "0.6.0"
        )

  }

  // Project definitions
  lazy val root = Project(id = "taschenfahrrad-generator",
    base = file("."), //
    settings = S.settings)
}

