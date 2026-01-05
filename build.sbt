name := "taschenfahrrad"

version := "1.0"

organization := "net.entelijan"

scalaVersion := "3.7.4"

//5resolvers += "Local Maven Repository" at "file:///" + Path.userHome + "/.m2/repository"

// scalacOptions ++= Seq("-deprecation")

Compile / run / fork := true
updateOptions := updateOptions.value.withLatestSnapshots(false)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test"
libraryDependencies += "net.coobird" % "thumbnailator" % "0.4.21"
libraryDependencies += "dev.zio" %% "zio-cli" % "0.7.4"

//offline := true
