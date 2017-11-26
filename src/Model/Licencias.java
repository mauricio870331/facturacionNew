/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author soluc
 */
public class Licencias {

    private String IdLicencia;
    private Date fecha;
    private String transacion;
    private Suscripciones suscripcion;
    private int estaciones;

    Licencias() {        
    }

    public String getIdLicencia() {
        return IdLicencia;
    }

    public void setIdLicencia(String IdLicencia) {
        this.IdLicencia = IdLicencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public Suscripciones getSuscripcion() {
        if (suscripcion == null) {
            suscripcion = new Suscripciones();
        }
        return suscripcion;
    }

    public void setSuscripcion(Suscripciones suscripcion) {
        this.suscripcion = suscripcion;
    }

    public int getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(int estaciones) {
        this.estaciones = estaciones;
    }

    public Licencias(String IdLicencia, Date fecha, String transacion, int idSuscripcion, int estaciones) {
        this.IdLicencia = IdLicencia;
        this.fecha = fecha;
        this.transacion = transacion;
        this.suscripcion = getSuscripcion();
        this.suscripcion.setIdSuscripcion(idSuscripcion);
        this.estaciones = estaciones;
    }
    
    
}
