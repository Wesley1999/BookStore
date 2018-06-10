<%@ page import="com.sun.org.apache.bcel.internal.generic.Select" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="dao.SelectCART" %>
<%@ page import="dao.SelectBOOK" %><%--
  Created by IntelliJ IDEA.
  User: 王少刚
  Date: 2018/6/3
  Time: 21:53
  To change this template use File | Settings | File Templates.
  这是用户的购物车页面，从cookie获取USERNUMBER，再从数据库获取用户的购物车信息
  可以在本页面完成商品的增删
  本页面用到了JavaBean技术、jsp内置对象
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
购物车：<HR>
<%
    String USERNUMBER = request.getParameter("USERNUMBER");
    try{
        String BOOKNUMBERbeforechange = request.getParameter("BOOKNUMBERbeforechange");
        String UPDATE = request.getParameter("UPDATE");
        if(UPDATE.equals("ADD")){
%>
            <jsp:useBean id="add" class="bean.UpdateCart" ></jsp:useBean>
            <jsp:setProperty property = "USERNUMBER" name="add" value="<%=USERNUMBER%>"></jsp:setProperty>
            <jsp:setProperty property = "BOOKNUMBER" name="add" value="<%=BOOKNUMBERbeforechange%>"></jsp:setProperty>
<%
            add.addToCart();
        }
        else if (UPDATE.equals("SUBTRACT")){
%>
            <jsp:useBean id="subtract" class="bean.UpdateCart" ></jsp:useBean>
            <jsp:setProperty property = "USERNUMBER" name="subtract" value="<%=USERNUMBER%>"></jsp:setProperty>
            <jsp:setProperty property = "BOOKNUMBER" name="subtract" value="<%=BOOKNUMBERbeforechange%>"></jsp:setProperty>
<%
            subtract.subtractFromCart();
        }
    }catch(Exception e){}
    String[] booknumbers = SelectCART.selectAllBooknumberByUsernumber(USERNUMBER);
    BigDecimal[] prices = new BigDecimal[booknumbers.length];
    BigDecimal totalprice = new BigDecimal(0);
    for(int i = 0;i<booknumbers.length;i++) {
        String BOOKNUMBER = booknumbers[i];
        String s = SelectCART.selectNumberByUsernumberAndBooknumber(USERNUMBER,BOOKNUMBER);
        prices[i] = (new BigDecimal(s).multiply(new BigDecimal(dao.SelectBOOK.selectPriceByBooknumber(BOOKNUMBER))));    //为了计算不损失精度，这里用了math包的BigDecimal类
        out.print(SelectBOOK.selectNameByBooknumber(BOOKNUMBER));%><BR><%
        out.print(s+"本");
        out.print(prices[i]+"元");
        totalprice = totalprice.add(prices[i]);
%>
    <a href="cart.jsp?USERNUMBER=<%=USERNUMBER%>&BOOKNUMBERbeforechange=<%=BOOKNUMBER%>&UPDATE=ADD">添加</a>
    <a href="cart.jsp?USERNUMBER=<%=USERNUMBER%>&BOOKNUMBERbeforechange=<%=BOOKNUMBER%>&UPDATE=SUBTRACT">减少</a><BR><HR>
<%
    }
    out.print("总价："+totalprice);
%>
    <a href="/order.jsp">提交订单</a>
    <a href="/">返回首页</a>
</body>
</html>
