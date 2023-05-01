/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class conexion {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public conexion() {
        //  conexiones();
    }

    public void conexiones() {
        String url = "jdbc:postgresql://localhost:5432/sacru";
        String usuario = "postgres";
        String contraseña = "1122628133Ma";

        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url, usuario, contraseña);
         //   JOptionPane.showMessageDialog(null,"Conectado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error de conexion " + e.getMessage());
        }

    }

//    public static void main(String[] args) {
//        conexion obj = new conexion();
//        obj.conexiones();
//    }

}
