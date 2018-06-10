package servlet;

/**
 * Created by 王少刚 on 2018/6/4.
 * 此Servlet用于检查注册信息是否正确
 * 首先判断输入的账号、昵称、密码格式是否正确，
 *   如果错误，显示错误信息并引导用户返回注册页面；
 *   如果正确，判断用户名是否存在，
 *     如果存在，显示错误信息并引导用户返回注册页面；
 *     如果不存在，将注册信息录入到数据库，
 *     然后提示注册成功，引导用户返回首页
 */

import dao.InsertIntoTable;
import dao.SelectUSER;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class CheckLogup extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String USERNUMBER = request.getParameter("USERNUMBER");
        String USERNICKNAME = request.getParameter("USERNICKNAME");
        String USERPASSWORD = request.getParameter("USERPASSWORD");
        if(USERNUMBER == "")
            out.print("账号不能为空<br><a href='/logup.jsp'>返回注册页面</a>");
        else if(USERNUMBER.length()>16)
            out.print("账号不能超过16位<br><a href='/logup.jsp'>返回注册页面</a>");
        else if(USERNICKNAME == "")
            out.print("昵称不能为空<br><a href='/logup.jsp'>返回注册页面</a>");
        else if(USERNICKNAME.length()>12)
            out.print("昵称不能超过12位<br><a href='/logup.jsp'>返回注册页面</a>");
        else if(USERPASSWORD == "")
                out.print("密码不能为空<br><a href='/logup.jsp'>返回注册页面</a>");
        else if(USERPASSWORD.length()>16)
            out.print("密码不能超过16位<br><a href='/logup.jsp'>返回注册页面</a>");
        else{
            try {
                if(SelectUSER.weatherUSERNUMBERExists(USERNUMBER)==1){
                    out.print("账号已存在，请输入其他账号<br><a href='/logup.jsp'>返回注册页面</a>");
                }
                else{
                    try {
                        boolean isNumber =USERNUMBER.matches("[0-9]+");
                        if(isNumber == false){
                            out.print("账号不是由纯数字组成的，请输入其他账号<br><a href='/logup.jsp'>返回注册页面</a>");
                        }
                        else {
                            System.out.print("尝试插入到数据库 ");
                            InsertIntoTable.insertIntoUSERTABLE(USERNUMBER, USERNICKNAME, USERPASSWORD);
                            System.out.print("插入到数据库成功");
                            out.print("注册成功<br>");
                            Cookie cookie = new Cookie("USERNUMBER", USERNUMBER);    //将USERNUMBER存入Cookie，用于注册完成后自动登陆
                            cookie.setMaxAge(3600);                                 //设置Cookie存活期位3600秒
                            response.addCookie(cookie);                             //将Cookie保存到客户端
                            System.out.print("Cookie已保存到客户端");
                            out.print("已自动为您登录");
                            out.print("<a href='/'>返回首页</a>");
                        }
                    } catch (Exception e) {
                        System.out.print("插入到数据库失败");
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}