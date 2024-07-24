package com.manual.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestCatch {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("admintx_manual_escritorio.properties")) {
            properties.load(input);
            System.out.println("Configuración cargada en main: " + properties);

            String driver = properties.getProperty("jdbc.driver");
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            // Cargar el driver de Oracle
            System.out.println("Intentando cargar el driver: " + driver);
            Class.forName(driver);
            System.out.println("Driver cargado exitosamente.");

            // Establecer la conexión
            System.out.println("Intentando establecer la conexión con URL: " + url);
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
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de configuración.");
            e.printStackTrace();
        }
    }
}
