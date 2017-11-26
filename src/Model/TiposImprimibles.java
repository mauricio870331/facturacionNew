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
public class TiposImprimibles {
    
    private int idTipoImprimible;
    private String nombre;
    private String descripcion;
    private String transacion;

    TiposImprimibles() {
        
    }

    public int getIdTipoImprimible() {
        return idTipoImprimible;
    }

    public void setIdTipoImprimible(int idTipoImprimible) {
        this.idTipoImprimible = idTipoImprimible;
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

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public TiposImprimibles(int idTipoImprimible, String nombre, String descripcion, String transacion) {
        this.idTipoImprimible = idTipoImprimible;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.transacion = transacion;
    }
       
    
    
}
