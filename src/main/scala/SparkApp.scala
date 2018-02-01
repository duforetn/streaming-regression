import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

//import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._

trait SparkApp extends App {

  val codecConfig = new SparkConf()

  implicit val spark = SparkSession
    .builder()
    .appName("spark-helpers")
    .config(codecConfig)
    .master("local[*]")
    .config("spark.executor.memory", "4096m")
    .config("spark.driver.memory", "200g")
    .config("spark.executor.extraJavaOptions", "-XX:-UseGCOverheadLimit")
    .config("spark.network.timeout", "60s")
    .config("spark.executor.heartbeatInterval", "100s")
    .config("spark.sql.streaming.checkpointLocation", "checkpoint")
    .getOrCreate()

  spark.conf.set("spark.memory.fraction", ".9")
  spark.conf.set("spark.dynamicAllocation.enabled", "true")

  import spark.implicits._

}

