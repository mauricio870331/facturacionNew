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
public class Departamentos {

    private int idDepartamento;
    private String nombre;
    private String transacion;   
    

    Departamentos() {

    }

    public Departamentos(int idDepartamento, String nombre, String transacion) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.transacion = transacion;
    }   
    
   

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
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
    

}
