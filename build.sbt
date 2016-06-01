sbtPlugin := true

organization := "com.typesafe.sbt"

name := "sbt-rjs"

version := "1.0.8-SNAPSHOT"

scalaVersion := "2.10.6"

scalacOptions += "-feature"

libraryDependencies ++= Seq(
  "org.webjars" % "rjs" % "2.2.0"
)

addSbtPlugin("com.typesafe.sbt" % "sbt-js-engine" % "1.1.4")

publishMavenStyle := false

publishTo := {
  if (isSnapshot.value) Some(Classpaths.sbtPluginSnapshots)
  else Some(Classpaths.sbtPluginReleases)
}

scriptedSettings

scriptedLaunchOpts <+= version apply { v => s"-Dproject.version=$v" }
