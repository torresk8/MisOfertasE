/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ariel
 */
public class Conexion {
        
    Statement instruccion;    
    String servidor="localhost:1521";
    String usuario="ofertas";
    String password="141516sk8"; //cambiar password
    
    public Conexion() 
    {
        
    }
    
    public Connection a()        
    {
        Connection conn =null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@"
                    + servidor
                    + ":XE",
                    usuario,
                    password);
        }
        catch(ClassNotFoundException | SQLException e)
        {
           e.printStackTrace();
        }
        return conn;
    }
    
    public void cerrar(Connection con,PreparedStatement ps)
    {
        try
        {
            if(con!=null)
        {
            con.close();
        }
        if(ps!=null)
        {
            ps.close();
        }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
 /*
   private Connection conexion;
    public Connection getConexion() {
        return conexion;
    }

        public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    public Conexion Conectar()
    {
        try{
        Class.forName("oracle.jdbc.OracleDriver");
        String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";
        conexion= DriverManager.getConnection(BaseDeDatos,"ofertas","141516sk8");
        if(conexion!=null)
        {
        System.out.println("Conexion exitosa a registro");
        }
        else{System.out.println("Conexion fallida");}
        }
        catch(Exception e)
        {System.out.println("error"+e);}
       
    return this;
    }*/
}
