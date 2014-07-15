import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.archetypes.ServerLoader._
import NativePackagerKeys._

name := "echo"

version := "1.0SNAPSHOT"

libraryDependencies ++= Seq(
  cache,
  "com.kenshoo" %% "metrics-play" % "0.1.5"
)

play.Project.playJavaSettings

packageArchetype.java_server

serverLoading := SystemV

maintainer := "Jeff Schwartz"

packageSummary := "Echo Play App"

packageDescription := "Echo Play App"

debianPackageDependencies in Debian ++= Seq("openjdk-7-jdk")

rpmVendor := "Jeff Schwartz"

rpmGroup := Some("Foo/Development")

rpmRelease := "1"

rpmUrl := Some("https://github.com/jschwartz73/echo-play2")

rpmLicense := Some("Answers Ahead EULA")

rpmBrpJavaRepackJars := true