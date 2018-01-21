import com.typesafe.config.ConfigFactory

case class AppConfig (
                       bootstrapServer: String = "localhost:9092",
                       topic: String = "test"
                     )

object AppConfig {

  import net.ceedubs.ficus.Ficus._
  import net.ceedubs.ficus.readers.ArbitraryTypeReader._

  val config = ConfigFactory.load("application.conf").as[AppConfig]("app")
}