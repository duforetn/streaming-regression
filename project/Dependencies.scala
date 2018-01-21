import sbt._

object Dependencies {

  private object versions {
    val breezeV = "0.12"
    val circeV = "0.4.1"
    val sparkV = "2.1.0"
    val confV = "1.2.3"
    val scoptV = "3.5.0"
    val configV = "1.3.1"
  }

  import versions._

  val kafka = Seq(
    ("org.apache.kafka" %% "kafka" % "1.0.0") exclude("org.slf4j", "slf4j-log4j12") exclude("log4j", "log4j") exclude("junit", "junit"),
    ("org.apache.spark" %% "spark-sql-kafka-0-10" % sparkV)
    .exclude("log4j", "log4j")
    .exclude("org.slf4j", "log4j")
    .exclude("org.slf4j", "slf4j-log4j12")
  )

  val spark = Seq(
    ("org.apache.spark" %% "spark-mllib" % sparkV)
    .exclude("log4j", "log4j")
    .exclude("org.slf4j", "log4j")
    .exclude("org.slf4j", "slf4j-log4j12")
  )

  val sparkAvro = Seq("com.databricks" %% "spark-avro" % "3.2.0")

  val sparkCSV = Seq("com.databricks" %% "spark-csv" % "1.5.0")

  val circe = Seq(
    "io.circe" %% "circe-core" % circeV,
    "io.circe" %% "circe-generic" % circeV,
    "io.circe" %% "circe-parser" % circeV
  )

  val breeze = Seq(
    "org.scalanlp" %% "breeze" % breezeV,
    "org.scalanlp" %% "breeze-natives" % breezeV,
    "org.scalanlp" %% "breeze-viz" % breezeV
  )

  val log = Seq(
     "org.slf4j" % "log4j-over-slf4j" % "1.7.13",
     "ch.qos.logback" % "logback-classic" % "1.1.7",
     "net.logstash.logback" % "logstash-logback-encoder" % "3.3",
     "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"
  )

  val scopt = Seq(
    "com.github.scopt" %% "scopt" % scoptV
  )

  val appConfig = Seq(
    "com.iheart" %% "ficus" % "1.2.3",
    "com.typesafe" % "config" % configV
  )
}
