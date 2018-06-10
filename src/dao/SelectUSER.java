package dao;

import java.sql.*;

/**
 * Created by 王少刚 on 2018/6/4.
 * 这个类中有三个静态方法，功能分别是：根据账号从用户表中查找正确的密码、根据账号从用户表中查找正确的昵称、判断账号在用户表中是否已经存在。
 */

public class SelectUSER {       //根据账号从用户表中查找正确的密码
    public static String selectPasswordByUsernumber(String USERNUMBER) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");
        Statement stat = conn.createStatement();
        String sql = "USE BookStore SELECT USERPASSWORD FROM USERTABLE WHERE USERNUMBER='"+USERNUMBER+"'";
        System.out.println("\nSQL语句为："+sql);
        ResultSet rs = stat.executeQuery(sql);
        if(rs.next()){
            String correctUSERPASSWORD = rs.getString("USERPASSWORD");
            System.out.print(correctUSERPASSWORD);
            correctUSERPASSWORD = correctUSERPASSWORD.replace(" ","");//这个表达式用来去空格，因为从数据库中找到的密码后面带有空格
            stat.close();
            conn.close();
            return correctUSERPASSWORD;
        }
        else {
            stat.close();
            conn.close();
            return "";
        }
    }

    public static String selectUsernicknameByUsernumber(String USERNUMBER) throws ClassNotFoundException, SQLException {       //根据账号从用户表中查找正确的昵称
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");
        Statement stat = conn.createStatement();
        String sql = "USE BookStore SELECT USERNICKNAME FROM USERTABLE WHERE USERNUMBER='"+USERNUMBER+"'";
        System.out.println("\nSQL语句为："+sql);
        ResultSet rs = stat.executeQuery(sql);
        if(rs.next()){
            String correctUSERNICKNAME = rs.getString("USERNICKNAME");
            System.out.print(correctUSERNICKNAME);
            correctUSERNICKNAME = correctUSERNICKNAME.replace(" ","");//这个表达式用来去空格，因为从数据库中找到的昵称后面带有空格
            stat.close();
            conn.close();
            return correctUSERNICKNAME;
        }
        else {
            stat.close();
            conn.close();
            return "";
        }
    }

    public static int weatherUSERNUMBERExists(String USERNUMBER) throws SQLException, ClassNotFoundException {      //判断账号在用户表中是否已经存在
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");
        Statement stat = conn.createStatement();
        String sql = "USE BookStore SELECT * FROM USERTABLE WHERE USERNUMBER='" + USERNUMBER + "'";
        System.out.println("\nSQL语句为：" + sql);
        ResultSet rs = stat.executeQuery(sql);
        if (rs.next())
            return 1;
        else
            return 0;
    }

}
