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
public class Cuentas {
    
    private int idCuenta;
    private String nombre;
    private String puc;
    private String detalle;
    private String transacion;

    Cuentas() {
        
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuc() {
        return puc;
    }

    public void setPuc(String puc) {
        this.puc = puc;
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

    public Cuentas(int idCuenta, String nombre, String puc, String detalle, String transacion) {
        this.idCuenta = idCuenta;
        this.nombre = nombre;
        this.puc = puc;
        this.detalle = detalle;
        this.transacion = transacion;
    }    
    
    
    
}
