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

  val df1 = df.select(
    get_json_object($"value".cast("string"), "$.y").alias("y").cast(DoubleType),
    get_json_object($"value".cast("string"), "$.var1").alias("var1").cast(DoubleType),
    get_json_object($"value".cast("string"), "$.var2").alias("var2").cast(DoubleType),
    get_json_object($"value".cast("string"), "$.var3").alias("var3").cast(DoubleType),
    get_json_object($"value".cast("string"), "$.var4").alias("var4").cast(DoubleType),
    get_json_object($"value".cast("string"), "$.var5").alias("var5").cast(DoubleType),
    get_json_object($"value".cast("string"), "$.var6").alias("var6").cast(DoubleType),
    get_json_object($"value".cast("string"), "$.var7").alias("var7").cast(DoubleType),
    get_json_object($"value".cast("string"), "$.var8").alias("var8").cast(DoubleType),
    get_json_object($"value".cast("string"), "$.var9").alias("var9").cast(DoubleType),
    get_json_object($"value".cast("string"), "$.var10").alias("var10").cast(DoubleType)
  )

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
