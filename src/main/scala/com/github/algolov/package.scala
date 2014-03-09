package com.github

import scala.util.Try
import scala.io.Source

package object algolov {
  /** loading json for test from either reddit.com or local file in resources
    *
    * @param count the number of items already seen in this listing.
    * @param before the full name of the listing that follows before this page, None if there is no previous page
    * @param after the full name of the listing that follows after this page, None if there is no next page
    */
  def loadListing(
    count: Int = 0,
    before: Option[String] = None,
    after: Option[String] = None): String = {

    val scalaSubredditUrl = "http://www.reeddit.com/r/scala/.json"

    val resp = Try {
      (before, after) match {
        case (Some(b), _) =>
          Source.fromURL(scalaSubredditUrl + s"?count=$count&before=$b")
        case (_, Some(a)) =>
          Source.fromURL(scalaSubredditUrl + s"?count=$count&after=$a")
        case _ => Source.fromURL(scalaSubredditUrl)
      }
    } getOrElse Source.fromURL(getClass.getResource("/reddit.json"))

    resp.getLines().mkString
  }
}
