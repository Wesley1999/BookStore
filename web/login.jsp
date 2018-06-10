<%--
  Created by IntelliJ IDEA.
  User: 王少刚
  Date: 2018/6/3
  Time: 20:39
  To change this template use File | Settings | File Templates.
  本页面用于登陆，表单提交到servlet.CheckLogin处理。
  处理过程为：如果信息正确,会把用户编号写入cookie，然后引导用户返回首页；如果信息错误，显示错误信息并提示用户返回登陆页面（本页面）。
  本页面可以直接进入，也可以在index.jsp中用Ajax技术显示出来。
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action = "/CheckLogin" method = "get">
        账号：<input name = "USERNUMBER" type = "text"><br>
        密码：<input name = "USERPASSWORD" type = "password"><br>
        <input type="submit" value = "登录">
        <a href='/logup.jsp'>注册</a>&nbsp;&nbsp;<a href='/findPassword.jsp'>找回密码</a>
    </form>

</body>
</html>
