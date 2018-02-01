package streaming

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.execution.streaming.Sink

class RegressionSink() extends Sink with LazyLogging {
  private val spark =  SparkSession
    .builder()
    .getOrCreate()

  import spark.implicits._
  import org.apache.spark.sql.functions._

  private def modelMethod(df: DataFrame) = {
    logger.error(s"modelMethod -  df.count() : ${df.count()}")
    df.show()
  }

  /*
   * As per SPARK-16020 arbitrary transformations are not supported, but
   * converting to an RDD allows us to do magic.
   */
  override def addBatch(batchId: Long, df: DataFrame) = {
    logger.error(s"RegressionSink -  batchId : ${batchId}")
    modelMethod(df)
  }
}

