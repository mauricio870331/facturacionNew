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
public class Franquicias {
    
    private int idFranquicia;
    private String nombre;
    private String transacion;

    Franquicias() {
       
    }

    public int getIdFranquicia() {
        return idFranquicia;
    }

    public void setIdFranquicia(int idFranquicia) {
        this.idFranquicia = idFranquicia;
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

    public Franquicias(int idFranquicia, String nombre, String transacion) {
        this.idFranquicia = idFranquicia;
        this.nombre = nombre;
        this.transacion = transacion;
    }
    
    
    
}
