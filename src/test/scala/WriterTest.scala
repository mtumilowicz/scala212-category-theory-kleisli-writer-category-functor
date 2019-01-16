import org.scalatest.{FunSuite, Matchers}

/**
  * Created by mtumilowicz on 2019-01-16.
  */
class WriterTest  extends FunSuite with Matchers  {

  test("testMap") {
    val writer = new Writer[Int](0, "")
    val mapped = writer.map(_ + 1)
    
    mapped.result should be (1)
    mapped.log should be ("")
  }

}
