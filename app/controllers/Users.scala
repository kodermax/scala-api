package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase, ScalaObservable}
import play.api._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.concurrent.duration._

/**
  * Created by Karpychev on 29.12.2016.
  */
object Users extends Controller {
  def index = Action.async {
    val mongoClient: MongoClient = MongoClient("mongodb://127.0.0.1:27017/")
    val database: MongoDatabase = mongoClient.getDatabase("portal")
    val collection: MongoCollection[Document] = database.getCollection("users")
    collection.find().collect().subscribe((results: Seq[Document]) => println(s"Found BsonDocuments: #${results.size}"))
    collection.find().collect().toFuture().map(seq => Ok(Document("users" -> seq).toJson))
  }
}
