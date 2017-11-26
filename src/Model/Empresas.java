/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.InputStream;

/**
 *
 * @author soluc
 */
public class Empresas {
    
    private String idEmpresa;
    private String nombreRazon;
    private String direccion;
    private String telefono;
    private String correo;
    private String celular;
    private boolean regimenComun;
    private InputStream logo;    
    private String sitioWeb;
    private int idCiudad;
    private boolean estado;
    private String patch;
    private String transacion;   
    private String info;

    public Empresas(String idEmpresa,
                    String nombreRazon,
                    String direccion,
                    String telefono,
                    String correo,
                    String celular,
                    boolean regimenComun, 
                    InputStream logo,
                    String sitioWeb, int idCiudad, boolean estado,  String transacion, String info, String patch) {
        this.idEmpresa = idEmpresa;
        this.nombreRazon = nombreRazon;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.celular = celular;
        this.regimenComun = regimenComun;
        this.sitioWeb = sitioWeb;
        this.idCiudad = idCiudad;
        this.estado = estado;
        this.logo = logo;
        this.transacion = transacion;
        this.info = info;
        this.patch = patch;
    }       

    Empresas() {        
    }  
    
    

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreRazon() {
        return nombreRazon;
    }

    public void setNombreRazon(String nombreRazon) {
        this.nombreRazon = nombreRazon;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isRegimenComun() {
        return regimenComun;
    }

    public void setRegimenComun(boolean regimenComun) {
        this.regimenComun = regimenComun;
    }

    public InputStream getLogo() {
        return logo;
    }

    public void setLogo(InputStream logo) {
        this.logo = logo;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    } 

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
}
