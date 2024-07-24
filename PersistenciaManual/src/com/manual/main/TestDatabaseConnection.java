package com.manual.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@//localhost:1521/XE";
        String username = "system";
        String password = "1234";

        try {
            // Cargar el driver de Oracle
            Class.forName(driver);

            // Establecer la conexión
            Connection connection = DriverManager.getConnection(url, username, password);

            // Si la conexión es exitosa, imprimir un mensaje
            System.out.println("Conexión establecida con éxito!");

            // Cerrar la conexión
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el driver de Oracle.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
            e.printStackTrace();
        }
    }
}
