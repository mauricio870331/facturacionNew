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
public final class Ciudades {
    
    private int idCiudad;
    private String nombre;
    private Departamentos dpto;

    Ciudades() {
        
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  

    public Ciudades(int idCiudad, String nombre, int idDepartamento) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.dpto = getDpto();
        this.dpto.setIdDepartamento(idDepartamento);
    }

    @Override
    public String toString() {
        return "Ciudades{" + "idCiudad=" + idCiudad + ", nombre=" + nombre + ", idDepartamento=" + dpto.getIdDepartamento() + '}';
    }    

    public Departamentos getDpto() {
        if (dpto == null) {
            dpto = new Departamentos();
        }
        return dpto;
    }

    public void setDpto(Departamentos dpto) {
        this.dpto = dpto;
    }
    
    
}
