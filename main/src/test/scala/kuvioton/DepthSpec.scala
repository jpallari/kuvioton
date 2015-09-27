package kuvioton

import kuvioton.adt._
import org.scalatest.{Matchers, FlatSpec}
import kuvioton.conversion.DepthSyntax._

class DepthSpec extends FlatSpec with Matchers {

  val group = Group(
    name = "Secret Club",
    leader = Person("Jane", 33, Sandwich("rye", hasMayo = true)),
    members = List(
      Person("John", 29, Sausage(12)),
      Person("Alan", 40, Soup(400, spicy = false, List("salmon", "potato")))
    )
  )

  "Depth" should "calculate the depth of an average group correctly." in {
    group.depth shouldEqual 4
  }

  it should "calculate the depth of a large ADT correctly." in {
    (B88("foo", 3): StupidAdt).depth shouldEqual 2
  }
}
