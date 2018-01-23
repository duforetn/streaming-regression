#Using rkafka
library('rkafka')
source('generativeModel.R')
bootstrapserver = "localhost:9092"
topic = "test"

produceStreamingDataSet <- function(n = 1000, interval_min = 1, interval_max = 10, topic, bootstrapserver) {

    producer = rkafka.createProducer(bootstrapserver)
    for (i in 1:n){
        rkafka.send(producer, topic, bootstrapserver, linearGeneration())
        Sys.sleep(runif(1, interval_min, interval_max)*0.001)
    }
    rkafka.closeProducer(producer)

}

#generate a stream
produceStreamingDataSet(interval_max = 100, topic = topic, bootstrapserver = bootstrapserver, n = 100)

