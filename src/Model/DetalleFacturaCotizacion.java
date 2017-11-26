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
public class DetalleFacturaCotizacion {

    private int idDetalle;
    private ProductosServicios item;
    private float cantidad;
    private Facturas factura;
    private Impuestos impuesto;
    private String transacion;
    private Float valor;
    private String nomprod;
    private float descuento;
    private float subtotal;
    private int id_Impuesto;
    private String unidad_medida;

    public DetalleFacturaCotizacion(int idDetalle, int idProductoServicio, int cantidad, String idFactura, int idImpuesto, float valor, String transacion, float descuento) {
        this.idDetalle = idDetalle;
        this.item = getItem();
        this.item.setIdProductoServicio(idProductoServicio);
        this.cantidad = cantidad;
        this.factura = getFactura();
        this.factura.setIdFactura(idFactura);
        this.impuesto = getImpuesto();
        this.impuesto.setIdImpuesto(idImpuesto);
        this.transacion = transacion;
        this.valor = valor;
        this.descuento = descuento;
    }

    public DetalleFacturaCotizacion(int idDetalle, int idProductoServicio, float cantidad,
            String idFactura, float Impuesto, float valor,
            String nomprod, float descuento, float subtotal, int id_Impuesto, String unidad_medida) {
        this.idDetalle = idDetalle;
        this.item = getItem();
        this.item.setIdProductoServicio(idProductoServicio);
        this.unidad_medida = unidad_medida;
        this.cantidad = cantidad;
        this.factura = getFactura();
        this.factura.setIdFactura(idFactura);
        this.impuesto = getImpuesto();
        this.impuesto.setImpuesto2(Impuesto);
        this.valor = valor;
        this.nomprod = nomprod;
        this.descuento = descuento;
        this.subtotal = subtotal;
        this.id_Impuesto = id_Impuesto;
    }

    public DetalleFacturaCotizacion() {

    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public ProductosServicios getItem() {
        if (item == null) {
            item = new ProductosServicios();
        }
        return item;
    }

    public void setItem(ProductosServicios item) {
        this.item = item;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
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

    public Impuestos getImpuesto() {
        if (impuesto == null) {
            impuesto = new Impuestos();
        }
        return impuesto;
    }

    public void setImpuesto(Impuestos impuesto) {
        this.impuesto = impuesto;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getNomprod() {
        return nomprod;
    }

    public void setNomprod(String nomprod) {
        this.nomprod = nomprod;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public int getId_Impuesto() {
        return id_Impuesto;
    }

    public void setId_Impuesto(int id_Impuesto) {
        this.id_Impuesto = id_Impuesto;
    }

    @Override
    public String toString() {
        return "DetalleFacturaCotizacion{" + "idDetalle=" + idDetalle + ", item=" + item + ", cantidad=" + cantidad + ", factura=" + factura + ", impuesto=" + impuesto + ", transacion=" + transacion + ", valor=" + valor + ", nomprod=" + nomprod + ", descuento=" + descuento + ", subtotal=" + subtotal + ", id_Impuesto=" + id_Impuesto + '}';
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

}
