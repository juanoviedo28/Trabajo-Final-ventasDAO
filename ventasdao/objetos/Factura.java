/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventasdao.objetos;

import java.util.Date;
import java.util.List;
import ventasdao.controladores.FacturaControlador;

/**
 *
 * @author hchanampe
 */
public class Factura {
    
    private Integer id;
    
    private Date date;
    
    private Date fechaCreacion;
    
    
    
   
    
    private List<LineaFactura> lineaFactura;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

   
    
    
    
    
}
