/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.ModeloDAO;

import Modelo.Conexion;
import Modelo.DTO.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Ariel
 */
public class UsuarioDAO {
      
    Conexion c=new Conexion();
    public UsuarioDAO() 
    {
        
       
    }
    
    public String MD5(String md5)
    {
        try
        {              
                
               java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
               byte[] array = md.digest(md5.getBytes());
               StringBuffer sb = new StringBuffer();
               
               for (int i = 0; i < 10; i++) {
                sb.append(Integer.toHexString((array[i]& 0xFF)| 0x100).substring(1,3));
            }
            return sb.toString();
        }catch(java.security.NoSuchAlgorithmException e)
        {
            e.getMessage();
        }
        return null;
    }
    
   public String insertUsuario(String usuario,String password,String nombre){
        String rptaRegistro=null;
        Connection cn =null;
        PreparedStatement ps = null;
        try {
            cn = c.a();            
            ps = cn.prepareStatement("insert into usuario(idUsuario,nombre,nombreUsuario,password,rut,direccion,telefono,recibirCorreo)"
                    + "values(sequence_login.nextval,?,?,?,?,?,?,?)");
            //CallableStatement cs = accesoDB.prepareCall("{call sp_insertPersona(?,?,?,?,?)}");
            ps.setString(1, nombre);
            ps.setString(2, usuario);          
            ps.setString(3, password);                                
            ps.setString(4, "");   
            ps.setString(5, "");   
            ps.setString(6, "");   
            ps.setString(7, "");   
            
            int n = ps.executeUpdate();
            
            if(n>0){
                rptaRegistro="Registro exitoso.";
            }
        } catch (Exception e) {
            rptaRegistro="Error: "+e.getMessage();
        }finally
        {
            c.cerrar(cn, ps);
        }
        return rptaRegistro;
    }
   
   public Usuario RetornaUsuario(String usuario,String password){        
        Usuario u = new Usuario();
         Connection cn =null;
        PreparedStatement ps = null;
        try {
            cn = c.a();
           
            ps = cn.prepareStatement("select * from usuario where nombreUsuario=? and password=?");
            ps.setString(1, usuario);
            ps.setString(2, password);
            
           ResultSet rs = ps.executeQuery();                    
                while(rs.next())
                {
                u.setIdUsuario(Integer.parseInt(rs.getString(1)));                
                u.setUsuario(rs.getString(3));
                u.setPassword(rs.getString(4));              
                }
            
        } catch (Exception e) {
            e.getMessage();
        }
        finally
        {
            c.cerrar(cn, ps);
        }
        return u;
    }
   
   public ArrayList<Usuario> listaUsuario(){
        ArrayList listaUsuario = new ArrayList();
        Usuario u;
         Connection cn =null;
        PreparedStatement ps = null;
        try {
             cn = c.a();
             ps = cn.prepareStatement("select * from usuario");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                u = new Usuario();
                u.setIdUsuario(Integer.parseInt(rs.getString(1)));
                u.setUsuario(rs.getString(2));
                u.setPassword(rs.getString(3));           
                listaUsuario.add(u);
            }
        } catch (Exception e) {
            e.getMessage();
        }finally
        {
            c.cerrar(cn, ps);
        }
        return listaUsuario;
    }
    
}
