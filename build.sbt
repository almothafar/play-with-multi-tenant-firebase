name := """multi-tenant"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.11"


libraryDependencies ++= Seq(
  evolutions,
  javaJdbc,
  cache,
  javaWs,
  filters,
  "com.google.firebase" % "firebase-admin" % "4.1.7"
)