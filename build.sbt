import Dependencies._

name := """kuvioton"""

lazy val adt = project.settings(Common.settings)

lazy val conversion = project.settings(Common.settings)

lazy val main = project.dependsOn(adt, conversion).settings(Common.settings)

lazy val root = project
  .in(file("."))
  .aggregate(adt, conversion, main)
  .settings(Common.settings)

