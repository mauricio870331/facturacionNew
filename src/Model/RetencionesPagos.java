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
public class RetencionesPagos {
    
    private int idRetencionpagos;
    private Facturas factura;
    private Retenciones retencion;
    private float valor;
    private String transacion;

    public int getIdRetencionpagos() {
        return idRetencionpagos;
    }

    public void setIdRetencionpagos(int idRetencionpagos) {
        this.idRetencionpagos = idRetencionpagos;
    }    

    public Retenciones getRetencion() {
        if (retencion == null) {
            retencion = new Retenciones();
        }
        return retencion;
    }

    public void setRetencion(Retenciones retencion) {
        this.retencion = retencion;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public RetencionesPagos(int idRetencionpagos, String idFactura, int idRetencion, float valor, String transacion) {
        this.idRetencionpagos = idRetencionpagos;
        this.factura = getFactura();
        this.factura.setIdFactura(idFactura);
        this.retencion = getRetencion();
        this.retencion.setIdRetencion(idRetencion);
        this.valor = valor;
        this.transacion = transacion;
    } 
    
    public RetencionesPagos(String idFactura, int idRetencion, float valor) {       
        this.factura = getFactura();
        this.factura.setIdFactura(idFactura);
        this.retencion = getRetencion();
        this.retencion.setIdRetencion(idRetencion);
        this.valor = valor;        
    }  

    public Facturas getFactura() {
        if (factura == null) {
            factura = new Facturas();
        }
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    @Override
    public String toString() {
        return "RetencionesPagos{" + "factura=" + factura.getIdFactura() + ", retencion=" + retencion.getIdRetencion() + ", valor=" + valor  + '}';
    }
    
    
    
       
    
}
