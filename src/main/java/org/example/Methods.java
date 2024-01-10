package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.sql.*;
import java.util.List;
import java.util.stream.IntStream;

public class Methods {

    public static void createNewFlowerShop(Connection con) {
        String sql = ("INSERT INTO FLOWERSHOP.FLOWERSHOP(name)VALUES('" + (Input.getString("Name:")) + "');");
        query(con, sql);
    }

    public static void createTree(Connection con) {
        String sql = ("INSERT INTO FLOWERSHOP.TREE(HEIGHT,PRICE,FLOWERSHOP_IDFLOWERSHOP)VALUES(" + (Input.getInt("Height:")) + "," + (Input.getFloat("Price:")) + ",1);");
        query(con, sql);
    }

    public static void createFlower(Connection con) {
    }

    public static void createDecoration(Connection con) {
    }

    public static void printStock(Connection con) {
        printEntries(con, "Flowers");

        // Llamada al método para imprimir las entradas de Tree
        printEntries(con, "Tree");

        // Llamada al método para imprimir las entradas de Decoration
        printEntries(con, "Decoration");
    }

    public static void deleteProduct(Connection con) {

    }

    public static void printProductCount(Connection con) {
    }

    public static void stockTotalValue(Connection con) {
    }

    public static void createTicketMethod(Connection con) {
    }

    public static void addProductTicket(Connection con) {
    }

    public static void showOldPurchases(Connection con) {
    }

    public static void showTotalEarnings(Connection con) {
    }

    public static void searchProduct(Connection con) {
    }

    public static ResultSet query(Connection con, String sql) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            System.err.println("Error " + ex);
        }
        return rs;
    }

    private static void printEntries(Connection con, String tableName) {
        try {
            String sql = "SELECT * FROM FlowerShop." + tableName;

            try (PreparedStatement statement = con.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    int columnCount = resultSetMetaData.getColumnCount();

                    System.out.println("Entries in " + tableName + ":");

                    for (int i = 1; i <= columnCount; i++) {
                    }
                    System.out.println();

                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(resultSetMetaData.getColumnName(i) + ": " + resultSet.getString(i) + "\t");
                        }
                        System.out.println();
                    }
                   System.out.println();
                }
            }

        } catch (Exception ex) {
            System.err.println("Error " + ex);
        }
    }

}
