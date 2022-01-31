// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Users/yamin/Documents/Game/assignment-1-mug/conf/posts.routes
// @DATE:Tue Apr 13 23:18:27 IST 2021


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
