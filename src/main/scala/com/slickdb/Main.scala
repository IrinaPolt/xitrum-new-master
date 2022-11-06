package com.slickdb
import scala.concurrent.{ExecutionContext, Future}
import java.util.concurrent.{ExecutorService, Executors}
import scala.util.{Failure, Success}


object PrivateExecutionContext {
  val executor: ExecutorService = Executors.newFixedThreadPool(4)
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(executor)
}


object Main {

  import PrivateExecutionContext._
  import slick.jdbc.PostgresProfile.api._
    
  val Novokuznetsk: Keyword = Keyword(1L, "Новокузнецк")

  def demoInsertKeyword(): Unit = {
    val queryDescription = SlickTables.keywordTable += Novokuznetsk

    val futureId: Future[Int] = Connection.db.run(queryDescription)

    futureId.onComplete {
      case Success(newKeywordId) => 
             println(s"Query was successful, new keyword id is $newKeywordId")
      case Failure(ex) => 
             println(s"Query failed, reason: $ex")
    }

    Thread.sleep(10000)
  }

  def main(args: Array[String]): Unit = {
    demoInsertKeyword()
  }
}
