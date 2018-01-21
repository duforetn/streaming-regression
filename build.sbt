import Dependencies._

name := "RegressionApp"
version := "0.0.1"
scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:reflectiveCalls",
  "-deprecation",           // Warn when deprecated API are used
  "-feature",               // Warn for usages of features that should be importer explicitly
  "-unchecked",             // Warn when generated code depends on assumptions
  "-Xlint",                 // Additional warnings (see scalac -Xlint:help)
  "-Xlog-reflective-calls", // Print a message when a reflective method call is generated
  "-Xfuture",               // Turn on future language features
  "-Ywarn-numeric-widen",   // Warn when numeric are widened
  "-Ywarn-dead-code",       // Warn when dead code is identified
  "-Yno-adapted-args",      // Error if an argument list is modified to match the receive
  "-Ywarn-inaccessible",    // Warn about inaccessible types in method signatures
  "-Ywarn-nullary-override",// Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
  "-Ywarn-infer-any",       // Warn when a type argument is inferred to be `Any`.
  "-encoding", "UTF-8"
)
libraryDependencies ++= kafka ++ spark ++ circe ++ breeze ++ log ++ scopt ++ appConfig ++ sparkAvro ++ sparkCSV

resolvers ++= Seq(
  Resolver.bintrayRepo("tabmo", "maven"),
  Resolver.bintrayRepo("scalaz", "releases"),
  Resolver.sonatypeRepo("releases"),
  Resolver.bintrayRepo("dwhjames", "maven"),
  Resolver.sonatypeRepo("public"),
  Resolver.jcenterRepo
)



resolvers ++= Seq(
                  "Maven Central" at "https://repo1.maven.org/maven2/",
                   "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases",
                   "Artima Maven Repository" at "http://repo.artima.com/releases",
                   "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
                   "apache-snapshot" at "https://repository.apache.org/snapshots/"
                 )




