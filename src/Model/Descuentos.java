/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Innova
 */
public class Descuentos {
    
    private int idDescuento;
    private String nombre;
    private String descuento;
    private int porcentajeDescuento;
    private String transacion;

    Descuentos() {
        
    }
       

    public int getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public int getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(int porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Descuentos(int idDescuento, String nombre, String descuento, int porcentajeDescuento, String transacion) {
        this.idDescuento = idDescuento;
        this.nombre = nombre;
        this.descuento = descuento;
        this.porcentajeDescuento = porcentajeDescuento;
        this.transacion = transacion;
    }

    @Override
    public String toString() {
        return "Descuentos{" + "idDescuento=" + idDescuento + ", nombre=" + nombre + ", descuento=" + descuento + ", porcentajeDescuento=" + porcentajeDescuento + '}';
    }   

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }
    
    
    
}
