package dev.christiano.app

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger

object Main extends App {

  val datalakePath = "/home/anderson/var/datalake/iceberg/"

  val spark = SparkSession
    .builder()
    .appName("PulsarPlaygroundUsers")
    .master("local[*]")
    .config(
      "spark.jars.packages",
      "io.streamnative.connectors:pulsar-spark-connector_2.12:3.1.1.3"
    )
    .getOrCreate()

  val userDF = spark.readStream
    .format("pulsar")
    .option("service.url", "pulsar://localhost:6650")
    .option("admin.url", "http://localhost:8080")
    .option("topic", "persistent://public/default/users-topic")
    .load()

  val streamQuery = userDF
    .select("firstName", "lastName", "city", "country")
    .writeStream
    .format("iceberg")
    .trigger(Trigger.ProcessingTime("60 seconds"))
    .option("path", datalakePath + "warehouse/db/user")
    .option("checkpointLocation", datalakePath + "checkpoint")
    .outputMode("append")
    .start()
}
