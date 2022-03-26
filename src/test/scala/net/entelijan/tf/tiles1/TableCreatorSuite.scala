package net.entelijan.tf.tiles1
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers._



class TableCreatorSuite extends AnyFunSuite {

  test("One Image one row one col") {
    val tab: Seq[Row] = TableCreator.createTable("dummy", List("a.png"), 1, 1)
    tab.size mustBe 1
    tab.head.cells.size mustBe 1
    val cell = tab.head.cells.head
    cell.id mustBe 0
    cell.imgIndex mustBe 0
    cell.imagePath mustBe "a.png"
    cell.imgId() mustBe "img_0_0"
  }

  test("One Image multiple rows one col") {
    val tab: Seq[Row] = TableCreator.createTable("dummy", List("a.png"), 3, 1)
    tab.size mustBe 3

    {
      tab.head.cells.size mustBe 1
      val cell = tab.head.cells.head
      cell.id mustBe 0
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_0_0"
    }

    {
      tab(1).cells.size mustBe 1
      val cell = tab(1).cells.head
      cell.id mustBe 1
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_1_0"
    }

    {
      tab(2).cells.size mustBe 1
      val cell = tab(2).cells.head
      cell.id mustBe 2
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_2_0"
    }

  }

  test("One Image multiple rows multiple cols") {
    val tab: Seq[Row] = TableCreator.createTable("Dummy", List("a.png"), 2, 3)
    tab.size mustBe 2

    {
      tab.head.cells.size mustBe 3
      val cell = tab.head.cells.head
      cell.id mustBe 0
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_0_0"
    }

    {
      tab.head.cells.size mustBe 3
      val cell = tab.head.cells(1)
      cell.id mustBe 1
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_1_0"
    }

    {
      tab.head.cells.size mustBe 3
      val cell = tab.head.cells(2)
      cell.id mustBe 2
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_2_0"
    }

    {
      tab(1).cells.size mustBe 3
      val cell = tab(1).cells.head
      cell.id mustBe 3
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_3_0"
    }

    {
      tab(1).cells.size mustBe 3
      val cell = tab(1).cells(1)
      cell.id mustBe 4
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_4_0"
    }

    {
      tab(1).cells.size mustBe 3
      val cell = tab(1).cells(2)
      cell.id mustBe 5
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_5_0"
    }

  }


  test("Multiple Images multiple rows multiple cols") {
    val tab: Seq[Row] = TableCreator.createTable("Dummy", List("a.png", "b.png"), 2, 3)
    tab.size mustBe 2

    {
      tab.head.cells.size mustBe 3
      val cell = tab.head.cells.head
      cell.id mustBe 0
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_0_0"
    }

    {
      tab.head.cells.size mustBe 3
      val cell = tab.head.cells(1)
      cell.id mustBe 1
      cell.imgIndex mustBe 1
      cell.imagePath mustBe "b.png"
      cell.imgId() mustBe "img_1_1"
    }

    {
      tab.head.cells.size mustBe 3
      val cell = tab.head.cells(2)
      cell.id mustBe 2
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_2_0"
    }

    {
      tab(1).cells.size mustBe 3
      val cell = tab(1).cells.head
      cell.id mustBe 3
      cell.imgIndex mustBe 1
      cell.imagePath mustBe "b.png"
      cell.imgId() mustBe "img_3_1"
    }

    {
      tab(1).cells.size mustBe 3
      val cell = tab(1).cells(1)
      cell.id mustBe 4
      cell.imgIndex mustBe 0
      cell.imagePath mustBe "a.png"
      cell.imgId() mustBe "img_4_0"
    }

    {
      tab(1).cells.size mustBe 3
      val cell = tab(1).cells(2)
      cell.id mustBe 5
      cell.imgIndex mustBe 1
      cell.imagePath mustBe "b.png"
      cell.imgId() mustBe "img_5_1"
    }
  }
}
