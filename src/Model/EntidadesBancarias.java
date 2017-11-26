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
public class EntidadesBancarias {
    
    private int idEntidadBancaria;
    private String entidad;
    private int codigoBanco;
    private boolean cuentaNacional;
    private String transacion;

    EntidadesBancarias() {
        
    }

    public int getIdEntidadBancaria() {
        return idEntidadBancaria;
    }

    public void setIdEntidadBancaria(int idEntidadBancaria) {
        this.idEntidadBancaria = idEntidadBancaria;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public int getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(int codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public boolean isCuentaNacional() {
        return cuentaNacional;
    }

    public void setCuentaNacional(boolean cuentaNacional) {
        this.cuentaNacional = cuentaNacional;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public EntidadesBancarias(int idEntidadBancaria, String entidad, int codigoBanco, boolean cuentaNacional, String transacion) {
        this.idEntidadBancaria = idEntidadBancaria;
        this.entidad = entidad;
        this.codigoBanco = codigoBanco;
        this.cuentaNacional = cuentaNacional;
        this.transacion = transacion;
    }
    
    
}
