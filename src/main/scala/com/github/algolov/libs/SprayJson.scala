package com.github.algolov.libs

import spray.json._
import DefaultJsonProtocol._
import java.net.URL
import java.util.UUID
import com.github.algolov.{Counters, Link, Listing}

trait SprayJson extends JsonLibrary {
  import SprayJson._

  type JSON = JsValue

  override def parseFromString(jsonStr: String) = JsonParser(jsonStr)
  override def parseToString(json: JsValue)     = json.compactPrint
  override def serialize(listing: Listing)      = listing.toJson(ListingFormat)
  override def deserialize(json: JsValue)       = Option(json.convertTo[Listing])
}

object SprayJson extends DefaultJsonProtocol {

  implicit object  LinkFormat extends RootJsonFormat[Link] {
    def write(l: Link) = JsObject(
      "data" -> JsObject(
        "title" -> JsString(l.title),
        "url" -> JsString(l.url.toString),
        "ups" -> JsNumber(l.stats.ups),
        "downs" -> JsNumber(l.stats.downs),
        "score" -> JsNumber(l.stats.score),
        "num_comments" -> JsNumber(l.stats.num_comments)
      )
    )

    def read(value: JsValue) = {
      value.asJsObject.getFields("data").flatMap (
        _.asJsObject.getFields("title", "url", "ups", "downs", "score", "num_comments") ) match {
          case Seq(JsString(title), JsString(url), JsNumber(ups), JsNumber(downs), JsNumber(score), JsNumber(num_comments)) =>
            Link(title, new URL(url), Counters(ups.toInt, downs.toInt, score.toInt, num_comments.toInt))
          case _ => throw new DeserializationException("Link Expected")
      }
    }
  }

  implicit object ListingFormat extends RootJsonFormat[Listing] {
    def write(l: Listing) = {
      JsObject(
        "data" -> JsObject(
          "children" -> l.data.toJson,
          "before" -> l.before.toJson,
          "after" -> l.after.toJson
        )
      )
    }

    def read(value: JsValue) = {
      value.asJsObject.getFields("data").flatMap { _.asJsObject.getFields("children", "before", "after") } match {
        case Seq(links, before, after) =>
          Listing(
            UUID.randomUUID(),
            links.convertTo[Seq[Link]],
            before.convertTo[Option[String]],
            after.convertTo[Option[String]]
          )
      }
    }
  }

}