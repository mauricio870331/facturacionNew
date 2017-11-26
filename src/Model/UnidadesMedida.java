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
public class UnidadesMedida {
    
    private int idUnidadMedida;
    private String nombre;
    private String descripcion;

    public UnidadesMedida(int idUnidadMedida, String nombre, String descripcion) {
        this.idUnidadMedida = idUnidadMedida;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    UnidadesMedida() {
       
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(int idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "UnidadesMedida{" + "idUnidadMedida=" + idUnidadMedida + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
    
}
