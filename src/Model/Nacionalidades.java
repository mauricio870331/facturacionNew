/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Adalberto Gomez
 */
public class Nacionalidades {
    
    private int idNacionalidad;
    private String codigo;
    private String pais;
    private String transacion;

    Nacionalidades() {
        
    }

    public int getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(int idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public Nacionalidades(int idNacionalidad, String codigo, String pais, String transacion) {
        this.idNacionalidad = idNacionalidad;
        this.codigo = codigo;
        this.pais = pais;
        this.transacion = transacion;
    }
    
    
    
}
