package net.entelijan.tf

object ModelReader {

  case class Prod(
    id: String,
    models: List[Model])

  case class Mod(
    id: String,
    name: String,
    equipment: Option[String],
    prize: Option[String],
    size: Option[String]) extends Model {}

  def readModels(rows: List[List[String]]): Map[String, List[Model]] = {

    def isNextProducer(row: List[String]): Boolean = !row(0).isEmpty

    def createNewProducer(row: List[String]): Prod = Prod(row(0), List.empty[Model])

    def isEmptyRow(row: List[String]): Boolean = {
      val x = row.map { _.isEmpty }
      // TODO Use some all or every
      x.foldRight(true)((a, b) => a && b)
    }

    /**
     * Producer is defined. Read Model-Data from row
     */
    def readModel(rowCount: Int, row: List[String], restRows: List[List[String]], result: Map[String, List[Model]], producer: Prod): Map[String, List[Model]] = {
      if (isEmptyRow(row) || !row(3).isEmpty()) readModel1(rowCount + 1, restRows, result, Some(producer))
      else if (row.size < 8) throw new IllegalStateException(s"Not enough columns in row $rowCount")
      else if (row(2).isEmpty()) throw new IllegalStateException(s"No ID defined in row $rowCount")
      else if (row(4).isEmpty()) throw new IllegalStateException(s"No NAME defined in row $rowCount")
      else {
        val m = Mod(
          id = row(2),
          name = row(4),
          equipment = if (row(5).isEmpty()) None else Some(row(5)),
          prize = if (row(7).isEmpty()) None else Some(row(7)),
          size = if (row(6).isEmpty()) None else Some(row(6)))
        val m1 = m :: producer.models
        val p1 = producer.copy(models = m1)
        readModel1(rowCount + 1, restRows, result, Some(p1))
      }
    }

    def readProducer(rowCount: Int, row: List[String], restRows: List[List[String]], result: Map[String, List[Model]], producer: Option[Prod]): Map[String, List[Model]] = {
      if (isNextProducer(row)) {
        val newProdKey = createNewProducer(row)
        if (producer.isDefined) {
          val p = producer.get
          val r1 = result + (p.id -> p.models.reverse)
          readModel(rowCount, row, restRows, r1, newProdKey)
        } else {
          readModel(rowCount, row, restRows, result, newProdKey)
        }
      } else if (producer.isDefined) {
        readModel(rowCount, row, restRows, result, producer.get)
      } else {
        throw new IllegalStateException(s"No producer defined in line $rowCount")
      }
    }

    def readModel1(rowCount: Int, rows: List[List[String]], result: Map[String, List[Model]], producer: Option[Prod]): Map[String, List[Model]] = {
      rows match {
        case Nil => {
          if (producer.isDefined) {
            val p = producer.get
            result + (p.id -> p.models.reverse)
          } else {
            result
          }
        }
        case row :: rest => readProducer(rowCount, row, rest, result, producer)
      }
    }

    rows match {
      case Nil => Map.empty[String, List[Model]]
      case _ :: data => readModel1(1, data, Map.empty[String, List[Model]], None)
    }

  }

}

