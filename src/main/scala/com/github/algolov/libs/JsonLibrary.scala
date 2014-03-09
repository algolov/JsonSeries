package com.github.algolov.libs

import com.github.algolov.Listing

trait JsonLibrary {
  type JSON
  def parseFromString (jsonStr: String): JSON
  def parseToString (json: JSON): String
  def serialize(listing: Listing): JSON
  def deserialize(json: JSON): Option[Listing]
}