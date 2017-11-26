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
public class Suscripciones {
    
    private int idSuscripcion;
    private String nombre;
    private String descripcion;

    public Suscripciones(int idSuscripcion, String nombre, String descripcion) {
        this.idSuscripcion = idSuscripcion;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    Suscripciones() {
        
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Suscripciones{" + "idSuscripcion=" + idSuscripcion + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
