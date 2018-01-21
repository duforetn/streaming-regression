import java.io.{File, PrintWriter}

import com.typesafe.scalalogging.StrictLogging
import com.databricks.spark.avro._
import com.databricks.spark.csv._
import org.apache.spark.sql.DataFrame

object DeviceIdHistorical extends SparkApp with StrictLogging {

  import spark.implicits._
  val conf = AppConfig.config

  val df = spark
  .readStream
  .format("kafka")
  .option("kafka.bootstrap.servers", conf.bootstrapServer)
  .option("subscribe", conf.topic)
  .load()
  df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
    .as[(String, String)]


  spark.stop
}
