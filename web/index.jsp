<%@ page import="dao.*" %>
<%--
  Created by IntelliJ IDEA.
  User: 王少刚
  Date: 2018/6/3
  Time: 17:23
  To change this template use File | Settings | File Templates.
  本页面是BookStore的首页，根据cookie判断用户是否已经登陆，如果没有登陆，会提示用户前往登陆页面，如果已登录，会显示欢迎信息，注销链接，以及跳转到购物车的链接。
  展开登录页面和展开注册页面按钮用到了Ajax和JavaScript技术，点击后不会刷新页面，直接在下面显示登录页面，关于登录页面和注册页面的介绍，在login.jsp和logup.jsp中有详细阐述。
  下面是各种商品的信息，如果没有登录，在把商品添加到购物车时会提示用户前往登陆页面，如果已登录，可以把商品添加到购物车，把商品添加到购物车用到了数据库技术。
  商品的信息包括商品的编号、名称、价格和图片，这些信息是用从数据库中读取得到的，其中商品的图片在数据库中只存放地址，不存放文件。
  在本页面中，用到了Html技术、数据库技术、Ajax、JavaScript、jsp内置对象。
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>BookStore</title>
  </head>
  <body>
    <%--读取cookie并存入USERNUMBER，如果不存在则为空串--%>
<%
    String USERNUMBER = "";
    try{                            //这里之所以用try-catch语句，是因为第一次进入首页的用户cookie为空，下面的语句不能运行，原因不明。
        Cookie[] cookies = request.getCookies();
        for(int i = 0;i<cookies.length;i++) {
            if (cookies[i].getName().equals("USERNUMBER")) {
                USERNUMBER = cookies[i].getValue();
            }
        }
    }catch(Exception e){}           //没有cookie就不用cookie给USERNUMBER赋值
    if(USERNUMBER.equals("")){      //这里是未登录用户的界面
%>
    欢迎来到BookStore<HR>
    您还没有登录
    <input type = "button" value = "点此展开登录页面" onClick = "showLogin()">
    <input type = "button" value = "点此展开注册页面" onClick = "showLogup()">
    <div id = "infoDiv"></div>
<%
      }
      else{                         //这里是已经登录用户的界面
        String USERNICKNAME = SelectUSER.selectUsernicknameByUsernumber(USERNUMBER);
%>
        <%=USERNICKNAME%>，欢迎来到BookStore&nbsp;&nbsp;
        <img src="/images/shoppingcart.jpg"><a href="cart.jsp?USERNUMBER=<%=USERNUMBER%>">查看购物车</a>&nbsp;&nbsp;
        <a href="logout.jsp">注销</a>
<%
      }
%>

<%--下面是公共界面--%>
    <HR>您可以将想要的商品添加到购物车中<BR><HR>
<%
    String[] booknumbers = SelectBOOK.selectAllBooknumber();
    for(int i = 0;i<booknumbers.length;i++){
        String BOOKNUMBER = booknumbers[i];
%>
        <img src="<%=SelectBOOK.selectUrlByBooknumber(BOOKNUMBER)%>"><BR>
<%
        out.print(SelectBOOK.selectNameByBooknumber(BOOKNUMBER));
%>
        <a href="addToCart.jsp?USERNUMBER=<%=USERNUMBER%>&BOOKNUMBER=<%=BOOKNUMBER%>">添加到购物车</a><BR><HR>
<%
    }
%>
    BY.王少刚
  </body>
  <SCRIPT LANGUAGE = "JavaScript">
      function showLogin(){
          var xmlHttp = new XMLHttpRequest();                     //少数浏览器不支持这种实例化方式
          xmlHttp.open("get","login.jsp",true);
          xmlHttp.onreadystatechange = function(){
              if(xmlHttp.readyState == 4){
                  infoDiv.innerHTML = xmlHttp.responseText;
              }
          }
          xmlHttp.send();
      }
      function showLogup(){
          var xmlHttp = new XMLHttpRequest();                     //少数浏览器不支持这种实例化方式
          xmlHttp.open("get","logup.jsp",true);
          xmlHttp.onreadystatechange = function(){
              if(xmlHttp.readyState == 4){
                  infoDiv.innerHTML = xmlHttp.responseText;
              }
          }
          xmlHttp.send();
      }
  </SCRIPT>
</html>