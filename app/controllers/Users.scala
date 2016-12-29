package controllers

import models.MongodbConnection
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
    val collection: MongoCollection[Document] = MongodbConnection.database.getCollection("users")
    collection.find().collect().toFuture().map(seq => Ok(Document("users" -> seq).toJson))
  }
}
