// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/vijayarajp/assignments/assignment-1-mug/conf/posts.routes
// @DATE:Sat Apr 06 09:58:12 IST 2019


package posts {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
