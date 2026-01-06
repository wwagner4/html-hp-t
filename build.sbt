name := "taschenfahrrad"

version := "1.0"

organization := "net.entelijan"

scalaVersion := "3.7.4"

//5resolvers += "Local Maven Repository" at "file:///" + Path.userHome + "/.m2/repository"

scalacOptions ++= Seq("-deprecation")

Compile / run / fork := true
updateOptions := updateOptions.value.withLatestSnapshots(false)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test"
libraryDependencies += "net.coobird" % "thumbnailator" % "0.4.21"
libraryDependencies += "com.lihaoyi" %% "mainargs" % "0.7.8"
libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.11.6"

//offline := true
