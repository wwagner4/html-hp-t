name := "taschenfahrrad"

version := "0.1-SNAPSHOT"

organization := "net.entelijan"

scalaVersion := "2.12.10"

//5resolvers += "Local Maven Repository" at "file:///" + Path.userHome + "/.m2/repository"

updateOptions := updateOptions.value.withLatestSnapshots(false)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "net.coobird" % "thumbnailator" % "0.4.8"
libraryDependencies += "org.planet42" %% "laika-core" % "0.12.0"


offline := true