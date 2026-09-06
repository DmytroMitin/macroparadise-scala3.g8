import macroparadise.sbt.MacroParadiseIntegration
import macroparadise.sbt.MacroParadisePrecompiledPlugin.autoImport._

val supportedScalaVersions = Set("3.3.8", "3.8.4", "3.9.0")
val selectedScalaVersion = {
  val value = "$scala_version$"
  require(
    supportedScalaVersions(value),
    "scala_version must be one of: " + supportedScalaVersions.toSeq.sorted.mkString(", ")
  )
  value
}

val mpVersion = {
  val value = "$macroparadise_version$"
  require(value == "0.1.1", "macroparadise_version must be exactly 0.1.1")
  value
}

ThisBuild / organization := "$organization$"
ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := selectedScalaVersion
ThisBuild / publish / skip := true

val mpApi =
  ("com.github.dmytromitin" % "macroparadise-scala3-plugin-api" % mpVersion)
    .cross(CrossVersion.full)

lazy val macroAnnotations = (project in file("macro-annotations"))
  .settings(
    name := "macro-annotations",
    libraryDependencies += mpApi
  )

lazy val macroHandlers = (project in file("macro-handlers"))
  .settings(
    name := "macro-handlers",
    libraryDependencies ++= Seq(
      mpApi,
      "org.scala-lang" %% "scala3-compiler" % scalaVersion.value
    )
  )

lazy val core = (project in file("core"))
  .dependsOn(macroAnnotations)
  .settings(
    name := "core",
    MacroParadiseIntegration.precompiledProjects(macroAnnotations, macroHandlers)
  )
  .enablePlugins(macroparadise.sbt.MacroParadisePrecompiledPlugin)
  .settings(macroParadiseCompilerProductVersion := mpVersion)

lazy val root = (project in file("."))
  .aggregate(macroAnnotations, macroHandlers, core)
  .settings(name := "$name;format="norm"$")
