package org.example;

import java.sql.*;


public class Methods {

    public static void createNewFlowerShop(Connection con) {
        String name = Input.getString("Name:");
        String sql = "INSERT INTO FLOWERSHOP.FLOWERSHOP(name) VALUES (?)";
        updateWithParameter(con, sql, name);
    }

    public static void createTree(Connection con, int idFlowerShop) {
        String name = Input.getString("Tree name:");
        int height = Input.getInt("Height:");
        float price = Input.getFloat("Price:");
        String sql = "INSERT INTO FLOWERSHOP.TREE (NAME, HEIGHT, PRICE, FLOWERSHOP_IDFLOWERSHOP) VALUES (?, ?, ?, ?)";
        updateWithParameters(con, sql, name, height, price, idFlowerShop);
    }

    public static void createFlower(Connection con, int idFlowerShop) {
        String flowerName = Input.getString("Flower name:");
        String color = Input.getString("Color:");
        float price = Input.getFloat("Price:");

        int idProduct = idProductGenerator(con);

        if (idProduct != -1) {
            String sqlFlower = "INSERT INTO FLOWERSHOP.FLOWERS (NAME, COLOR, PRICE, FLOWERSHOP_IDFLOWERSHOP, IDPRODUCT) VALUES (?, ?, ?, ?, ?)";
            updateWithParameters(con, sqlFlower, flowerName, color, price, idFlowerShop, idProduct);
        } else {
            System.out.println("Imposible to create a new id.");
        }
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

        int idProduct = idProductGenerator(con);

        if (idProduct != -1) {

            String sql = "INSERT INTO FLOWERSHOP.DECORATION(NAME, TYPE, PRICE, FLOWERSHOP_IDFLOWERSHOP, IDPRODUCT) VALUES (?, ?, ?, ?, ?)";
            updateWithParameters(con, sql, name, typeDecoration, price, idFlowerShop, idProduct);
        }
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
        //TODO this method.
    }
    public static void createTicketAndAddProducts(Connection con, int idFlowershop){
        int idTicket = createTicketMethod(con,idFlowershop);
        Menu.chooseMenuTicket(con,idTicket,idFlowershop);
        printEntries(con, "TICKET");
        System.out.println("Ticket with items:\n");
        printTicket(con,idTicket);
    }
    public static int createTicketMethod(Connection con, int idFlowershop) {
        String date = Input.getString("Ticket date:");
        String sql = "INSERT INTO FLOWERSHOP.TICKET(DATE, FLOWERSHOP_IDFLOWERSHOP) VALUES (?, ?)";

        try (PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, date);
            statement.setInt(2, idFlowershop);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("Ticket creado con IDTICKET: " + generatedId);
                        return generatedId;
                    } else {
                        System.err.println("Error al obtener el IDTICKET generado.");
                        return -1;
                    }
                }
            } else {
                System.err.println("Error to insert the ticket.");
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("imposible to create the ticket ");
            return -1;
        }
    }

    public static void addProductTicket(Connection con, int idTicket, int idFlowerShop) {
        printStock(con);
        int idProduct = Input.getInt("Choose the number of the id product");
        String type = TableType.getTableNameById(con, idProduct); // TODO: Controlar errores
        int quantity = Input.getInt("Quantity:");

        int generatedProductId = idProductGenerator(con);

        if (generatedProductId != -1) {
            float price = getProductPrice(con, generatedProductId, type);
            float amountPrice = price * quantity;

            String sql = "INSERT INTO FLOWERSHOP.TICKETITEMS(ID_PRODUCT, PRODUCT_TYPE, QUANTITY, UNIT_PRICE, TOTAL_PURCHASE, TICKET_IDTICKET, FLOWERSHOP_IDFLOWERSHOP) VALUES (?, ?, ?, ?, ?, ?, ?)";
            updateWithParameters(con, sql, generatedProductId, type, quantity, price, amountPrice, idTicket, idFlowerShop);

            String deleteSql = "DELETE FROM FLOWERSHOP." + type + " WHERE IDPRODUCT = ?";
            updateWithParameter(con, deleteSql, generatedProductId);
        } else {
            System.out.println("Error: Failed to generate a new ID for the product.");
        }
    }
 

    public static void showOldPurchases(Connection con) {
        //TODO this method.
    }

    public static void showTotalEarnings(Connection con) {
        //TODO this method.
    }

    public static int searchFlowerShop(Connection con){
        printEntries(con, "flowershop");
        return Input.getInt("Id Flower Shop:");
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
    public static float getProductPrice(Connection connection, int idProducto, String type) {
        float price = 0.0F;

        try {
            String sql = "SELECT PRICE FROM FLOWERSHOP."+type+" WHERE IDPRODUCT = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idProducto);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        price = resultSet.getFloat("PRICE");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }
    public static int idProductGenerator(Connection con) {
        try {
            String sql = "CALL NEXT VALUE FOR FLOWERSHOP.PRODUCTIDGENERATOR_SEQ";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int nextId = resultSet.getInt(1);
                        if (!isProductIdExisting(con, nextId)) {
                            String insertSql = "INSERT INTO FLOWERSHOP.PRODUCTIDGENERATOR (IDPRODUCT) VALUES (?)";
                            updateWithParameter(con, insertSql, nextId);
                            return nextId;
                        } else {
                            System.out.println("Error: ID " + nextId + " already exists in PRODUCTIDGENERATOR table.");
                            return -1;
                        }
                    } else {
                        System.out.println("Error: No se pudo obtener el próximo valor de la secuencia.");
                        return -1;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static boolean isProductIdExisting(Connection con, int productId) {
        String query = "SELECT 1 FROM FLOWERSHOP.PRODUCTIDGENERATOR WHERE IDPRODUCT = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("This product, already exist.");;
            return false;  // Manejar cualquier error de SQL
        }
    }

    public static void printTicket(Connection con, int idTicket) {
        String sql = "SELECT * FROM FLOWERSHOP.TICKET WHERE IDTICKET = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, idTicket);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Ticket Information:");
                    System.out.println("IDTICKET: " + resultSet.getInt("IDTICKET"));
                    System.out.println("Date: " + resultSet.getString("DATE"));
                    System.out.println("FLOWERSHOP_IDFLOWERSHOP: " + resultSet.getInt("FLOWERSHOP_IDFLOWERSHOP"));
                    System.out.println("\nProducts in the Ticket:");

                    String ticketItemsSql = "SELECT * FROM FLOWERSHOP.TICKETITEMS WHERE TICKET_IDTICKET = ?";
                    try (PreparedStatement ticketItemsStatement = con.prepareStatement(ticketItemsSql)) {
                        ticketItemsStatement.setInt(1, idTicket);
                        try (ResultSet ticketItemsResultSet = ticketItemsStatement.executeQuery()) {
                            while (ticketItemsResultSet.next()) {
                                System.out.println("\nProduct ID: " + ticketItemsResultSet.getInt("ID_PRODUCT"));
                                System.out.println("Product Type: " + ticketItemsResultSet.getString("PRODUCT_TYPE"));
                                System.out.println("Quantity: " + ticketItemsResultSet.getInt("QUANTITY"));
                                System.out.println("Unit Price: " + ticketItemsResultSet.getFloat("UNIT_PRICE"));
                                System.out.println("Total Purchase: " + ticketItemsResultSet.getFloat("TOTAL_PURCHASE"));
                            }
                        }
                    }
                } else {
                    System.out.println("Ticket with ID " + idTicket + " not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Another error, What do you think?");;
        }
    }

}
