package dao;

import java.sql.*;

/**
 * Created by 王少刚 on 2018/6/6.
 * 这个类中有两个静态方法，功能分别是：通过账号和商品编号查商品数目、通过账号查询购物车中所有的商品编号
 */
public class SelectCART {
    public static String selectNumberByUsernumberAndBooknumber(String USERNUMBER, String BOOKNUMBER) throws ClassNotFoundException, SQLException {
        //通过账号和商品编号查商品数目，使用这个方法要确保USERNUMBER和BOOKNUMBER均已存在，保存措施是先通过账号查询购物车中所有的商品编号
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");
        Statement stat = conn.createStatement();
        String sql = "USE BookStore SELECT NUMBER FROM CART WHERE USERNUMBER="+USERNUMBER+" AND BOOKNUMBER="+BOOKNUMBER;
        System.out.println("\nSQL语句为："+sql);
        ResultSet rs = stat.executeQuery(sql);
        if(rs.next()){
            String NUMBER = rs.getString("NUMBER");
            System.out.println("商品数目为："+NUMBER);
            stat.close();
            conn.close();
            return NUMBER;    //找到则返回NUMBER
        }
        else {                //这种情况本不应该出现，但必须写出来
            stat.close();
            conn.close();
            return "";        //未找到则为空
        }
    }

    public static String[] selectAllBooknumberByUsernumber(String USERNUMBER) throws ClassNotFoundException, SQLException {     //通过账号查询购物车中所有的商品编号
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");

        //下面这段代码用来查找数据库中商品的数目，进而确定该方法返回的字符串数组的元素个数s
        Statement stat0 = conn.createStatement();
        ResultSet rs0=stat0.executeQuery("SELECT COUNT(*) AS LINES FROM CART WHERE USERNUMBER="+USERNUMBER);
        if(rs0.next()){
            String LINES = rs0.getString("LINES");
            System.out.print(LINES);
        }
        String LINES = rs0.getString("LINES");
        System.out.println("行数是："+LINES);
        int s = Integer.parseInt(LINES);
        stat0.close();

        Statement stat = conn.createStatement();
        String sql = "USE BookStore SELECT BOOKNUMBER FROM CART WHERE USERNUMBER="+USERNUMBER;
        System.out.println("\nSQL语句为："+sql);
        ResultSet rs = stat.executeQuery(sql);
        String[] BOOKNUMBER = new String[s];    //s是通过前面一次查找得到的
        for(int i = 0;rs.next();i++){
            BOOKNUMBER[i] = rs.getString("BOOKNUMBER");
            BOOKNUMBER[i] = BOOKNUMBER[i].replace(" ", "");//这个表达式用来去空格，因为从数据库中找到的找到则返回BOOKNUMBER[i]后面带有空格
        }
        stat.close();
        conn.close();
        return BOOKNUMBER;
    }

}
