<%--
  Created by IntelliJ IDEA.
  User: 王少刚
  Date: 2018/6/4
  Time: 12:02
  To change this template use File | Settings | File Templates.
  本页面用于注销，会把cookie中的USERNUMBER置为空串，然后清除，提示用户注销成功，可以返回首页。
  本页面用到了jsp内置对象
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注销</title>
</head>
<body>
    <%
        Cookie cookie = new Cookie("USERNUMBER","");    //将USERNUMBER存入Cookie
        cookie.setMaxAge(0);                            //设置Cookie存活期位0秒
        response.addCookie(cookie);                     //将Cookie保存到客户端
        System.out.print("Cookie已清除");
        out.print("注销成功");
    %>
    <a href="/">返回首页</a>
</body>
</html>
