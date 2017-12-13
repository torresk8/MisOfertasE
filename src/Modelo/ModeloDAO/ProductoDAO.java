/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.ModeloDAO;

import Modelo.Conexion;
import Modelo.DTO.Producto;
import Modelo.DTO.TipoProducto;
import Modelo.DTO.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Ariel
 */
public class ProductoDAO {
 
     Conexion c=new Conexion();
     
    public ProductoDAO() 
    {
        
       
    }
    
    public String insertProducto(String nombre,String descripcion,int precio,int idTipoProducto){
        String rptaRegistro=null;
        
        try {
            Connection cn = c.a();            
            PreparedStatement ps = cn.prepareStatement("insert into producto(idProducto,nombre,descripcion,precio,idTipoProducto)values(sucuence_producto.nextval,?,?,?,?)");
            //Procedimiento almacenado
            //CallableStatement cs = accesoDB.prepareCall("{call sp_insertPersona(?,?,?,?,?)}");
            ps.setString(1, nombre);
            ps.setString(2, descripcion);          
            ps.setInt(3, precio);          
            ps.setInt(4, idTipoProducto);          
            
            int n = ps.executeUpdate();
            
            if(n>0){
                rptaRegistro="Registro exitoso.";
            }
        } catch (Exception e) {
            rptaRegistro="Error al insertar los datos";
        }
        return rptaRegistro;
    }
    
        public String actualizarProducto(int idProducto,String nombre,String descripcion,int precio){
        String rptaRegistro=null;
        Connection cn =null;
        PreparedStatement ps = null;
        
        try {
            cn= c.a();            
            ps = cn.prepareStatement("update producto set nombre=?,descripcion=?,precio=? where idProducto=?");
            //Procedimiento almacenado
            //CallableStatement cs = accesoDB.prepareCall("{call sp_insertPersona(?,?,?,?,?)}");
            ps.setString(1, nombre);
            ps.setString(2, descripcion);          
            ps.setInt(3, precio);    
            ps.setInt(4, idProducto);
            
            int n = ps.executeUpdate();
            
            if(n>0){
                rptaRegistro="Datos actualizados";
            }
        } catch (Exception e) {
            rptaRegistro="Error: "+e.getMessage();
        }finally
        {
            c.cerrar(cn, ps);
        }
        
        return rptaRegistro;
    }
   
          public String eliminarProducto(int idProducto){
        String rptaRegistro=null;
        Connection cn =null;
        PreparedStatement ps = null;
        
        try {
            cn= c.a();            
            ps = cn.prepareStatement("delete producto where idProducto=?");
            //Procedimiento almacenado
            //CallableStatement cs = accesoDB.prepareCall("{call sp_insertPersona(?,?,?,?,?)}");        
            ps.setInt(1, idProducto);
            
            int n = ps.executeUpdate();
            
            if(n>0){
                rptaRegistro="Datos Eliminados";
            }
        } catch (Exception e) {
            rptaRegistro="Error: "+e.getMessage();
        }finally
        {
            c.cerrar(cn, ps);
        }
        
        return rptaRegistro;
    }
          
   
          public Producto RetornaProducto(int idProducto){        
        Producto p = new Producto();
          Connection cn =null;
        PreparedStatement ps = null;
        try {
             cn = c.a();
           
            ps = cn.prepareStatement("select p.idProducto,p.nombre,p.descripcion,p.precio,t.idTipoProducto idTipoProducto,t.nombre t_nombre from producto p, tipoProducto t where  p.idProducto =?");
            ps.setInt(1, idProducto);            
            
           ResultSet rs = ps.executeQuery();                    
                while(rs.next())
                {
                    p.setIdProducto(Integer.parseInt(rs.getString(1)));
                    p.setNombre(rs.getString(2));
                    p.setDescripcion(rs.getString(3));
                    p.setPrecio(Integer.parseInt(rs.getString(4)));
                    p.idTipoProducto.setIdTipoProducto(Integer.parseInt(rs.getString(5)));
                    p.idTipoProducto.setNombre(rs.getString(6));                          
                }
            
        } catch (Exception e) {
            e.getMessage();
        }finally
        {
            c.cerrar(cn, ps);
        }
        return p;
    }
   
   public ArrayList<Producto> listaProducto(){
        ArrayList listaProducto = new ArrayList();
        Producto p;
        Connection cn =null;
        PreparedStatement ps = null;
        try {
             cn = c.a();
             ps = cn.prepareStatement("select p.idProducto,p.nombre,p.descripcion,p.precio,t.idTipoProducto idTipoProducto,"
                    + "t.nombre t_nombre from producto p, tipoProducto t where  p.idTipoProducto = t.idTipoProducto order by p.idProducto");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p = new Producto();
               p.setIdProducto(Integer.parseInt(rs.getString(1)));
                    p.setNombre(rs.getString(2));
                    p.setDescripcion(rs.getString(3));
                    p.setPrecio(Integer.parseInt(rs.getString(4)));                    
                    p.idTipoProducto.setIdTipoProducto(Integer.parseInt(rs.getString(5)));
                    p.idTipoProducto.setNombre(rs.getString(6));              
                    listaProducto.add(p);
            }
        } catch (Exception e) {
            e.getMessage();
        }finally
        {
            c.cerrar(cn, ps);
        }
        return listaProducto;
    }
    

}


