<h2>Get widgets</h2>
<p><small>GET</small> http://localhost:8080</p>
<p>src\main\java\com.miro.widget.controllers <strong>WidgetController\getAllWidgets()</strong></p>

<h4 style="color:gray;">Size and # of page (optional)</h4>
<p><p><small>GET</small> http://localhost:8080?page=0&size=20</p></p>

<h2>Get widget by id</h2>
<p><small>GET</small> http://localhost:8080/widget?id=1</p>
<p>src\main\java\com.miro.widget.controllers <strong>WidgetController\GetWidgetById()</strong></p>

<h2>Set new widget</h2>
<p><small>POST</small> http://localhost:8080?xValue=1&yValue=1&weight=10.0&height=20.5&zIndex=1</p>
<p>or</p>
<p><small>POST</small> http://localhost:8080?xValue=1&yValue=1&weight=10&height=20</p>
<p>src\main\java\com.miro.widget.controllers <strong>WidgetController\setNewWidget()</strong></p>

<h2>Edit widget</h2>
<p><small>POST</small> http://localhost:8080/edit?id=1&xValue=2&yValue=3&weight=10.0&height=20.5&zIndex=3</p>
<p>or</p>
<p><small>POST</small> http://localhost:8080/edit?id=1&xValue=5</p>
<p>or</p>
<p><small>POST</small> http://localhost:8080/edit?id=1&weight=50.0</p>
<p>src\main\java\com.miro.widget.controllers <strong>WidgetController\editWidget()</strong></p>

<h2>Delete widget</h2>
<p><small>DELETE</small> http://localhost:8080?id=1</p>
<p>src\main\java\com.miro.widget.controllers <strong>WidgetController\DeleteWidget()</strong></p>