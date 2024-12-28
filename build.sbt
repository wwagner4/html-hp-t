name := "taschenfahrrad"

version := "1.0"

organization := "net.entelijan"

scalaVersion := "3.6.2"

//5resolvers += "Local Maven Repository" at "file:///" + Path.userHome + "/.m2/repository"

updateOptions := updateOptions.value.withLatestSnapshots(false)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"
libraryDependencies += "net.coobird" % "thumbnailator" % "0.4.8"
libraryDependencies += "org.planet42" %% "laika-core" % "0.18.1"

//offline := true
