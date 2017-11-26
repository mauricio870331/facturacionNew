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
public class Impuestos {
    
    private int idImpuesto;
    private float porcentajeImpuesto;
    private String detalle;
    private String transacion;
    private float impuesto2;

    Impuestos() {
        
    }

    public int getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(int idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public float getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    public void setPorcentajeImpuesto(float porcentajeImpuesto) {
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public Impuestos(int idImpuesto, float porcentajeImpuesto, String detalle, String transacion) {
        this.idImpuesto = idImpuesto;
        this.porcentajeImpuesto = porcentajeImpuesto;
        this.detalle = detalle;
        this.transacion = transacion;
    }   

    public float getImpuesto2() {
        return impuesto2;
    }

    public void setImpuesto2(float impuesto2) {
        this.impuesto2 = impuesto2;
    }
    
    
}
