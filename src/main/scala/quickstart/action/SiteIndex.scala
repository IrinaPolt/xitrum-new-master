package quickstart.action

import xitrum.annotation.GET

case class Keyword(keyword_id: Int, name: String)

@GET("")
class SiteIndex extends DefaultLayout {
  def execute(): Unit = {
    respondView()
  }
}
