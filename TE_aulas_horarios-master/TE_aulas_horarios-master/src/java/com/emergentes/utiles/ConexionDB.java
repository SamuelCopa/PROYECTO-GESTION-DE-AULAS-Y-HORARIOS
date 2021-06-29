package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.*;

public class ConexionDB {

    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bd_aulas_horarios";
    static String usuario = "root";
    static String password = "jhin6730026";

    protected Connection conn = null;

    public ConexionDB() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
            if (conn != null) {
                System.out.println("Conexion Ok" + conn);
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Falta en driver " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error al conectar " + ex.getMessage());
        }
    }

    public Connection conectar() {
        return conn;
    }

    public void desconectar() {
        System.out.println("Cerrando la BD: " + conn);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error de SQL " + ex.getMessage());
        }

    }

}
