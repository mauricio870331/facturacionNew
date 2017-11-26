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
public class TiposResoluciones {
    
    private int idTipoResolucion;
    private String nombre;
    private String descripcion;

    public TiposResoluciones(int idTipoResolucion, String nombre, String descripcion) {
        this.idTipoResolucion = idTipoResolucion;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipoResolucion() {
        return idTipoResolucion;
    }

    public void setIdTipoResolucion(int idTipoResolucion) {
        this.idTipoResolucion = idTipoResolucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TiposResoluciones{" + "idTipoResolucion=" + idTipoResolucion + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
