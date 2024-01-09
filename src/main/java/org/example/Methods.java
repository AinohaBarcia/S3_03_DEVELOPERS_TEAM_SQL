package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class Methods {

    public static void createNewFlowerShop (Connection con){
        String sql = ("INSERT INTO FLOWERSHOP.FLOWERSHOP(name)VALUES('"+(Input.getString("Name:"))+"');");
        query(con,sql);
    }
    public static void createTree(Connection con){
        String sql = ("INSERT INTO FLOWERSHOP.TREE(HEIGHT,PRICE,FLOWERSHOP_IDFLOWERSHOP)VALUES("+(Input.getInt("Height:"))+","+(Input.getFloat("Price:"))+",1);");
        query(con,sql);
    }
    public static void createFlower(Connection con){
    }

    public static void createDecoration(Connection con){
    }
    public static void printStock(Connection con){
    }

    public static void deleteProduct(Connection con){

    }

    public static void printProductCount(Connection con){
    }
    public static void stockTotalValue(Connection con){
    }
    public static void createTicketMethod (Connection con){
    }

    public static void addProductTicket(Connection con){
    }
    public static void showOldPurchases (Connection con){
    }

    public static void showTotalEarnings(Connection con) {
    }

    public static void searchProduct (Connection con) {
    }

    public static ResultSet query(Connection con, String sql){
        ResultSet rs = null;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception ex){
            System.err.println("Error "+ex);
        }
        return rs;
    }
}

