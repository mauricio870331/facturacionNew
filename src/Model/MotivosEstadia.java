/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Adalberto Gomez
 */
public class MotivosEstadia {
    
    private int idMotivo;
    private String nombre;
    private String transacion;

    MotivosEstadia() {
       
    }

    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
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

    public MotivosEstadia(int idMotivo, String nombre, String transacion) {
        this.idMotivo = idMotivo;
        this.nombre = nombre;
        this.transacion = transacion;
    }   
    
    
}
