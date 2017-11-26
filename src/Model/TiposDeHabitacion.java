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
public class TiposDeHabitacion {

    private int idTipoHabitacion;
    private String nombre;
    private float costoPersona;
    private float costoPareja;
    private float costoPersonaAdicional;
    private Empresas empresa;
    private String transacion;

    TiposDeHabitacion() {

    }

    public TiposDeHabitacion(int idTipoHabitacion, float costoPersona, float costoPareja, float costoPersonaAdicional) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.costoPersona = costoPersona;
        this.costoPareja = costoPareja;
        this.costoPersonaAdicional = costoPersonaAdicional;
    }

    public int getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setIdTipoHabitacion(int idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCostoPersona() {
        return costoPersona;
    }

    public void setCostoPersona(float costoPersona) {
        this.costoPersona = costoPersona;
    }

    public float getCostoPareja() {
        return costoPareja;
    }

    public void setCostoPareja(float costoPareja) {
        this.costoPareja = costoPareja;
    }

    public float getCostoPersonaAdicional() {
        return costoPersonaAdicional;
    }

    public void setCostoPersonaAdicional(float costoPersonaAdicional) {
        this.costoPersonaAdicional = costoPersonaAdicional;
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

    public TiposDeHabitacion(int idTipoHabitacion, String nombre, float costoPersona, float costoPareja, float costoPersonaAdicional, String idEmpresa, String transacion) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.nombre = nombre;
        this.costoPersona = costoPersona;
        this.costoPareja = costoPareja;
        this.costoPersonaAdicional = costoPersonaAdicional;
        this.empresa = getEmpresa();
        this.empresa.setIdEmpresa(idEmpresa);
        this.transacion = transacion;
    }

}
