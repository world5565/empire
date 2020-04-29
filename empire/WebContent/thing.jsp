<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>thing</title>
<link type="text/css" rel=stylesheet href="css/login_css.css">
<style type="text/css">
form{margin:0px; display:inline}
</style>
</head>
<body>
<div style="position:absolute; width:100%; height:100%; z-index:-1"> 
	<img src="images/load_3.jpg">
</div>

<div style="position: absolute;left: 44%;top:38%;">
<form action="" method="post">
<input type=text style=“height=20px;line-hight=20px;text-align:center;” disabled="disabled" name="thingid" id="thingid" size=20 maxlength=20 value=<%=request.getAttribute("findid")%>><br>
<pre>
</pre>
<input type=text style=“height=20px;line-hight=20px;text-align:center;” name="thingname" id="thingname" size=20 maxlength=20 onkeyup="copythname()" value=<%=request.getAttribute("findname")%>><br>
<pre>
</pre>
<input type=text style=“height=20px;line-hight=20px;text-align:center;” disabled="disabled" name="thingtime" id="thingtime" size=20 maxlength=20 value=<%=request.getAttribute("findtime")%>><br>
</form>
<pre>
</pre>
<%
   if(request.getAttribute("error_thdechid")!=null)
   {
%>
    <font color="red">&nbsp;&nbsp;<%=request.getAttribute("error_thdechid")%></font>
<%
   }
%>
</div>

<div style="position: absolute;left:43%;top:60%;">
<form action="logins?who=addthing" method="post" id="addform">
<input type="hidden" name="addthname" id="addthname" value="">
<input type="submit" id="add" value="新增">
</form>
<form action="logins?who=chthth" method="post">
<input type="hidden" name="chthid" id="chthid" value=<%=request.getAttribute("findid")%>>
<input type="hidden" name="chthname" id="chthname" value=<%=request.getAttribute("findname")%>>
<input type="submit" value="修改"/>
</form>
<form action="logins?who=deletethth" method="post">
<input type="hidden" name="deththid" id="deththid" value=<%=request.getAttribute("findid")%>>
<input type="submit" value="删除">
</form>
<form action="logins?who=thingtopla" method="post">
<input type="submit" value="退出"/>
</form>
</div>

</body>
<script type="text/javascript">
function copythname(){
	document.all["addthname"].value=document.all["thingname"].value;
	document.all["chthname"].value=document.all["thingname"].value;
}

</script>
</html>