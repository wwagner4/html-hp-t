name := "taschenfahrrad"

version := "0.1-SNAPSHOT"

organization := "net.entelijan"

scalaVersion := "2.12.8"

//5resolvers += "Local Maven Repository" at "file:///" + Path.userHome + "/.m2/repository"

updateOptions := updateOptions.value.withLatestSnapshots(false)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.planet42" %% "laika-core" % "0.10.0"
libraryDependencies += "net.coobird" % "thumbnailator" % "0.4.9-SNAPSHOT"

offline := true