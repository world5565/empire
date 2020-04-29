<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set scope="page" var="url"
    value="${pageContext.request.contextPath }"></c:set>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="jquery/jquery.min.js"></script>

<script>
$(document).ready(function(){
	$("#start").click(function(){
		
		$.ajax({url:"logins?who=start",dataType: "json",async:true,success:function(data){
			changelist(data);
		}});

	});
});

$(document).ready(function(){
	$("#lastpage").click(function(){
		
		$.ajax({url:"logins?who=lastpage",dataType: "json",async:true,success:function(data){
			changelist(data);
		}});

	});
});

$(document).ready(function(){
	$("#nextpage").click(function(){
		
		$.ajax({url:"logins?who=nextpage",dataType: "json",async:true,success:function(data){
			changelist(data);
		}});

	});
});

function changelist(data){
	
	var thisListValueStr = "";
	thisListValueStr = "<tr><th>" + "ID" + "</th><th>" + "名称" + "</th>"+ "<th>" + "时间" + "</th><th>" + "操作" + "</th></tr>";
    for (var i = 0; i < data.length; i++) {
        var caseList = data[i]; 
        thisListValueStr += "<tr><td>" + caseList.id + "</td><td>" + caseList.name + "</td>"+ "<td>" + caseList.time + "</td><td>" +
        "<a href='logins?who=chthpla&chthid="+caseList.id+"&chthname="+caseList.name+"&chthtime="+caseList.time+"'>修改</a>"
        		+"  "+
        "<a href='logins?who=deletethpla&dethplaid="+caseList.id+"'>删除</a>"
        + "</td></tr>";
        
    }
    $("#thinglist").html(thisListValueStr);
    showpage();
}


function showpage(){
	$.ajax({url:"logins?who=ajaxnowshowpage",dataType:"text",asyc:true,success:function(data){
		document.all["nowshowpage"].value=data;
	}
	});
	$.ajax({url:"logins?who=ajaxtotalpage",dataType:"text",asyc:true,success:function(data){
		document.all["totalpage"].value=data;
	}
	});
};
</script>

 
<title>Planeptune</title>
<link type="text/css" rel=stylesheet href="css/login_css.css">
<style type="text/css">
form{margin:0px; display:inline}
table.gridtable {
font-family: verdana,arial,sans-serif;
font-size:11px;
color:#333333;
border-width: 1px;
border-color: #666666;
border-collapse: collapse;
} 
table.gridtable th {
border-width: 1px;
padding: 8px;
border-style: solid;
border-color: #666666;
background-color: #dedede;
}
table.gridtable td {
border-width: 1px;
padding: 8px;
border-style: solid;
border-color: #666666;
background-color: #ffffff;
} 
a:link {
 font-size: 12px;
 color: #000000;
 text-decoration: none;
}
a:visited {
 font-size: 12px;
 color: #000000;
 text-decoration: none;
}
a:hover {
 font-size: 12px;
 color: #999999;
 text-decoration: underline;
}

</style>
</head>
<body>

<div style="position:absolute; width:100%; height:100%; z-index:-1"> 
	<img src="images/load_2.jpg">
</div>

<div>
<form method="post">
<input type=text style=“height=20px;line-hight=20px;text-align:center;” id="id" disabled="disabled" size=20 maxlength=20 value=<%=request.getAttribute("id")%>><br>
<pre>
</pre>
<input type=text style=“height=20px;line-hight=20px;text-align:center;” id="username" disabled="disabled" size=20 maxlength=20 value=<%=request.getAttribute("username")%>><br>
</form>
<pre>
</pre>
<form action="logins?who=platologin" method="post">
<input type="submit" value="退出">
</form>
</div>

<div style="left: 42%;top:20%;">
<form action="logins?who=findid" method="post">
<input type=text style=“height=20px;line-hight=20px;text-align:center;” name="findthingid" id="findthingid" size=20 maxlength=20 value="thing_id">
<input type="submit" value="搜索"/>
</form>
<%
   if(request.getAttribute("error_findthing")!=null)
   {
%>
    <font color="red">&nbsp;&nbsp;<%=request.getAttribute("error_findthing")%></font>
<%
   }
%>
</div>

<div style="left: 32%;top:28%;" id="thing">
<!-- <form action="logins?who=flip" method="post">
<input type="submit" value="查询所有的thing"/>
</form> -->
<form action="logins?who=addpla" method="post">
<input type="submit" value="新增">
</form>
<form>
<table id="thinglist" class="gridtable" id="t_thing" border="1" cellpadding="2" cellspacing="0" style="background-color: white;text-align:center;width:500px">
          <thead>
            <tr>
                <th>ID</th>
                <th>名称</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
        </thead>
        <c:forEach items="${list}" var="list">
        <tr>
            <td>${list.id}</td>
            <td>${list.name}</td>
            <td>${list.time}</td>
            <td><a  href= "logins?who=chthpla&chthid=${list.id}&chthname=${list.name}&chthtime=${list.time}"     style='text-decoration:none'>修改</a>
            <a  href= "logins?who=deletethpla&dethplaid=${list.id}"  style='text-decoration:none'     >删除</a>  </td> 
             
        </tr>
    </c:forEach>    
    </table>
    </form>
</div>

<div style="left: 32%;top:63%;">
	<form>
	<input type="button" id="start" value="首页"/>
	<input type="button" id="lastpage" value="上一页"/>
	<input type="button" id="nextpage" value="下一页"/>
	</form>
 <!--    <form action="logins?who=start" method="post">
    <input type="submit" value="首页"/>
    </form> 
    <form action="logins?who=lastpage" method="post">
    <input type="submit" value="上一页"/>
    </form>
    <form action="logins?who=nextpage" method="post">
    <input type="submit" value="下一页"/>
    </form> -->
    <form>
    <input type=text style=“height=20px;line-hight=20px;text-align:center;” disabled="disabled" name="nowshowpage" id="nowshowpage" size=20 maxlength=20 value=<%=request.getAttribute("nowshowpage")%>>
    <input type=text style=“height=20px;line-hight=20px;text-align:center;” disabled="disabled" name="totalpage" id="totalpage" size=20 maxlength=20 value=<%=request.getAttribute("totalpage")%>>
    </form>
    
</div>

</body>
</html>