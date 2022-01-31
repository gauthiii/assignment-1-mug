// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Users/yamin/Documents/Game/assignment-1-mug/conf/posts.routes
// @DATE:Tue Apr 13 23:18:27 IST 2021

package v1.post;

import posts.RoutesPrefix;

public class routes {
  
  public static final v1.post.ReversePostController PostController = new v1.post.ReversePostController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final v1.post.javascript.ReversePostController PostController = new v1.post.javascript.ReversePostController(RoutesPrefix.byNamePrefix());
  }

}
