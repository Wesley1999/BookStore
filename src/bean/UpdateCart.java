package bean;

import dao.DeleteFromTable;
import dao.InsertIntoTable;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * Created by 王少刚 on 2018/6/6.
 * 这是一个javabean，在jsp页面中将这个类实例化以后，可以调用这里面的方法完成购物车中商品的增删
 * 这里面有两个方法，功能分别是增加购物车中的商品和减少购物车中的上品。
 */
public class UpdateCart {
    private String USERNUMBER;
    private String BOOKNUMBER;
    public void setUSERNUMBER(String USERNUMBER){
        this.USERNUMBER = USERNUMBER;
    }
    public void setBOOKNUMBER(String BOOKNUMBER){
        this.BOOKNUMBER = BOOKNUMBER;
    }
    public void addToCart() throws Exception{
        try{
            System.out.println("添加到购物车方法被调用!");
            System.out.println("USERNUMBER是："+USERNUMBER);
            System.out.println("BOOKNUMBER是："+BOOKNUMBER);
            InsertIntoTable.insertIntoCART(USERNUMBER,BOOKNUMBER);
        }catch (Exception e){
            System.out.println("addToCart方法遇到了错误");
        }

    }
    public void subtractFromCart() throws Exception{
        System.out.println("从购物车中减少方法被调用!");
        System.out.println("USERNUMBER是："+USERNUMBER);
        System.out.println("BOOKNUMBER是："+BOOKNUMBER);
        DeleteFromTable.subtractFromCart(USERNUMBER,BOOKNUMBER);

    }
}
