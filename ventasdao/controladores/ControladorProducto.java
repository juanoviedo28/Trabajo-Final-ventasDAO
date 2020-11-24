
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
import ventasdao.objetos.Cliente;
import ventasdao.objetos.Producto;


public class ControladorProducto implements ICrud <Producto>{
    
    private Connection connection;
            
    private Statement statement;
    
    private PreparedStatement preparedStatement;
    
    private ResultSet resultSet;
    
    private String query;
    
    private CategoriaControlador categoriacontrolador;

    @Override
    public boolean crear(Producto entidad) throws SQLException, Exception {
        connection = Conexion.obtenerConexion ();
        String sql = "INSERT INTO productos (nombre,descripcion,precio,fecha_creacion, categoria_id) VALUES (?,?,?,?,?)";
        Date fecha = new Date(entidad.getFechaCreacion().getTime());
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entidad.getNombre());
            preparedStatement.setString(2, entidad.getDescripcion());
            preparedStatement.setFloat(3, entidad.getPrecio());
            preparedStatement.setDate(4, fecha);
            preparedStatement.setInt(5, entidad.getCategoria().getId());
            preparedStatement.executeUpdate();
            connection.close();
            

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminar(Producto entidad) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Producto extraer(int id) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(Producto entidad) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Producto> listar() throws SQLException, Exception {
       connection = Conexion.obtenerConexion ();
        try{
            
            this.statement = connection.createStatement();
            this.query = "SELECT * FROM productos";
            this.resultSet   = statement.executeQuery(query);
            connection.close();
            
            ArrayList<Producto> productos = new ArrayList();
            
            while(resultSet.next()){
                
                Producto producto = new Producto();
                
                producto.setNombre(resultSet.getString("nombre"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setId(resultSet.getInt("id"));
                producto.setPrecio (resultSet.getFloat("precio"));
                producto.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                producto.setCategoria(getCategoria(resultSet.getInt("categoria_id")));
                
                
                        //System.out.println();

                productos.add(producto);
                
            }
            //System.out.println(cont);
            return productos;
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
     private Categoria getCategoria(Integer id) throws Exception, Exception{
     this.categoriacontrolador = new CategoriaControlador();
     
     Categoria categoria = categoriacontrolador.extraer(id);
     
     return categoria;
 }   
    
    
    
    
}
