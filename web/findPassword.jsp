<%--
  Created by IntelliJ IDEA.
  User: 王少刚
  Date: 2018/6/5
  Time: 9:44
  To change this template use File | Settings | File Templates.
  本页面用于找回密码，将用户填写的账号和昵称提交到servset.CheckFindPassword处理。
  处理过程为：如果信息正确，显示密码；如果信息错误，显示错误信息并提示用户返回找回密码页面。
  本页面用到了servlet技术
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
</head>
<body>
    <form action = "/CheckFindPassword" method = "get">
        账号：<input name = "USERNUMBER" type = "text"><br>
        昵称：<input name = "USERNICKNAME" type = "text"><br>
        <input type="submit" value = "找回密码"> <a href="/">返回首页</a>
    </form>
</body>
</html>
