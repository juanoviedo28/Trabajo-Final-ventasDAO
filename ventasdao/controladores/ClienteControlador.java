/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventasdao.controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import ventasdao.dominio.Conexion;
import ventasdao.objetos.Categoria;
import ventasdao.objetos.Cliente;

/**
 *
 * @author Hugo Chanampe
 */
public class ClienteControlador implements ICrud<Cliente>{
    
    private Connection connection;
    
    private Statement stmt;
    
    private PreparedStatement ps;
    
    private ResultSet rs;
    
    private String sql;
    
    

    //public void modificarCategoria(Categoria c);
    //public Categoria obtenerCategoria(Integer id);
    //public void eliminarCategoria(Categoria c);
    
    @Override
    public boolean crear(Cliente entidad) throws SQLException, Exception{

        connection = Conexion.obtenerConexion ();
        String sql = "INSERT INTO clientes (documento, nombre, apellido, tipoCliente, fechaNacimiento, cuil) VALUES (?,?,?,?,?,?)";
        Date fecha = new Date(entidad.getFechaNacimiento().getTime());
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getApellido ());
            ps.setString(3, entidad.getCuil());
            ps.setDate(4, fecha);
            ps.setInt(5, entidad.getTipoCliente().getId());
            ps.setString(6, entidad.getDocumento());
            ps.executeUpdate();
            connection.close();
            

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminar(Cliente entidad) throws SQLException, Exception {
        connection = Conexion.obtenerConexion();
        String sql = "DELETE FROM public. Clientes (nombre,apellido,cuil,fecha_nacimiento, categoria_id) VALUES(?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getApellido());
            ps.setString(3, entidad.getCuil());
            ps.setInt(4, entidad.getTipoCliente().getId());
            
            
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
             Logger.getLogger(CategoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return false;
    }

    @Override
    public ArrayList<Cliente> listar() throws SQLException,Exception{
        
     connection = Conexion.obtenerConexion ();
        try{
            
            this.stmt = connection.createStatement();
            this.sql = "SELECT * FROM clientes";
            this.rs   = stmt.executeQuery(sql);
            connection.close();
            
            ArrayList<Cliente> clientes = new ArrayList();
            
            while(rs.next()){
                
                Cliente cliente = new Cliente();
                
                cliente.setNombre(rs.getString("nombre"));
                cliente.setCuil(rs.getString("cuil"));
                cliente.setId(rs.getInt("id"));
                cliente.setApellido (rs.getString("apellido"));
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                
                
                        //System.out.println(cliente);

                clientes.add(cliente);
                
            }
            //System.out.println(cont);
            return clientes;
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    

    }

    @Override
    public boolean modificar(Cliente entidad) throws SQLException, Exception {
         connection = Conexion.obtenerConexion ();
       this.sql = "UPDATE clientes SET nombre=?, apellido=? WHERE id=?";
        
       ps = connection.prepareStatement(sql);
       ps.setString(1,entidad.getNombre());
       ps.setString(2,entidad.getApellido());
       ps.setInt(3, entidad.getId());
       ps.setString(4, entidad.getCuil());
       
       ps.executeUpdate();
       connection.close();
       return true;
    }

    @Override
    public Cliente extraer(int id) throws SQLException, Exception {
        connection = Conexion.obtenerConexion();
            sql = "SELECT * FROM Clientes WHERE id = ?";
            ps = connection.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            
            this.rs   = ps.executeQuery();
            
            connection.close();
            
            this.rs.next();
            Cliente cliente = new Cliente();
            cliente.setNombre(rs.getString("nombre"));
            cliente.setId(rs.getInt("id"));
            cliente.setCuil(rs.getString("cuil"));
            cliente.setApellido(rs.getString("apellido"));
            return cliente;
    }

    
    
}
