package com.slickdb

import slick.jdbc.PostgresProfile

final case class Keyword(id: Long, name: String)

class SlickTablesGeneric(val profile: PostgresProfile) {
  import profile.api._
  class KeywordTable(tag: Tag) extends Table[Keyword](tag, Some("keywords"), "Keyword") {
    def id = column[Long]("movie_id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    override def * = (id, name) <> (Keyword.tupled, Keyword.unapply)
  }
  lazy val keywordTable = TableQuery[KeywordTable]
}

object SlickTables extends SlickTablesGeneric(PostgresProfile)
