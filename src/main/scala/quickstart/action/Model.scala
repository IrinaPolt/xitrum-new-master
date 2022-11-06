package quickstart.action

import slick.lifted.Tag
import slick.jdbc.PostgresProfile
import slick.lifted.ProvenShape

final case class Keyword(id: Long, name: String)

class SlickTablesGeneric(val profile: PostgresProfile) {
  import profile.api._

  class KeywordTable(tag: Tag) extends Table[Keyword](tag, Some("keywords"), "Keyword") {
    def id = column[Long]("keyword_id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    override def * = (id, name) <> (Keyword.tupled, Keyword.unapply)
  }
  lazy val keywordTable = TableQuery[KeywordTable]
}

object SlickTables extends SlickTablesGeneric(PostgresProfile)

//docker run --name postgresql -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres
//docker run --name postgresql -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgis/postgis
