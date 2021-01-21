package part2dataframes

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, column, desc, expr}

object ColumnsAndExpressions extends App {


  val spark = SparkSession.builder()
    .appName("DF Columns and Expressions")
    .config("spark.master", "local")
    .getOrCreate()

  val carsDF = spark.read
    .option("inferSchema", "true")
    .json("src/main/resources/data/cars.json")

  // Columns
  val firstColumn = carsDF.col("Name")

  // selecting (projecting)
  val carNamesDF = carsDF.select(firstColumn)

  // various select methods

  import spark.implicits._

  carsDF.select(
    carsDF.col("Name"),
    col("Acceleration"),
    column("Weight_in_lbs"),
    'Year, // Scala Symbol, auto-converted to column
    $"Horsepower", // fancier interpolated string, returns a Column object
    expr("Origin") // EXPRESSION
  )

  // select with plain column names
  carsDF.select("Name", "Year")

  // EXPRESSIONS
  val simplestExpression = carsDF.col("Weight_in_lbs")
  val weightInKgExpression = carsDF.col("Weight_in_lbs") / 2.2

  val carsWithWeightsDF = carsDF.select(
    col("Name"),
    col("Weight_in_lbs"),
    weightInKgExpression.as("Weight_in_kg"),
    expr("Weight_in_lbs / 2.2").as("Weight_in_kg_2")
  )

  // selectExpr
  val carsWithSelectExprWeightsDF = carsDF.selectExpr(
    "Name",
    "Weight_in_lbs",
    "Weight_in_lbs / 2.2"
  )

  // DF processing

  // adding a column
  val carsWithKg3DF = carsDF.withColumn("Weight_in_kg_3", col("Weight_in_lbs") / 2.2)
  // renaming a column
  val carsWithColumnRenamed = carsDF.withColumnRenamed("Weight_in_lbs", "Weight in pounds")
  // careful with column names
  carsWithColumnRenamed.selectExpr("`Weight in pounds`")
  // remove a column
  carsWithColumnRenamed.drop("Cylinders", "Displacement")

  // filtering
  val europeanCarsDF = carsDF.filter(col("Origin") =!= "USA")
  val europeanCarsDF2 = carsDF.where(col("Origin") =!= "USA")
  // filtering with expression strings
  val americanCarsDF = carsDF.filter("Origin = 'USA'")
  // chain filters
  val americanPowerfulCarsDF = carsDF.filter(col("Origin") === "USA").filter(col("Horsepower") > 150)
  val americanPowerfulCarsDF2 = carsDF.filter(col("Origin") === "USA" and col("Horsepower") > 150)
  val americanPowerfulCarsDF3 = carsDF.filter("Origin = 'USA' and Horsepower > 150")

  // unioning - adding more rows
  val moreCarsDF = spark.read.option("inferSchema", "true").json("src/main/resources/data/more_cars.json")
  val allCarsDF = carsDF.union(moreCarsDF)
  //  allCarsDF.write.format("json").save("src/main/resources/data/all_the_cars.json")

  // distinct values
  val allCountriesDF = carsDF.select("Origin").distinct()
  //  allCountriesDF.show()

  // Exercises

  // #1 grab columns in different ways
  val moviesDF = spark.read.option("inferSchema", "true").json("src/main/resources/data/movies.json")


  val moviesReleaseDateDF = moviesDF.select("Title", "Release_Date")
  val moviesWorldWideGrossDF = moviesDF.select(col("Title"), col("Worldwide_Gross"))
  //  moviesWorldWideGrossDF.orderBy(desc("Worldwide_Gross")).show()

  //  moviesDF.selectExpr("Title", "Release_Date").show()

  // 2 - total profit
  val moviesProfitDF = moviesDF.select(col("Title"), col("US_Gross"), col("Worldwide_Gross"), col("US_DVD_Sales"),
    (col("US_Gross") + col("Worldwide_Gross")).as("Total_Profits"))
  val moviesProfit2DF = moviesDF.select("Title", "US_Gross", "Worldwide_Gross")
    .withColumn("Total_Profits", col("US_Gross") + col("Worldwide_Gross"))

  // 3 - comedies with ratings above 6 on IMDB
  val filteredComediesDF = moviesDF.select("Title", "IMDB_Rating")
    .where(col("Major_Genre") === "Comedy" && col("IMDB_Rating") >= 6.0)
    .orderBy(desc("IMDB_Rating"))

}
