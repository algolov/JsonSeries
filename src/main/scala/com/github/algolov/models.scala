package com.github.algolov

import java.net.URL
import java.util.UUID

/** Used to paginate content
  *
  * @param id Universal Unique IDentifier of listing
  * @param before the full name of the listing that follows before this page, None if there is no previous page
  * @param after the full name of the listing that follows after this page, None if there is no next page
  * @param data the list of [[Link]]s
  */
case class Listing (
  id: UUID = UUID.randomUUID(),
  data: Seq[Link],
  before: Option[String],
  after: Option[String])

/** representation of record with link
  *
  * @param title record title
  * @param url link in record
  * @param stats link's [[Counters]] info
  */
case class Link(
  title: String,
  url: URL,
  stats: Counters)

/** link's counters info
  *
  * @param ups the number of upvotes
  * @param downs the number of downvote
  * @param score [[Counters.ups]] - [[Counters.downs]]
  * @param num_comments the number of comments that belong to this link. Includes removed comments.
  */
case class Counters (
  ups: Int,
  downs: Int,
  score: Int,
  num_comments: Int)