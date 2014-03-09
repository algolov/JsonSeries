package com.github.algolov.libs

import org.scalatest.{OptionValues, Matchers, FlatSpec}

trait UnitJsonSpec extends FlatSpec with Matchers with OptionValues with JsonLibrary {
  import com.github.algolov._

  val jsonString = loadListing()
}
