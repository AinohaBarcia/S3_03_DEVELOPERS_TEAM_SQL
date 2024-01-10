package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args)  throws Exception {
        Connection con = DriverManager.
                getConnection("jdbc:h2:./src/Db/FlowerShop", "Admin", "");
        // add application code here
        Methods.printStock(con);
        Methods.createNewFlowerShop(con);
        Methods.createTree(con);
        con.close();
    }
}