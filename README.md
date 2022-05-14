# Spark Pulsar Playground

This is a dummy playground project to demonstrate the integration between Apache Spark and Apache Pulsar. It generates `parquet` files as output, simulating a simple Data Lake (ingestion, streaming, lake). 

## Components

    1. Apache Pulsar (a standalone cluster up and running)
    2. Apache Spark (check below how to setup a standalone cluster)
    3. Pulsar Spark Connector

You can check this repository [Apache Pulsar Playground](https://github.com/christiano/pulsar_playground) as reference and a sample for the `producer` and the `consumer`, a sample application in Java. 

## Setup Apache Spark standalone cluster

This project has been tested with Apache Spark 3.2.1

    1. Download the Apache Spark 3.2.1
    2. Start the master: `./sbin/start-master.sh`
    3. Start the worker: `./sbin/start-worker.sh spark://localhost.localdomain:7077` (the URL may differ, check the logs from the master to get the correct url)

## Build the jar

Edit the `Main.scala` and fix the __path__ and the __checkpointLocation__ options.

Execute this command on the root directory of the project:

```
sbt package
```

If the result is `success`, the __jar__ will be located into `target/scala-2.12/spark_pulsar_playground_2.12-1.0.jar`

## Submit the jar to the standalone Spark cluster

Submit using this command:

```
spark-submit --master spark://localhost.localdomain:7077 --packages io.streamnative.connectors:pulsar-spark-connector_2.12:3.1.1.3 spark_pulsar_playground_2.12-1.0.jar
```

Once again, the master URL may differ, check the logs from the Standalone cluster. 

## How to invoke the spark-shell with support to Pulsar

```
spark-shell  --packages io.streamnative.connectors:pulsar-spark-connector_2.12:3.1.1.3
```

The same for pyspark:

```
pyspark  --packages io.streamnative.connectors:pulsar-spark-connector_2.12:3.1.1.3
```
