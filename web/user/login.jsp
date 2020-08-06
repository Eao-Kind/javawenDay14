<%--
  Created by IntelliJ IDEA.
  User: 17724
  Date: 2020/8/6
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>登陆 </title>
</head>
<body>
<h1>登录</h1>
<p style="color: red; font-weight: 900">${msg}</p>
<%--${pageContext.request.contextPath }/RegistServlet --%>
<form action="<c:url value='/LoginServlet'/>" method="post">
    用户名：<input type="text" name="username" value="${user.username}"/><br/>
    密　码：<input type="password" name="password" value="${user.password }"/>
    <input type="submit" value="登陆"/>
</form>
</body>
</html>
