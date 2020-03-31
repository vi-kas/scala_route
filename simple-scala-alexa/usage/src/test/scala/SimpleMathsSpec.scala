package com.simple.maths

import org.scalatest.FlatSpec

class SimpleMathsSpec extends FlatSpec {

  "SimpleMaths.isEven" should "give true" in {
    assert(SimpleMaths.isEven(12) == true)
  }

  it should "give false" in {
    assert(SimpleMaths.isEven(121) == false)
  }

}