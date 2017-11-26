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
public class ResolucionesFacturas {

    private String idResolucion;
    private Date fecha;
    private String prefijo;
    private int consecutivoInicial;
    private int consecutivoFinal;
    private int idTipoResolucion;
    private boolean activo;
    private Empresas empresa;
    private String transacion;

    ResolucionesFacturas() {
    }

    public String getIdResolucion() {
        return idResolucion;
    }

    public void setIdResolucion(String idResolucion) {
        this.idResolucion = idResolucion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public int getConsecutivoInicial() {
        return consecutivoInicial;
    }

    public void setConsecutivoInicial(int consecutivoInicial) {
        this.consecutivoInicial = consecutivoInicial;
    }

    public int getConsecutivoFinal() {
        return consecutivoFinal;
    }

    public void setConsecutivoFinal(int consecutivoFinal) {
        this.consecutivoFinal = consecutivoFinal;
    }

    public int getIdTipoResolucion() {
        return idTipoResolucion;
    }

    public void setIdTipoResolucion(int idTipoResolucion) {
        this.idTipoResolucion = idTipoResolucion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
    public ResolucionesFacturas(String idResolucion, Date fecha, String prefijo, int consecutivoInicial, int consecutivoFinal, int idTipoResolucion, boolean activo, String idEmpresa, String transacion) {
        this.idResolucion = idResolucion;
        this.fecha = fecha;
        this.prefijo = prefijo;
        this.consecutivoInicial = consecutivoInicial;
        this.consecutivoFinal = consecutivoFinal;
        this.idTipoResolucion = idTipoResolucion;
        this.activo = activo;
        this.empresa = getEmpresa();
        this.empresa.setIdEmpresa(idEmpresa);
        this.transacion = transacion;
    }

    
    public Empresas getEmpresa() {
        if (empresa == null) {
            empresa = new Empresas();
        }
        return empresa;
    }

    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

}
