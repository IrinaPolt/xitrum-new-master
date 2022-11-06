package quickstart.action

import scala.concurrent.Future
import slick.jdbc.GetResult

import java.time.LocalDate

object QueryOperations {
  val db = Connection.db

  import SlickTables.profile.api._

  def findKeywordByName(name: String): Future[Option[Keyword]] = {
    db.run(SlickTables.keywordTable.filter(_.name === name).result.headOption)
  }

  def findAllKeywords: Future[Seq[Keyword]] = {
    db.run(SlickTables.keywordTable.result)
  }

  def getAllKeywordsByPlainQuery: Future[Seq[Keyword]] = {
    implicit val getResultKeyword =
      GetResult(r => Keyword(r.<<, r.<<))
    val keywordsQuery = sql"""SELECT * FROM keywords."Keyword" """.as[Keyword]
    db.run(keywordsQuery)
  }

  // todo: check the return id
  def insertKeyword(keyword: Keyword): Future[Keyword] = {
    val insertQuery = SlickTables.keywordTable += keyword
    val insertQueryWithReturn = SlickTables.keywordTable.returning(SlickTables.keywordTable) += keyword
    db.run(insertQueryWithReturn)
  }

  def forceInsertKeywords(keywords: Seq[Keyword]): Future[Option[Int]] = {
    db.run(SlickTables.keywordTable.forceInsertAll(keywords))
  }

  def updateKeyword(keywordId: Long, keyword: Keyword): Future[Int] = {
    val updateQuery = SlickTables.keywordTable.filter(_.id === keywordId).update(keyword)
    val updateKeywordNameQuery = SlickTables.keywordTable.filter(_.id === keywordId).map(_.name).update("updatedName")
    db.run(updateQuery)
  }

  def deleteKeyword(keywordId: Long): Future[Int] = {
    db.run(SlickTables.keywordTable.filter(_.id === keywordId).delete)
  }

}