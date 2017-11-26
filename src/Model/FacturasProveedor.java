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
public class FacturasProveedor {
    
    private int idFacturaProveedor;
    private Clientes proveedor;
    private String observaciones;
    private String numeroFactura;
    private Date fechaFactura;
    private Date fechaVencimiento;
    private float valor;
    private String transacion;

    FacturasProveedor() {
        
    }

    public int getIdFacturaProveedor() {
        return idFacturaProveedor;
    }

    public void setIdFacturaProveedor(int idFacturaProveedor) {
        this.idFacturaProveedor = idFacturaProveedor;
    }

    public Clientes getProveedor() {
        if (proveedor == null) {
            proveedor = new Clientes();
        }
        return proveedor;
    }

    public void setProveedor(Clientes proveedor) {
        this.proveedor = proveedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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

    public FacturasProveedor(int idFacturaProveedor, int idCliente, String observaciones, String numeroFactura, Date fechaFactura, Date fechaVencimiento, float valor, String transacion) {
        this.idFacturaProveedor = idFacturaProveedor;
        this.proveedor = getProveedor();
        this.proveedor.setIdCliente(idCliente);
        this.observaciones = observaciones;
        this.numeroFactura = numeroFactura;
        this.fechaFactura = fechaFactura;
        this.fechaVencimiento = fechaVencimiento;
        this.valor = valor;
        this.transacion = transacion;
    }    
    
    
}
