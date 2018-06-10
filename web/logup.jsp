<%--
  Created by IntelliJ IDEA.
  User: 王少刚
  Date: 2018/6/4
  Time: 10:33
  To change this template use File | Settings | File Templates.
  本页面用于用户注册，用户填写的注册信息提交到CheckLogup处理，处理过程在CheckLogup中介绍。
  本页面用到了servlet技术
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>

<form action = "/CheckLogup" method = "get">
    账号：<input name = "USERNUMBER" type = "text">（不超过16位，只能由数字组成）<br>
    昵称：<input name = "USERNICKNAME" type = "text">（不超过12位，昵称可用于找回密码）<br>
    密码：<input name = "USERPASSWORD" type = "password">（不超过16位）<br>
    <input type="submit" value = "注册">
</form>
</body>
</html>
