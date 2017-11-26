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
public class Retenciones {

    private int idRetencion;
    private String nombre;
    private String descripcion;
    private float tarifa;
    private String transacion;

    public Retenciones(int idRetencion, String nombre, float tarifa, String transacion) {
        this.idRetencion = idRetencion;
        this.nombre = nombre;
        this.tarifa = tarifa;
        this.transacion = transacion;
    }

    Retenciones() {
    
    }

    public float getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public int getIdRetencion() {
        return idRetencion;
    }

    public void setIdRetencion(int idRetencion) {
        this.idRetencion = idRetencion;
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
