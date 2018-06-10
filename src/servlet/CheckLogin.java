package servlet;

/**
 * Created by 王少刚 on 2018/6/3.
 * 此Servlet用于检查登录信息是否正确
 * 首先判断账号是否存在，
 *   如果不存在，显示错误信息并引导用户返回登录页面；
 *   如果存在，判断输入的密码是否正确，
 *     如果错误，显示错误提示并引导用户返回注册页面；
 *     如果正确，将用户名写入cookie，并引导用户返回首页。
 */

import dao.SelectUSER;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class CheckLogin extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String USERNUMBER = request.getParameter("USERNUMBER");
        String USERPASSWORD = request.getParameter("USERPASSWORD");
        if(USERNUMBER == "")
            out.println("账号不能为空<br><a href='/login.jsp'>返回登录页面</a>");
        else if(USERPASSWORD == "")
            out.print("密码不能为空<br><a href='/login.jsp'>返回登录页面</a>");
        else{
            try {
                if(SelectUSER.weatherUSERNUMBERExists(USERNUMBER)==0)
                    out.print("账号不存在<br><a href='/login.jsp'>返回登录页面</a>");
                else{
                    try {
                        String correctUSERPASSWORD = SelectUSER.selectPasswordByUsernumber(USERNUMBER);
                        if((USERPASSWORD).equals(correctUSERPASSWORD)) {    //登录成功后写cookie
                            System.out.print("密码正确<br>");
                            Cookie cookie = new Cookie("USERNUMBER",USERNUMBER);  //将USERNUMBER存入Cookie
                            cookie.setMaxAge(3600);                         //设置Cookie存活期位3600秒
                            response.addCookie(cookie);                     //将Cookie保存到客户端
                            System.out.print("Cookie已保存到客户端");
                            out.print("登陆成功<BR>");
                            out.print("<a href='/'>回到首页</a>");
                        }
                        else
                            out.print("密码错误<br><a href='/login.jsp'>返回登录页面</a>");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
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
