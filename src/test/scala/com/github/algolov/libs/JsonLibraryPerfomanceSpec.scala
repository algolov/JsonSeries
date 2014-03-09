package com.github.algolov.libs

trait JsonLibraryPerfomanceSpec extends UnitJsonSpec {
  /** library name */
  def name: String

  /** number of test's function iterations */
  val numIterations = 2000

  /** helper for repeating test's functions */
  def repeat[A](n: Int = 1)(f: => A) {
    if(n > 0) { f; repeat(n-1)(f) }
  }


  val json = parseFromString(jsonString)
  val listing = deserialize(json).value

  name should s"parse a json $numIterations times (iteration )" in {
    repeat(numIterations) {
      parseFromString(jsonString)
    }
  }

  name should s"deserialize from a json $numIterations times" in {
    repeat(numIterations) {
      deserialize(json)
    }
  }

  name should s"serialize to a json $numIterations times" in {
    repeat(numIterations) {
      serialize(listing)
    }
  }
}
