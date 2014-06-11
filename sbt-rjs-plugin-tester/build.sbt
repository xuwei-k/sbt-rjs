import RjsKeys._
import WebJs._

lazy val root = (project in file(".")).enablePlugins(SbtWeb)

JsEngineKeys.engineType := JsEngineKeys.EngineType.Node

libraryDependencies ++= Seq(
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.webjars" % "underscorejs" % "1.6.0-1"
)

pipelineStages := Seq(rjs)

RjsKeys.mainConfig := "build"

//buildProfile := JS.Object("locale" -> "en-au")

//modules += JS.Object("name" -> "foo/bar/bip", "exclude" -> Seq("foo/bar/bop"))
