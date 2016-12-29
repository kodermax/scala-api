package models

/**
  * Created by ekubyshin on 29/12/2016.
  */
import org.mongodb.scala.{MongoClient, MongoDatabase}

object MongodbConnection {
  val mongoClient: MongoClient = MongoClient("mongodb://127.0.0.1:27017/")
  val database: MongoDatabase = mongoClient.getDatabase("portal")
}
