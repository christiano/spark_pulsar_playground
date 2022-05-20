
scalaVersion := "2.12.15"
name := "spark_pulsar_playground"
organization := "dev.christiano"
version := "1.1"
libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "3.2.1",
    "org.apache.spark" %% "spark-sql" % "3.2.1"
)

assemblyMergeStrategy in assembly := {
      case PathList("META-INF", "eclipse.inf") => MergeStrategy.last
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
}
