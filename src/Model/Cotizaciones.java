/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Innova
 */
public class Cotizaciones {
    
    private String idCotizacion;
    private Date fecha;
    private String idComprador;
    private String idVendedor;
    private String idUsuario;
    private int validezDias;

    public String getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(String idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(String idComprador) {
        this.idComprador = idComprador;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getValidezDias() {
        return validezDias;
    }

    public void setValidezDias(int validezDias) {
        this.validezDias = validezDias;
    }

    public Cotizaciones(String idCotizacion, Date fecha, String idComprador, String idVendedor, String idUsuario, int validezDias) {
        this.idCotizacion = idCotizacion;
        this.fecha = fecha;
        this.idComprador = idComprador;
        this.idVendedor = idVendedor;
        this.idUsuario = idUsuario;
        this.validezDias = validezDias;
    }

    @Override
    public String toString() {
        return "Cotizaciones{" + "idCotizacion=" + idCotizacion + ", fecha=" + fecha + ", idComprador=" + idComprador + ", idVendedor=" + idVendedor + ", idUsuario=" + idUsuario + ", validezDias=" + validezDias + '}';
    } 
    
    
    
}
