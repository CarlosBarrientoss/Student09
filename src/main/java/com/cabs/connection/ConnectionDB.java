package com.cabs.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection getConnectionBD(){

        //Objeto para guardar la conexión
        Connection connection = null;

        //Variables de conexión
        String db = "sena_db";
        String url = "jdbc:mysql://localhost:3306/" + db;
        String name = "root";
        String pass = "1312";

        try {
            //drive para comprobar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //intento conectarme a la base de datos
            connection = DriverManager.getConnection(url,name,pass);
        }catch (ClassNotFoundException | SQLException ex){
            System.out.println("Ocurrio un error en la conexión: "+ ex.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection connection2 = ConnectionDB.getConnectionBD();

        if(connection2 != null){
            System.out.println("Conexión exitosa: " + connection2);
        }else{
            System.out.println("Error en la conexión: " + connection2);
        }
    }

}
