package part2dataframes

import org.apache.spark.sql.types._
import org.apache.spark.sql.{SaveMode, SparkSession}

object DataSources extends App {

  val spark =
    SparkSession
      .builder()
      .appName("Data Sources and Formats")
      .config("spark.master", "local")
      .getOrCreate()

  val carsSchema = StructType(Array(
    StructField("Name", StringType),
    StructField("Miles_per_Gallon", DoubleType),
    StructField("Cylinders", LongType),
    StructField("Displacement", DoubleType),
    StructField("Horsepower", LongType),
    StructField("Weight_in_lbs", LongType),
    StructField("Acceleration", DoubleType),
    StructField("Year", DateType),
    StructField("Origin", StringType)
  ))

  /*
    Reading a DF needs:
      - format
      - schema or .option("inferSchema", "true")
      - path
      - zero or more options
   */
  val carsDF = spark.read
    .format("json")
    .schema(carsSchema)
    .option("mode", "failFast") // dropMalformed, permissive (default)
    .option("path", "src/main/resources/data/cars.json")
    .load() // alternate way of loading with above line

  // options Map
  val carsDFWithOptionsMap = spark.read
    .format("json")
    .options(Map(
      "mode" -> "failFast",
      "path" -> "src/main/resources/data/cars.json",
      "inferSchema" -> "true"
    ))
    .load()
  /*
    Writing DFs
    - format
    - save mode = override, append, ignore, errorIfExists
    - path
    - zero or more options
   */
  carsDF.write
    .format("json")
    .mode(SaveMode.Overwrite) // more typesafe than "overwrite"
    .save("src/main/resources/data/cars_dupe.json")


  // JSON flags
  spark.read
    .options(Map(
      "dateFormat" -> "YYYY-MM-DD", // couple with schema; if Spark fails parsing, it will put null
      "allowSingleQuotes" -> "true",
      "compression" -> "uncompressed" // default value (else bzip2, gzip, lz4, snappy, deflate)
    )).json("src/main/resources/data/cars.json") // .json is like "load" that specifies json

  // CSV flags
  val stocksSchema = StructType(Array(
    StructField("symbol", StringType),
    StructField("data", DateType),
    StructField("price", DoubleType)
  ))

  spark.read
    .schema(stocksSchema)
    .options(Map(
      "dateFormat" -> "MMM dd YYYY",
      "header" -> "true",
      "sep" -> ",",
      "nullValue" -> ""
    ))
    .csv("src/main/resources/data/stocks.csv")

  // Parquet flags - parquet is default
  carsDF.write
    .mode(SaveMode.Overwrite)
    .save("src/main/resources/data/cars.parquet")

  // Text files
  spark.read.text("src/main/resources/data/sampleTextFile.txt").show()

  // Reading from a remote DB
  val employeesDF = spark.read
    .format("jdbc")
    .option("driver", "org.postgresql.Driver")
    .option("url", "jdbc:postgresql://localhost:5432/rtjvm")
    .option("user", "docker")
    .option("password", "docker")
    .option("dbtable", "public.employees")
    .load()
  employeesDF.show()

  /*
   EXERCISE: Read movies dataframe and write as
    1) Tab-separated values
    2) Snappy parquet
    3) table "public.movies" table in the Postgres DB
   */

  val moviesDF = spark.read.json("src/main/resources/data/movies.json")

  moviesDF.write
    .format("csv")
    .mode(SaveMode.Overwrite)
    .option("header", "true")
    .option("sep", "\t")
    .save("src/main/resources/data/movies.csv")

  moviesDF.write.mode(SaveMode.Overwrite).save("src/main/resources/data/movies.parquet")

  moviesDF.write
    .format("jdbc")
    .option("driver", "org.postgresql.Driver")
    .option("url", "jdbc:postgresql://localhost:5432/rtjvm")
    .option("user", "docker")
    .option("password", "docker")
    .option("dbtable", "public.movies")
    .save()
}
