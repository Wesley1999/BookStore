package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static dao.SelectCART.selectNumberByUsernumberAndBooknumber;

/**
 * Created by 王少刚 on 2018/6/8.
 * 这个类里面有两个静态方法，功能分别是从购物车中减少商品和删除商品
 */
public class DeleteFromTable {
    public static void subtractFromCart(String USERNUMBER, String BOOKNUMBER) throws ClassNotFoundException, SQLException {     //从购物车中减少商品，如果商品数目位1，调用这个方法会把商品中购物车表中删除
        String NUMBER = selectNumberByUsernumberAndBooknumber(USERNUMBER, BOOKNUMBER);
        int intNUMBER = Integer.parseInt(NUMBER);
        if (intNUMBER > 1) {
            String newNUMBER = (intNUMBER - 1) + "";
            System.out.println("数目将改为：" + newNUMBER);
            dao.UpdateTable.updateNumberInCart(USERNUMBER, BOOKNUMBER, newNUMBER);
        }
        else if (intNUMBER == 1) {
            deleteFromCART(USERNUMBER,BOOKNUMBER);
        }
    }

    public static void deleteFromCART(String USERNUMBER,String BOOKNUMBER) throws ClassNotFoundException, SQLException {        //调用这个方法会把商品中购物车表中删除
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=wsg;password=123456");
        Statement stat = conn.createStatement();
        String sql = "USE BookStore DELETE FROM CART WHERE USERNUMBER='"+USERNUMBER+"' AND BOOKNUMBER="+BOOKNUMBER;
        System.out.println("\nSQL语句为："+sql);
        int i = stat.executeUpdate(sql);
        System.out.println("成功删除"+i+"行");
        stat.close();
        conn.close();
    }

}
