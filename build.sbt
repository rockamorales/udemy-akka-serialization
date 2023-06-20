ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val akkaVersion = "2.8.2"
lazy val slickVersion = "3.4.1"

lazy val root = (project in file("."))
  .settings(
    name := "udemy-akka-serialization",
    libraryDependencies ++= Seq(
      /************************************************************
       * Akka core
       ************************************************************/
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-remote" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
      "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
      "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion,

      "com.lightbend.akka" %% "akka-persistence-jdbc" % "5.2.1",
      "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion,
      "com.typesafe.slick" %% "slick" % slickVersion,
      "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,

      "io.aeron" % "aeron-driver" % "1.41.4",

      /** **********************************************************
       * Serialization frameworks
       * ********************************************************** */
      "com.esotericsoftware.kryo" % "kryo5" % "5.5.0",
      "org.apache.avro" % "avro" % "1.11.1",
      "com.google.protobuf" % "protobuf-java" % "3.23.3",
      "io.spray" %% "spray-json" % "1.3.6",

      /*************************************************************
       * Persistent stores support
       *************************************************************/

      //local levelDB stores
      "org.iq80.leveldb" % "leveldb" % "0.12",
      "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",

      // JDBC with postgreSQL
      "org.postgresql" % "postgresql" % "42.6.0",

      // Cassandra
      "com.typesafe.akka" %% "akka-persistence-cassandra" % "1.1.1",
      "com.typesafe.akka" %% "akka-persistence-cassandra-launcher" % "1.1.1" % Test
    )
  )
