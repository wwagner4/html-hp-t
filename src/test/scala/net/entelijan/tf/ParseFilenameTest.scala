package net.entelijan.tf.tiles

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers._
import org.scalatest._

import net.entelijan.tf.imgutil.*


class ParseFilenameTest extends AnyFunSuite {

  val valid_names = List(
    (0, "tf-0-067.jpg", 0, 67),
    (1, "tf-0-000.jpg", 0, 0),
    (2, "tf-1-000.jpg", 1, 0),
    (3, "tf-1-999.jpg", 1, 999),
    // Somhow strange but still valid
    (4, "t-1-999.jpeg", 1, 999),
    (5, "tf-0001-999.JPG", 1, 999),
    (6, "tf-1-00000999.png", 1, 999),
    (7, "hallo-1-999.jpg", 1, 999),
    (8, "tf-1000-999.jpg", 1000, 999),
  )  

  for (testNr, name, origin, nr) <- valid_names do 
    test(s"valid-$testNr") {
        val fn = parseTfFilename(name)
        assert(origin == fn.origin)
        assert(nr == fn.nr)
    }

  val invalid_names = List(
    (0, "hallo.jpg"),
    (1, "tf-x-067.jpg"),
    (2, "tf-x--1.jpg"),
    (3, "tf-0-0000x.jpg"),
    (4, "tf-0.jpg"),
    (5, "tf_0-009.jpg"),
    (6, "hallo"),
    (7, "tf-x-067"),
    (8, "tf-x--1"),
    (9, "tf-0-0000x"),
    (10, "tf-0"),
    (11, "tf_0-009"),
  )  

  for (testNr, name) <- invalid_names do 
    test(s"invalid-$testNr") {
        val ex = intercept[IllegalArgumentException] {
            parseTfFilename(name)
        }
        assert(ex.getMessage().contains(name), s"Exception message '$ex.getMessage' must contain the original message")
    }

}