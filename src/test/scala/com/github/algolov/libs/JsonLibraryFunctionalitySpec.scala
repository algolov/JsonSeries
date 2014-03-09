package com.github.algolov.libs

trait JsonLibraryFunctionalitySpec extends UnitJsonSpec {
  /** library name */
  def name: String

  name should "parse a String representing a json, and return it as a json value and vice versa" in {
    val strJson = """{"key1":1,"key2":[1,2,3,4,5]}"""

    parseToString(parseFromString(strJson)) shouldBe strJson
  }

  it should "serialize and deserialize a json to scala classes and vice versa" in {

    import com.github.algolov.Listing

    val json = parseFromString(jsonString)
    val listing = deserialize(json).value

    listing shouldBe a [Listing]

    val listing2 = deserialize(serialize(listing)).value

    listing2 should have (
      'before (listing.before),
      'after (listing.after) )

    listing2.data.length should be (listing.data.length)
  }

}
