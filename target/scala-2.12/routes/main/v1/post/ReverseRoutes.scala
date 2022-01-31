// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Users/yamin/Documents/Game/assignment-1-mug/conf/posts.routes
// @DATE:Tue Apr 13 23:18:27 IST 2021

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:1
package v1.post {

  // @LINE:1
  class ReversePostController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def update(id:String): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:1
    def list(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:4
    def show(id:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:2
    def create(): Call = {
      
      Call("POST", _prefix)
    }
  
  }


}
