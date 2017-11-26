/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author soluc
 */
public class EstadosFacturas {
    
    private int idEstadoFactura;
    private String nombre;
    private String descripcion;
    private String transacion;

    EstadosFacturas() {        
    }

    public int getIdEstadoFactura() {
        return idEstadoFactura;
    }

    public void setIdEstadoFactura(int idEstadoFactura) {
        this.idEstadoFactura = idEstadoFactura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadosFacturas(int idEstadoFactura, String nombre, String descripcion, String transacion) {
        this.idEstadoFactura = idEstadoFactura;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.transacion = transacion;
    }
    

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }
    
    
}
