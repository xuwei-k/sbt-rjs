lazy val main = (project in file("."))
  .enablePlugins(SbtWeb)
  .dependsOn(a)
  .settings(
    libraryDependencies += "org.webjars" % "requirejs" % "2.1.11-1",
    pipelineStages := Seq(rjs)
  )

lazy val a = (project in file("modules/a"))
  .enablePlugins(SbtWeb)
  .dependsOn(b)

lazy val b = (project in file("modules/b"))
  .enablePlugins(SbtWeb)
