package com.github.algolov.libs

import argonaut._, Argonaut._
import java.net.URL
import java.util.UUID
import com.github.algolov.{Counters, Link, Listing}
import scalaz._, Scalaz._

trait ArgonautLibrary extends JsonLibrary {
  import ArgonautLibrary._

  type JSON = Json

  def parseFromString (jsonStr: String) = Parse.parseOption(jsonStr).get
  def parseToString (json: JSON)        = json.nospaces
  def serialize(listing: Listing)       = listing.asJson
  def deserialize(json: JSON)           = json.nospaces.decodeOption[Listing]
}

object ArgonautLibrary {

  implicit def linkCodec: CodecJson[Link] = CodecJson(
  // Link Encoder
    (l: Link) =>
      ("data" := (
        ("title" := l.title) ->:
        ("url" := l.url.toString) ->:
        ("ups" := l.stats.ups) ->:
        ("downs" := l.stats.downs) ->:
        ("score" := l.stats.score) ->:
        ("num_comments" := l.stats.num_comments) ->:
          jEmptyObject)
        ) ->: jEmptyObject,
  // Link Decoder
    c => for {
      title        <- (c --\ "data" --\ "title").as[String]
      url          <- (c --\ "data" --\ "url").as[String]
      ups          <- (c --\ "data" --\ "ups").as[Int]
      downs        <- (c --\ "data" --\ "downs").as[Int]
      score        <- (c --\ "data" --\ "score").as[Int]
      num_comments <- (c --\ "data" --\ "num_comments").as[Int]
    } yield Link(title, new URL(url), Counters(ups, downs, score, num_comments))
  )

  implicit def listingCodec: CodecJson[Listing] = CodecJson(
  // Listing Encoder
    (l: Listing) =>
      ("data" :=
        ("children" := l.data.toList) ->:
        ("before" :=? l.before) ->?:
        ("after" :=? l.after) ->?:
        jEmptyObject) ->: jEmptyObject,
  // Listing Decoder
    c => for {
      data   <- (c --\ "data" --\ "children").as[List[Link]]
      before <- (c --\ "data" --\ "before").as[Option[String]]
      after  <- (c --\ "data" --\ "after").as[Option[String]]
    } yield Listing(UUID.randomUUID(), data, before, after)
  )
}