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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ventasdao.dominio.Conexion;
import ventasdao.objetos.Categoria;
import ventasdao.objetos.Factura;

/**
 *
 * @author hchanampe
 */
public class FacturaControlador implements ICrud<Factura>{
    
    
    private Connection connection;

    private Statement statementmt;

    private PreparedStatement ps;

    private ResultSet resultSet;

    private String query;
   
   

    @Override
    public boolean crear(Factura entidad) throws SQLException, Exception {
         connection = Conexion.obtenerConexion ();
        String sql = "INSERT INTO factura (id,date,fechaCreacion,date_id) VALUES (?,?,?,?)";
        Date fecha = new Date(entidad.getFechaCreacion().getTime());
        
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, entidad.getId());
            ps.setDate(2, (Date) entidad.getFechaCreacion());
           
            
         
           
            ps.executeUpdate();
            connection.close();
            

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminar(Factura entidad) throws SQLException, Exception {
         connection = Conexion.obtenerConexion();
        String sql = "DELETE FROM public. Facturas (id,date,fechaCreacion,date_id) VALUES(?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, entidad.getId());
            ps.setDate(2, (Date) entidad.getFechaCreacion());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
             Logger.getLogger(CategoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return false;
    }

    @Override
    public Factura extraer(int id) throws SQLException, Exception {
        connection = Conexion.obtenerConexion();
            query = "SELECT * FROM factura WHERE id = ?";
            ps = connection.prepareStatement(query);
            
            ps.setInt(1, id);
            
            
            this.resultSet   = ps.executeQuery();
            
            connection.close();
            
            this.resultSet.next();
            Factura factura = new Factura();
            factura.setId(id);
            factura.setFechaCreacion(resultSet.getDate("fechaCreacion"));
            
            return factura;
    }

    @Override
    public boolean modificar(Factura entidad) throws SQLException, Exception {
       connection = Conexion.obtenerConexion ();
       this.query = "UPDATE Facturas SET Id=?, fechaCreacion=? WHERE id=?";
        
       ps = connection.prepareStatement(query);
       ps.setInt(1,entidad.getId());
       ps.setDate(2, (Date) entidad.getFechaCreacion());
       ps.setInt(3, entidad.getId());
       
       ps.executeUpdate();
       connection.close();
       return true;
    }

    @Override
    public List<Factura> listar() throws SQLException, Exception {
        connection = Conexion.obtenerConexion ();
        try{
            
            this.statementmt = connection.createStatement();
            this.query = "SELECT * FROM facturas";
            this.resultSet   = statementmt.executeQuery(query);
            connection.close();
            
            ArrayList<Factura> facturas = new ArrayList();
            
            while(resultSet.next()){
                
                Factura factura = new Factura();
                
                factura.setId(resultSet.getInt("id"));
                factura.setDate(resultSet.getDate("date"));
               
                        //System.out.println(cliente);

                facturas.add(factura);
                
            }
            //System.out.println(cont);
            return facturas;
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
}
