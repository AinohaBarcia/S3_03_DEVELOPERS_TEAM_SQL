package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args)  throws Exception {
        Connection con = DriverManager.
                getConnection("jdbc:h2:./src/Db/FlowerShop", "Admin", "");

        Menu.choseMenu(con);
        // add application code here
        //Methods.createDecoration(con,Methods.searchFlowerShop(con));
        //Methods.createNewFlowerShop(con);
        //Methods.createTree(con);
        con.close();
    }
}