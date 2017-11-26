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
public class VersionesApp {
    
    private int idVersion;
    private String nombre;
    private String descripcion;

    public VersionesApp(int idVersion, String nombre, String descripcion) {
        this.idVersion = idVersion;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(int idVersion) {
        this.idVersion = idVersion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "VersionesApp{" + "idVersion=" + idVersion + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
