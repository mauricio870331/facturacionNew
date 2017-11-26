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
public class DetalleFacturaProveedor {
    
    private int idDfp;
    private FacturasProveedor factura;
    private float valor;
    private CategoriasPagos item;
    private Impuestos impuesto;
    private int cantidad;
    private String observaciones;
    private String transacion;

    public int getIdDfp() {
        return idDfp;
    }

    public void setIdDfp(int idDfp) {
        this.idDfp = idDfp;
    }

    public FacturasProveedor getFactura() {
        if (factura == null) {
           factura = new FacturasProveedor(); 
        }
        return factura;
    }

    public void setFactura(FacturasProveedor factura) {
        this.factura = factura;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public CategoriasPagos getItem() {
        if (item == null) {
            item = new CategoriasPagos();
        }
        return item;
    }

    public void setItem(CategoriasPagos item) {
        this.item = item;
    }

    public Impuestos getImpuesto() {
        if (impuesto == null) {
            impuesto = new Impuestos();
        }
        return impuesto;
    }

    public void setImpuesto(Impuestos impuesto) {
        this.impuesto = impuesto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public DetalleFacturaProveedor(int idDfp, int idFacturaProveedor, float valor, int idCategoriaPago, int idImpuesto, int cantidad, String observaciones, String transacion) {
        this.idDfp = idDfp;
        this.factura = getFactura();
        this.factura.setIdFacturaProveedor(idFacturaProveedor);
        this.valor = valor;
        this.item = getItem();
        this.item.setIdCategoriaPago(idCategoriaPago);
        this.impuesto = getImpuesto();
        this.impuesto.setIdImpuesto(idImpuesto);
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        this.transacion = transacion;
    }   
    
    
    
}
