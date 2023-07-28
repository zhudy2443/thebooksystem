<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/7/21
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()
        +"://"+request.getServerName()
        +":"+request.getServerPort()
        +request.getContextPath()
        +"/";
    pageContext.setAttribute("basePath",basePath);
%>
<!-- Base标签 拥有固定相对路径的跳转的结果-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>

