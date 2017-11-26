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
public class TiposDePagos {
    
    private int idTipoPago;
    private String nombre;
    private String descripcion;

    public TiposDePagos(int idTipoPago, String nombre, String descripcion) {
        this.idTipoPago = idTipoPago;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    TiposDePagos() {
        
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TiposDePagos{" + "idTipoPago=" + idTipoPago + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
