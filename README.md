sbt-rjs
=======

[![Build Status](https://api.travis-ci.org/sbt/sbt-rjs.png?branch=master)](https://travis-ci.org/sbt/sbt-rjs)

An SBT plugin to perform [RequireJs optimization](http://requirejs.org/docs/optimization.html).

To use this plugin use the addSbtPlugin command within your project's `plugins.sbt` file:

    addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.0-RC3")

Your project's build file also needs to enable sbt-web plugins. For example with build.sbt:

    lazy val root = (project in file(".")).enablePlugins(SbtWeb)

As with all sbt-web asset pipeline plugins you must declare their order of execution e.g.:

```scala
pipelineStages := Seq(rjs)
```

WebJars are treated specially. If a path is referenced that is part of a path belong to a Webjar then the `webjarCdn`
setting is used to translate it to the CDN. This is all fully automatic and provided as part of a [buildWriter](http://www.ericfeminella.com/blog/2012/03/24/preprocessing-modules-with-requirejs-optimizer/)
function. Furthermore if a `.bin` or `-bin` equivalent of the resource is available then it is used. The end result is
that all WebJar sourced resources are located via a CDN along with their minified versions.

RequireJs optimization [permits build profiles](http://requirejs.org/docs/optimization.html#basics)
to be declared that specify what needs to be done. A standard build profile for the RequireJS optimizer is provided.
You are able to use and/or customize settings already made, and add your own. Here are a list of relevant settings and
their meanings:

Option                  | Description
------------------------|------------
appBuildProfile         | The project build profile contents.
appDir                  | The top level directory that contains your app js files. In effect, this is the source folder that rjs reads from.
baseUrl                 | The dir relative to the assets or public folder where js files are housed. Will default to "js", "javascripts" or "." with the latter if the other two cannot be found.
buildProfile            | Build profile key -> value settings in addition to the defaults supplied by appBuildProfile. Any settings in here will also replace any defaults.
buildWriter             | The project build writer JS that is responsible for writing out source files in rjs.
dir                     | By default, all modules are located relative to this path. In effect this is the target directory for rjs.
generateSourceMaps      | By default, source maps are generated.
mainModule              | By default, 'main' is used as the module.
modules                 | The json array of modules.
optimize                | The name of the optimizer, defaults to uglify2.
paths                   | A set of RequireJS path mappings. By default all WebJar libraries are made available from a CDN and their mappings can be found here (unless the cdn is set to None).
preserveLicenseComments | Whether to preserve comments or not. Defaults to false given source maps (see http://requirejs.org/docs/errors.html#sourcemapcomments).
webJarCdns              | CDNs to be used for locating WebJars. By default "org.webjars" is mapped to "jsdelivr".
webJarModuleIds         | A sequence of webjar module ids to be used.

Supposing that your application does not use "main.js" as its main entry point and instead uses `app.js`:

```scala
RjsKeys.mainModule := "app"
```

(note the absence of the file extension).

If you wish to add a property that this plugin does not provide, but is available to rjs then you can provide a map
of additional properties. Supposing that you wish to specify the locale to be used:

```scala
import WebJs._
import RjsKeys._

...

buildProfile := Map("locale" -> j"en-au")
```

The `j` character preceding the string value takes a String and converts it into a quoted JavaScript string literal. `buildProfile` is a `Map[String, JS]` where
`JS` is a type that holds JavaScript expressions. `WebJs._` is required to be imported.

`WebJs` also has functions to convert maps and sequences to the JS type via a `toJS` method. For example,
[given an example from the rjs documentation](https://github.com/jrburke/r.js/blob/master/build/example.build.js#L388)
you can express:

```scala
import WebJs._
import RjsKeys._

...

modules += Map("name" -> j"foo/bar/bip", "exclude" -> Seq(j"foo/bar/bop").toJS)
```

The plugin is built on top of [JavaScript Engine](https://github.com/typesafehub/js-engine) which supports different JavaScript runtimes.

&copy; Typesafe Inc., 2014
