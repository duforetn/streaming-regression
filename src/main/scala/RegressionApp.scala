import java.io.{File, PrintWriter}

import com.typesafe.scalalogging.StrictLogging
import org.apache.spark.streaming._
import com.databricks.spark.avro._
import com.databricks.spark.csv._
import models.InputData
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DoubleType, StringType, StructType}

object StreamingRegression extends SparkApp with StrictLogging {

  import spark.implicits._
  val conf = AppConfig.config

  val df: DataFrame = spark
  .readStream
  .format("kafka")
  .option("kafka.bootstrap.servers", conf.bootstrapServer)
  .option("subscribe", conf.topic)
  .load()

  val newData = new StructType()
    .add("y", DoubleType)
    .add("var1", DoubleType)
    .add("var2", DoubleType)

  val yCol = new StructType()
    .add("y", DoubleType)

  val featureCol = new StructType()
    .add("var1", DoubleType)
    .add("var2", DoubleType)

  val df1 = df.withColumn("newData", from_json($"value".cast(StringType), newData))
              .withColumn("label", from_json($"value".cast(StringType), yCol))
              .withColumn("features", from_json($"value".cast(StringType), featureCol))
              .select("newData", "label", "features")

  val streamDebug = df1.writeStream
    .format("console")
    .queryName("debug")
    .start()

  val mlStreaming = df1.writeStream
    .format("streaming.RegressionSinkProvider")
    .outputMode("update")
    .queryName("RegressionStreaming")
    .start()

  //Wait for all streams to finish
  spark.streams.awaitAnyTermination()
}
