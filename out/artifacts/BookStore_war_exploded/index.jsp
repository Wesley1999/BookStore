<%@ page import="database.SelectUSER" %>
<%@ page import="database.SelectBOOK" %><%--
  Created by IntelliJ IDEA.
  User: 王少刚
  Date: 2018/6/3
  Time: 17:23
  To change this template use File | Settings | File Templates.
  本页面是BookStore的首页，根据cookie判断用户是否已经登陆，如果没有登陆，会提示用户前往登陆页面，如果已登录，会显示欢迎信息，注销链接，以及跳转到购物车的链接。
  展开登录页面按钮用到了Ajax和JavaScript技术，点击后不会刷新页面，直接在下面显示登录页面，关于登录页面的介绍，在login.jsp中有详细阐述。
  下面是各种商品的信息，如果没有登录，在把商品添加到购物车时会提示用户前往登陆页面，如果已登录，可以把商品添加到购物车，把商品添加到购物车用到了数据库技术。
  商品的信息包括商品的编号、名称、价格和图片，这些信息是用从数据库中读取得到的，其中商品的图片在数据库中只存放地址，不存放文件。
  在本页面中，用到了Html技术、数据库技术、Ajax技术技术、JavaScript技术。
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>主页</title>
  </head>
  <body>

  <%--以下是Ajax代码--%>
  <SCRIPT LANGUAGE = "JavaScript">
    function showLogin(){
//    var xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");      //针对IE浏览器
      var xmlHttp = new XMLHttpRequest();                    //针对Chrome、Firefox等除IE浏览器外的大部分浏览器
      xmlHttp.open("get","login.jsp",true);
      xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState == 4){
          infoDiv.innerHTML = xmlHttp.responseText;
        }
      }
      xmlHttp.send();
    }
  </SCRIPT>
  <%--以上是Ajax代码--%>

  <%--读取cookie并存入USERNUMBER，如果不存在则为初始的空串--%>
    <%
      String USERNUMBER = "";
//      String BOOKNUMBER = "";   //顺便把BOOKNUMBER初始化为空串
      Cookie[] cookies = request.getCookies();
      for(int i = 0;i<cookies.length;i++) {
        if (cookies[i].getName().equals("USERNUMBER")) {
          USERNUMBER = cookies[i].getValue();
        }
      }
    %>

  <%--if中是未登录用户的界面--%>
    <%
      if(USERNUMBER.equals("")){
    %>
    欢迎来到BookStore<HR>
    您还没有登录
    <input type = "button" value = "点此展开登录页面" onClick = "showLogin()">
    <div id = "infoDiv"></div>

  <%--else中是已登录用户的界面--%>
    <%
      }
      else{
        String USERNICKNAME = SelectUSER.selectUsernicknameByUsernumber(USERNUMBER);
    %>
    <%=USERNICKNAME%>，欢迎来到BookStore&nbsp;&nbsp;
  <img src="/images/shoppingcart.jpg"><a href="cart.jsp?USERNUMBER=<%=USERNUMBER%>">查看购物车</a>&nbsp;&nbsp;
    <a href="logout.jsp">注销</a>
    <%
      }
    %>
  <HR>
  下面是商品<BR><HR>




  <%
    String[] booknumbers = SelectBOOK.selectAllBooknumber();
    for(int i = 0;i<booknumbers.length;i++){
      String BOOKNUMBER = booknumbers[i];
      %>
        <img src="<%=SelectBOOK.selectUrlByBooknumber(BOOKNUMBER)%>"><BR>
      <%
        out.print(SelectBOOK.selectNameByBooknumber(BOOKNUMBER));
      %>

  <%--废弃的代码
        <input type = "button" value = "添加到购物车" onClick = "showAddToCart()">
  废弃的代码--%>

        <a href="addToCart.jsp?USERNUMBER=<%=USERNUMBER%>&BOOKNUMBER=<%=BOOKNUMBER%>">添加到购物车</a><BR><HR>
      <%
    }
  %>
  </body>
</html>




<%--以下是Ajax代码

<SCRIPT LANGUAGE = "JavaScript">
  function showAddToCart(){
//    var xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");      //针对IE浏览器
    var xmlHttp = new XMLHttpRequest();                    //针对Chrome、Firefox等除IE浏览器外的大部分浏览器
    xmlHttp.open("get","addToCart.jsp?USERNUMBER=<%=USERNUMBER%>&BOOKNUMBER=<%=BOOKNUMBER%>",true);
    xmlHttp.onreadystatechange = function(){
      if(xmlHttp.readyState == 4){
        infoDiv.innerHTML = xmlHttp.responseText;
      }
    }
    xmlHttp.send();
  }
</SCRIPT>

--%>
