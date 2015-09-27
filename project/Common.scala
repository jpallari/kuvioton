import sbt.Keys._
import sbt._

object Common {
  val settings = Defaults.defaultSettings ++ Seq(
    version := "1.0",
    scalaVersion := "2.11.7",
    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots")
    )
  )
}

object Dependencies {
  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  val shapeless = "com.chuusai" %% "shapeless" % "2.3.0-SNAPSHOT"
//  val shapeless = "com.chuusai" %% "shapeless" % "2.3.0-SNAPSHOT"
}
