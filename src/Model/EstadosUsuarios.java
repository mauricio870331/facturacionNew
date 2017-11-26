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
public class EstadosUsuarios {

    private int idEstadoUsuario;
    private String nombre;
    private String descripcion;
    private String transacion;

    public EstadosUsuarios(int idEstadoUsuario, String nombre, String descripcion, String transacion) {
        this.idEstadoUsuario = idEstadoUsuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.transacion = transacion;
    }

    EstadosUsuarios() {

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    public void setIdEstadoUsuario(int idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
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
