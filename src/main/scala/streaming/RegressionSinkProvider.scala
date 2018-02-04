package streaming

import org.apache.spark.sql.sources.StreamSinkProvider
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.{DataFrame, SQLContext}

/**
  From Holden Karau's High Performance Spark
  https://github.com/holdenk/polomarcus.spark-structured-streaming-ml/blob/master/src/main/scala/com/high-performance-polomarcus.spark-examples/structuredstreaming/CustomSink.scala#L66
  *
  */
class RegressionSinkProvider extends StreamSinkProvider {
  override def createSink(sqlContext: SQLContext,
                          parameters: Map[String, String],
                          partitionColumns: Seq[String],
                          outputMode: OutputMode): RegressionSink = {
    new RegressionSink()
  }
}