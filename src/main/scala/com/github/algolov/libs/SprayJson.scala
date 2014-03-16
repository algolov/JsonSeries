package com.github.algolov.libs

import spray.json._
import DefaultJsonProtocol._
import java.net.URL
import java.util.UUID
import com.github.algolov.{Counters, Link, Listing}

trait SprayJson extends JsonLibrary {
  import SprayJson._

  type JSON = JsValue

  override def parseFromString(jsonStr: String) = ???
  override def parseToString(json: JsValue)     = ???
  override def serialize(listing: Listing)      = ???
  override def deserialize(json: JsValue)       = ???
}

object SprayJson {

}