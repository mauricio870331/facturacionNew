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
public class CategoriasPagos {
    
    private int idCategoriaPago;
    private String nombre;
    private String detalle;
    private String transacion;

    CategoriasPagos() {        
    }

    public int getIdCategoriaPago() {
        return idCategoriaPago;
    }

    public void setIdCategoriaPago(int idCategoriaPago) {
        this.idCategoriaPago = idCategoriaPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public CategoriasPagos(int idCategoriaPago, String nombre, String detalle, String transacion) {
        this.idCategoriaPago = idCategoriaPago;
        this.nombre = nombre;
        this.detalle = detalle;
        this.transacion = transacion;
    }
    
    
    
}
