<%@ page import="dao.SelectCART" %><%--
  Created by IntelliJ IDEA.
  User: 王少刚
  Date: 2018/6/6
  Time: 20:43
  To change this template use File | Settings | File Templates.
  这是从首页中把商品添加到购物车后跳转的页面，对于已登录的用户，会调用JavaBean完成商品相购物车的添加
  对于未登陆的用户，会要求其进入登陆页面
  本页面用到了JavaBean技术、jsp内置对象
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加到购物车</title>
</head>
<body>
<%
    String USERNUMBER = request.getParameter("USERNUMBER");
    String BOOKNUMBER = request.getParameter("BOOKNUMBER");
    if(USERNUMBER.equals("")){
        out.print("您还没有登录，请先<a href='/login.jsp'>登录</a><BR>");
    }
    else{
%>
            <jsp:useBean id="aaaaaaaaa" class="bean.UpdateCart" ></jsp:useBean>
            <jsp:setProperty property = "USERNUMBER" name="aaaaaaaaa" value="<%=USERNUMBER%>"></jsp:setProperty>
            <jsp:setProperty property = "BOOKNUMBER" name="aaaaaaaaa" value="<%=BOOKNUMBER%>"></jsp:setProperty>
<%
            aaaaaaaaa.addToCart();
%>
            添加商品成功！<BR>
            该商品当前有<%=SelectCART.selectNumberByUsernumberAndBooknumber(USERNUMBER,BOOKNUMBER)%>件<BR>
            <a href="/">返回首页</a>&nbsp;&nbsp;<a href="cart.jsp?USERNUMBER=<%=USERNUMBER%>">查看购物车</a>
<%
    }
%>
</body>
</html>
