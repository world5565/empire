<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登陆界面</title>
<link type="text/css" rel=stylesheet href="css/login_css.css">
</head>
<body>
<div style="position:absolute; width:100%; height:100%; z-index:-1"> 
	<img src="images/load_1.jpg">
</div>

<div style="position: absolute;left: 44%;top:44%;">
<form action="logins?who=finduser" method="post">
<input type=text name="username" id="username" size=20 maxlength=20 placeholder="用户名(4~16字节)"><br>
<pre>
</pre>
<input type=password name="password" id="password" size=20 maxlength=20 placeholder="密码(6~12字节)"><br>
<br>
<input onclick="" style="height:32px;width:170px;" type="submit" name=login value="登陆">
</form>
<%
   if(request.getAttribute("error_login")!=null)
   {
%>
    <font color="red">&nbsp;&nbsp;<%=request.getAttribute("error_login")%></font>
<%
   }
%>
</div>

</body>
</html>