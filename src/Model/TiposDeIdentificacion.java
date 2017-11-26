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
public class TiposDeIdentificacion {
    
    private int idTipoIdentificacion;
    private String nombre;
    private String descripcion;

    public TiposDeIdentificacion(int idTipoIdentificacion, String nombre, String descripcion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    TiposDeIdentificacion() {
        
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(int idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TiposDeIdentificacion{" + "idTipoIdentificacion=" + idTipoIdentificacion + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
