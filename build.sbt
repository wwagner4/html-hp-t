name := "taschenfahrrad"

version := "0.1-SNAPSHOT"

organization := "net.entelijan"

scalaVersion := "2.13.10"

//5resolvers += "Local Maven Repository" at "file:///" + Path.userHome + "/.m2/repository"

updateOptions := updateOptions.value.withLatestSnapshots(false)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test
libraryDependencies += "net.coobird" % "thumbnailator" % "0.4.19"
libraryDependencies += "org.planet42" %% "laika-core" % "0.19.0"

offline := true