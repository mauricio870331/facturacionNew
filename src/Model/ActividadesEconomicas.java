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
public class ActividadesEconomicas {

    private int idActividadEconomica;
    private String nombre;
    private String descripcion;
    private String transacion;
    
    

    public ActividadesEconomicas(int idActividadEconomica, String nombre, String descripcion, String transacion) {
        this.idActividadEconomica = idActividadEconomica;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.transacion = transacion;
    }

    ActividadesEconomicas() {        
    }
        

    public int getIdActividadEconomica() {
        return idActividadEconomica;
    }

    public void setIdActividadEconomica(int idActividadEconomica) {
        this.idActividadEconomica = idActividadEconomica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }   

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }
    
    
    
}
