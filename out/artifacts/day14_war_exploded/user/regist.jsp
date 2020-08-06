<%--
  Created by IntelliJ IDEA.
  User: 17724
  Date: 2020/8/5
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>注册</title>
    <script type="text/javascript">
        function _change() {
            var ele = document.getElementById("vCode");
            ele.src = "<c:url value='/VerifyCodeServlet'/>?xxx=" + new Date().getTime();
        }
    </script>
</head>
<body>
<h1>注册</h1>
<p style="font-weight: 900; color: red;">${msg}</p>
<%--${pageContext.request.contextPath }/RegistServlet --%>
<form action="<c:url value='/RegistServlet'/>" method="post">
    用户名：<input type="text" name="username" value="${user.username}"/>${errors.username}<br/>
    密　码：<input type="password" name="password" value="${user.password }"/>${errors.password }<br/>
    验证码：<input type="text" name="verifyCode" value="${user.verifyCode}" size="3"/>
            <img id="vCode" src="<c:url value='/VerifyCodeServlet'/> "border="2"/>
    <a href="javascript:_change()">换一张</a>${errors.verifyCode }<br/>
    <input type="submit" value="注册"/>
</form>
</body>
</html>
