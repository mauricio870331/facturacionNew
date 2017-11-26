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
public class TerminosDePago {
    
    private int idTerminoPago;
    private String nombre;
    private String transacion;

    TerminosDePago() {
        
    }

    public int getIdTerminoPago() {
        return idTerminoPago;
    }

    public void setIdTerminoPago(int idTerminoPago) {
        this.idTerminoPago = idTerminoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public TerminosDePago(int idTerminoPago, String nombre, String transacion) {
        this.idTerminoPago = idTerminoPago;
        this.nombre = nombre;
        this.transacion = transacion;
    }    
    
    
}
