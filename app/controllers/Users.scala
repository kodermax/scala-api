package controllers

import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}
import play.api._
import play.api.libs.json.Json
import play.api.mvc._

/**
  * Created by Karpychev on 29.12.2016.
  */
object Users extends Controller {
  def index = Action {
    val mongoClient: MongoClient = MongoClient("mongodb://corp.pharm.local:27017")
    val database: MongoDatabase = mongoClient.getDatabase("portal")
    val collection: MongoCollection[Document] = database.getCollection("users")
    val results = collection.find().limit(30).collect()
    val json = Json.obj(
      "name" -> "Maksim",
      "age" -> "30",
      "created" -> new java.util.Date().getTime()
    )
    Ok(json)
  }
}
