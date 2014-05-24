lazy val root = (project in file(".")).enablePlugins(SbtWeb)

libraryDependencies ++= Seq(
  "org.webjars" % "requirejs" % "2.1.11-1"
)

pipelineStages := Seq(rjs)

val checkCdn = taskKey[Unit]("Check the CDN")

checkCdn := {
  if (RjsKeys.paths.value != Map("requirejs" -> "http://cdn.jsdelivr.net/webjars/requirejs/2.1.11-1")) {
    sys.error(s"${RjsKeys.paths} is not what we expected")
  }
}