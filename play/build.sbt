import com.typesafe.config._
import scala.language.postfixOps

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()


name := """conciliefacil-portal"""
organization in ThisBuild := "hackaton4.h24"
version := conf.getString("app.version")


lazy val root = (project in file(".")).enablePlugins(PlayScala)
scalaVersion := "2.11.8"


// Scala Compiler Options
scalacOptions in ThisBuild ++= Seq(
    "-target:jvm-1.8",
    "-encoding", "UTF-8",
    "-deprecation", // warning and location for usages of deprecated APIs
    "-feature", // warning and location for usages of features that should be imported explicitly
    "-unchecked", // additional warnings where generated code depends on assumptions
    "-Xlint", // recommended additional warnings
    "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
    "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
    "-Ywarn-inaccessible",
    "-Ywarn-dead-code"
)


libraryDependencies ++= Seq(
    ws,
    filters,
    guice,
    ehcache,
    "com.typesafe.play" %% "play-json" % "2.6.+",
    "net.codingwell" %% "scala-guice" % "4.+"
)

//lazy val akkaVersion = "2.5.3"
//lazy val akkaHttpVersion = "10.0.7"



fork in run := true

