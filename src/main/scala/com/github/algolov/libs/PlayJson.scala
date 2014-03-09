package com.github.algolov.libs

import play.api.libs.json._
import play.api.libs.functional.syntax._
import java.net.URL
import java.util.UUID
import com.github.algolov.{Counters, Link, Listing}

trait PlayJson extends JsonLibrary {
  import PlayJson._

  override type JSON = JsValue

  override def parseFromString(jsonStr: String) = Json.parse(jsonStr)
  override def parseToString(json: JsValue)        = Json.stringify(json)
  override def serialize(listing: Listing)      = Json.toJson[Listing](listing)
  override def deserialize(json: JsValue)          = Json.fromJson[Listing](json).asOpt
}

object PlayJson {

  implicit val linkFormat: Format[Link] = (
    (__ \ "data" \ "title").format[String] ~
    (__ \ "data" \ "url").format[String].inmap[URL](new URL(_), _.toString) ~
    (__ \ "data").format[Counters](Json.format[Counters])
  )(Link.apply, unlift(Link.unapply))

  implicit val listingFormat: Format[Listing] = (
    (__ \ "data" \ "children").format[Seq[Link]] ~
    (__ \ "data" \ "before").formatNullable[String] ~
    (__ \ "data" \ "after").formatNullable[String]
    )(Listing(UUID.randomUUID(), _, _, _), l => (l.data, l.before, l.after))

}