package org.example;

import java.sql.*;


public class Methods {

    public static void createNewFlowerShop(Connection con) {
        String name = Input.getString("Name:");
        String sql = "INSERT INTO FLOWERSHOP.FLOWERSHOP(name) VALUES (?)";
        updateWithParameter(con, sql, name);
    }

    public static void createTree(Connection con, int idFlowerShop) {
        String name = Input.getString("Name:");
        int height = Input.getInt("Height:");
        float price = Input.getFloat("Price:");
        String sql = "INSERT INTO FLOWERSHOP.TREE(NAME, HEIGHT, PRICE, FLOWERSHOP_IDFLOWERSHOP) VALUES (?, ?, ?, ?)";
        updateWithParameters(con, sql, name, height, price, idFlowerShop);
    }

    public static void createFlower(Connection con, int idFlowerShop) {
        String flowerName = Input.getString("Flower name:");
        String color = Input.getString("Color:");
        float price = Input.getFloat("Price:");
        String sql = "INSERT INTO FLOWERSHOP.FLOWERS(NAME, COLOR, PRICE, FLOWERSHOP_IDFLOWERSHOP) VALUES (?, ?, ?, ?)";
        updateWithParameters(con, sql, flowerName, color, price, idFlowerShop);
    }

    public static void createDecoration(Connection con, int idFlowerShop) {
        boolean result = false;
        String typeDecoration = "";
        do {
            typeDecoration = Input.getString("Wood or plastic:").toUpperCase();
            result = typeDecoration.contains("PLASTIC") || typeDecoration.contains("WOOD");
        } while (!result);

        String name = Input.getString("Name:");
        float price = Input.getFloat("Price:");
        String sql = "INSERT INTO FLOWERSHOP.DECORATION(NAME, TYPE, PRICE, FLOWERSHOP_IDFLOWERSHOP) VALUES (?, ?, ?, ?)";
        updateWithParameters(con, sql, name, typeDecoration, price, idFlowerShop);
    }

    public static void printStock(Connection con) {
        printEntries(con, "Flowers");
        printEntries(con, "Tree");
        printEntries(con, "Decoration");
    }

    public static void deleteFlower(Connection con) {
        printEntries(con, "Flowers");
        int idProduct = Input.getInt("Id Flower:");
        String sql = "DELETE FROM FlowerShop.Flowers WHERE idFlowers = ?";
        updateWithParameter(con, sql, idProduct);
    }

    public static void deleteDecoration(Connection con) {
        printEntries(con, "Decoration");
        int idProduct = Input.getInt("Id Decoration:");
        String sql = "DELETE FROM FlowerShop.Decoration WHERE idDecoration = ?";
        updateWithParameter(con, sql, idProduct);
    }

    public static void deleteTree(Connection con) {
        printEntries(con, "Tree");
        int idProduct = Input.getInt("Id Tree:");
        String sql = "DELETE FROM FlowerShop.Tree WHERE idTree = ?";
        updateWithParameter(con, sql, idProduct);
    }

    public static void printProductCount(Connection con) {
        int flowersCount = countEntries(con, "Flowers");
        int treeCount = countEntries(con, "Tree");
        int decorationCount = countEntries(con, "Decoration");

        System.out.println("Number of entries in Flowers: " + flowersCount);
        System.out.println("Number of entries in Tree: " + treeCount);
        System.out.println("Number of entries in Decoration: " + decorationCount);
    }

    public static void stockTotalValue(Connection con) {

    }

    public static void createTicketMethod(Connection con) {
        String date = Input.getString("Ticket date:");
        String sql = "INSERT INTO FLOWERSHOP.TICKET(DATE,FLOWER) VALUES (?)";
        updateWithParameter(con,sql,date);

    }


    public static void addProductTicket(Connection con) {
        ResultSet rs = null;
        rs = Menu.chooseMenuProduct(con);

    }

    public static void showOldPurchases(Connection con) {

    }

    public static void showTotalEarnings(Connection con) {

    }

    public static int searchFlowerShop(Connection con){
        printEntries(con, "flowershop");
        return Input.getInt("Id Flower Shop:");
    }

    public static ResultSet  searchProductTree(Connection con) {
        printEntries(con, "tree");
        int id = Input.getInt("Id product:");
        String sql = "SELECT * FROM FLOWERSHOP.TREE WHERE IDTREE =" +id;
        return query(con, sql);
    }

    public static ResultSet  searchProductFlower(Connection con) {
        printEntries(con, "flower");
        int id = Input.getInt("Id product:");
        String sql = "SELECT * FROM FLOWERSHOP.FLOWERS WHERE IDFLOWERS =" +id;
        return query(con, sql);
    }

    public static ResultSet  searchProductDecoration(Connection con) {
        printEntries(con, "decoration");
        int id = Input.getInt("Id product:");
        String sql = "SELECT * FROM FLOWERSHOP.DECORATION WHERE IDDECORATION =" +id;
        return query(con, sql);
    }

    public static ResultSet query(Connection con, String sql) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
        }
        return rs;
    }

    private static void updateWithParameter(Connection con, String sql, Object parameter) {
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            if (parameter instanceof Integer) {
                ps.setInt(1, (int) parameter);
            } else if (parameter instanceof String) {
                ps.setString(1, (String) parameter);
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
        }
    }

    private static void updateWithParameters(Connection con, String sql, Object... parameters) {
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i + 1, parameters[i]);
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
        }
    }

    public static void printEntries(Connection con, String tableName) {
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

    private static int countEntries(Connection con, String tableName) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM FlowerShop." + tableName;
            try (PreparedStatement statement = con.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
        }
        return count;
    }

}
