package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 王少刚 on 2018/6/4.
 * 这个类中有两个静态方法，第一个方法的功能是功能是把账号、昵称、密码插入到用户表
 * 第二个方法的功能是把账号、商品编号插入到购物车表
 */
public class InsertIntoTable {
    public static void insertIntoUSERTABLE(String USERNUMBER, String USERNICKNAME, String USERPASSWORD) throws Exception {      //把账号、昵称、密码插入到用户表
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");
        Statement stat = conn.createStatement();
        String sql = "USE BookStore INSERT INTO USERTABLE(USERNUMBER,USERNICKNAME,USERPASSWORD) VALUES('"+USERNUMBER+"','"+USERNICKNAME+"','"+USERPASSWORD+"')";
        System.out.println("\nSQL语句为："+sql);
        int i = stat.executeUpdate(sql);
        System.out.println("成功添加"+i+"行");
        stat.close();
        conn.close();
    }

    public static void insertIntoCART(String USERNUMBER, String BOOKNUMBER) throws ClassNotFoundException, SQLException {       //把账号、商品编号插入到购物车表
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");
        Statement stat = conn.createStatement();
        String sql = "USE BookStore INSERT INTO CART(USERNUMBER,BOOKNUMBER,NUMBER) VALUES('"+USERNUMBER+"','"+BOOKNUMBER+"','1')";
        System.out.println("\nSQL语句为："+sql);
        try {
            int i = stat.executeUpdate(sql);
            System.out.println("成功添加" + i + "行");
        }catch (SQLException e){
            System.out.println("添加到购物车中的商品已存在");
            String NUMBER = (Integer.parseInt(SelectCART.selectNumberByUsernumberAndBooknumber(USERNUMBER,BOOKNUMBER))+1)+"";   //查找某用户购物车中某商品的数目，然后转为int类型，然后+1，然后转为String类型
            System.out.println("数目将改为："+NUMBER);
            dao.UpdateTable.updateNumberInCart(USERNUMBER,BOOKNUMBER,NUMBER);
        }
        stat.close();
        conn.close();
    }

}