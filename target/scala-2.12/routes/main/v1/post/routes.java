// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/vijayarajp/assignments/assignment-1-mug/conf/posts.routes
// @DATE:Sat Apr 06 09:58:12 IST 2019

package v1.post;

import posts.RoutesPrefix;

public class routes {
  
  public static final v1.post.ReversePostController PostController = new v1.post.ReversePostController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final v1.post.javascript.ReversePostController PostController = new v1.post.javascript.ReversePostController(RoutesPrefix.byNamePrefix());
  }

}
