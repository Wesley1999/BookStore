package dao;

import java.sql.*;

/**
 * Created by 王少刚 on 2018/6/6.
 * 这个类中有四个静态方法，功能分别是通过商品编号查名称、通过商品编号查价格、通过商品编号查图片链接、查找BOOK中所有的商品编号
 */
public class SelectBOOK {
    public static String selectNameByBooknumber(String BOOKNUMBER) throws ClassNotFoundException, SQLException {        //通过商品编号查名称
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");
        Statement stat = conn.createStatement();
        String sql = "USE BookStore SELECT BOOKNAME FROM BOOK WHERE BOOKNUMBER='"+BOOKNUMBER+"'";
        System.out.println("\nSQL语句为："+sql);
        ResultSet rs = stat.executeQuery(sql);
        if(rs.next()){
            String BOOKNAME = rs.getString("BOOKNAME");
            BOOKNAME = BOOKNAME.replace(" ","");//这个表达式用来去空格，因为从数据库中找到的找到则返回BOOKNAME后面带有空格
            stat.close();
            conn.close();
            return BOOKNAME; //找到则返回BOOKNAME
        }
        else {
            stat.close();
            conn.close();
            return "";      //未找到则为空
        }
    }

    public static String selectPriceByBooknumber(String BOOKNUMBER) throws ClassNotFoundException, SQLException {       //通过商品编号查价格
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");
        Statement stat = conn.createStatement();
        String sql = "USE BookStore SELECT BOOKPRICE FROM BOOK WHERE BOOKNUMBER='"+BOOKNUMBER+"'";
        System.out.println("\nSQL语句为："+sql);
        ResultSet rs = stat.executeQuery(sql);
        if(rs.next()){
            String BOOKPRICE = rs.getString("BOOKPRICE");
            BOOKPRICE = BOOKPRICE.replace(" ","");//这个表达式用来去空格，因为从数据库中找到的BOOKPRICE后面带有空格
            stat.close();
            conn.close();
            return BOOKPRICE; //找到则返回BOOKURL
        }
        else {
            stat.close();
            conn.close();
            return "";      //未找到则为空
        }
    }

    public static String selectUrlByBooknumber(String BOOKNUMBER) throws ClassNotFoundException, SQLException {     //通过商品编号查图片链接
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");
        Statement stat = conn.createStatement();
        String sql = "USE BookStore SELECT BOOKURL FROM BOOK WHERE BOOKNUMBER='"+BOOKNUMBER+"'";
        System.out.println("\nSQL语句为："+sql);
        ResultSet rs = stat.executeQuery(sql);
        if(rs.next()){
            String BOOKURL = rs.getString("BOOKURL");
//            BOOKURL = BOOKURL.replace(" ","");//这个表达式用来去空格，因为从数据库中找到的找到则返回BOOKURL后面带有空格
            stat.close();
            conn.close();
            return BOOKURL; //找到则返回BOOKURL
        }
        else {
            stat.close();
            conn.close();
            return "";      //未找到则为空
        }
    }

    public static String[] selectAllBooknumber() throws ClassNotFoundException, SQLException {                  //查找BOOK中所有的商品编号
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");

        //这段代码用来查找数据库中商品的数目，进而确定该方法返回的字符串数组的元素个数s
        Statement stat0 = conn.createStatement();
        ResultSet rs0=stat0.executeQuery("SELECT COUNT(*) AS LINES FROM BOOK");
        if(rs0.next()){
            String LINES = rs0.getString("LINES");
            System.out.print(LINES);
        }
        String LINES = rs0.getString("LINES");
        System.out.println("行数是："+LINES);
        int s = Integer.parseInt(LINES);
        stat0.close();

        Statement stat = conn.createStatement();
        String sql = "USE BookStore SELECT BOOKNUMBER FROM BOOK";
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
