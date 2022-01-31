
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object open extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<html>
<title>SNAKE GAME HOME</title>
<style>
.text"""),format.raw/*4.6*/("""{"""),format.raw/*4.7*/("""

"""),format.raw/*6.1*/("""color: white;
font-size:120%;

"""),format.raw/*9.1*/("""}"""),format.raw/*9.2*/("""

"""),format.raw/*11.1*/(""".button """),format.raw/*11.9*/("""{"""),format.raw/*11.10*/("""

  """),format.raw/*13.3*/("""padding: 15px 32px;
  text-align: center;
  font-size: 16px;
  width :100%;
  cursor: pointer;	
"""),format.raw/*18.1*/("""}"""),format.raw/*18.2*/("""

"""),format.raw/*20.1*/("""</style>
<body bgcolor="black">

<h1 style="color:white;" align="center">WELCOME TO THE SNAKE GAME</h1>

<p class="text" align="center" >A really hungry Snake needs apples for survival
<br>Use the arrow keys to move the snake
<br>The snake grows for every apple it eats and gains 10 points
<br>If the snake bites(passes through) another snake it gains 15 points and the other snake loses 5 points
<br>The snake can lose length only till it has 4 cells (minimum limit) in case it is bitten by any other snakes
<br>If the snake bites itself then GAME OVER
</p>



<p class="text" align="center" >Click the button to Start Game.</p>


<button class="button" onclick="window.location.href = 'http://snakegame.com:9000/';">PLAY GAME</button>


</body>
</html>"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Apr 13 23:18:28 IST 2021
                  SOURCE: D:/Users/yamin/Documents/Game/assignment-1-mug/app/views/open.scala.html
                  HASH: 984d9a9825462ab6d751e4e0da888d79db849398
                  MATRIX: 1029->0|1109->54|1136->55|1166->59|1226->93|1253->94|1284->98|1319->106|1348->107|1381->113|1509->214|1537->215|1568->219
                  LINES: 33->1|36->4|36->4|38->6|41->9|41->9|43->11|43->11|43->11|45->13|50->18|50->18|52->20
                  -- GENERATED --
              */
          