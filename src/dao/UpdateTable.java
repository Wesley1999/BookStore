package dao;

import java.sql.*;

/**
 * Created by 王少刚 on 2018/6/6.
 * 这个类中有一个静态方法，功能是更新购物车中商品的数目
 */
public class UpdateTable {
    public static void updateNumberInCart(String USERNUMBER, String BOOKNUMBER, String NUMBER) throws ClassNotFoundException, SQLException {        //更新购物车中商品的数目


            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");
            Statement stat = conn.createStatement();
            String sql = "USE BookStore UPDATE CART SET NUMBER=" + NUMBER + " WHERE USERNUMBER='" + USERNUMBER + "' AND BOOKNUMBER=" + BOOKNUMBER;
            System.out.println("\nSQL语句为：" + sql);
            int i = stat.executeUpdate(sql);
            System.out.println("成功修改" + i + "行");

    }

}
