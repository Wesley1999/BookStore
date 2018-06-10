package servlet;

import dao.SelectUSER;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by 王少刚 on 2018/6/5.
 * 此Servlet用于检查找回密码信息是否正确
 * 首先判断账号是否存在，
 *   如果不存在，显示错误信息并引导用户返回找回密码页面；
 *   如果存在，判断输入的昵称是否正确，
 *     如果错误，显示错误信息并引导用户返回找回密码页面；
 *     如果正确，显示密码并引导用户返回登录页面。
 */

public class CheckFindPassword extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String USERNUMBER = request.getParameter("USERNUMBER");
        String USERNICKNAME = request.getParameter("USERNICKNAME");
        if(USERNUMBER == "")
            out.print("账号不能为空<br><a href='/login.jsp'>返回登录页面</a>");
        else if(USERNICKNAME == "")
            out.print("昵称不能为空<br><a href='/login.jsp'>返回登录页面</a>");
        else{
            try {
                if(SelectUSER.weatherUSERNUMBERExists(USERNUMBER)==0)
                    out.print("账号不存在<br><a href='/login.jsp'>返回登录页面</a>");
                else{
                    try {
                        String correctUSERNICKNAME = SelectUSER.selectUsernicknameByUsernumber(USERNUMBER);
                        if((USERNICKNAME).equals(correctUSERNICKNAME)) {
                            out.print("昵称正确");
                            String correctUSERPASSWORD = SelectUSER.selectPasswordByUsernumber(USERNUMBER);
                            out.print("您的密码是："+correctUSERPASSWORD);
                        }
                        else
                            out.print("昵称错误<br><a href='/findPassword.jsp'>返回找回密码页面");
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
