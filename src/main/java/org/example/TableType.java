package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableType {

    public static String getTableNameById (Connection connection, int idProducto) {
        String nombreTabla = null;

        try {

            String sqlFlowers = "SELECT 1 FROM FLOWERSHOP.FLOWERS WHERE IDPRODUCT = ?";
            try (PreparedStatement statementFlowers = connection.prepareStatement(sqlFlowers)) {
                statementFlowers.setInt(1, idProducto);
                try (ResultSet resultSetFlowers = statementFlowers.executeQuery()) {
                    if (resultSetFlowers.next()) {
                        nombreTabla = "FLOWERS";
                        return nombreTabla;
                    }
                }
            }


            String sqlTree = "SELECT 1 FROM FLOWERSHOP.TREE WHERE IDPRODUCT = ?";
            try (PreparedStatement statementTree = connection.prepareStatement(sqlTree)) {
                statementTree.setInt(1, idProducto);
                try (ResultSet resultSetTree = statementTree.executeQuery()) {
                    if (resultSetTree.next()) {
                        nombreTabla = "TREE";
                        return nombreTabla;
                    }
                }
            }


            String sqlDecoration = "SELECT 1 FROM FLOWERSHOP.DECORATION WHERE IDPRODUCT = ?";
            try (PreparedStatement statementDecoration = connection.prepareStatement(sqlDecoration)) {
                statementDecoration.setInt(1, idProducto);
                try (ResultSet resultSetDecoration = statementDecoration.executeQuery()) {
                    if (resultSetDecoration.next()) {
                        nombreTabla = "DECORATION";
                        return nombreTabla;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Imposible to reach the type of the product");
        }

        return nombreTabla;
    }


}