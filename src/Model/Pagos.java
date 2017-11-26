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
public class Pagos {

    private int idPago;
    private Clientes cliente;
    private Cuentas cuenta;
    private Date fecha;
    private String observaciones;
    private String notaEgreso;
    private TiposDePagos pago;
    private ComprobantesEgreso comprobante;
    private RecibosCaja reciboCaja;
    private float valor;
    private FacturasProveedor factura;
    private Facturas factura2;
    private String cuatroDigitos;
    private String voucher;
    private String transacion;
    private Franquicias franquicia;
    private UsuariosApp recepcionista;
    private Hospedajes hospedaje;

    Pagos() {

    }
    //cuentas.nombre,                                 tipopago.nombre

    public Pagos(String cuenta, Date fecha, String observaciones, String tipo_pago, int id_recibo_caja, float valor, String cuatroDigitos) {
        this.cuenta = getCuenta();
        this.cuenta.setNombre(cuenta);
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.pago = getPago();
        this.pago.setNombre(tipo_pago);
        this.reciboCaja = getReciboCaja();
        this.reciboCaja.setIdReciboCaja(id_recibo_caja);
        this.valor = valor;
        this.cuatroDigitos = cuatroDigitos;

    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Clientes getCliente() {
        if (cliente == null) {
            cliente = new Clientes();
        }
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Cuentas getCuenta() {
        if (cuenta == null) {
            cuenta = new Cuentas();
        }
        return cuenta;
    }

    public void setCuenta(Cuentas cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNotaEgreso() {
        return notaEgreso;
    }

    public void setNotaEgreso(String notaEgreso) {
        this.notaEgreso = notaEgreso;
    }

    public TiposDePagos getPago() {
        if (pago == null) {
            pago = new TiposDePagos();
        }
        return pago;
    }

    public void setPago(TiposDePagos pago) {
        this.pago = pago;
    }

    public ComprobantesEgreso getComprobante() {
        if (comprobante == null) {
            comprobante = new ComprobantesEgreso();
        }
        return comprobante;
    }

    public void setComprobante(ComprobantesEgreso comprobante) {
        this.comprobante = comprobante;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public Pagos(int idPago, int idCliente, int idCuenta, Date fecha, String observaciones, String notaEgreso, int idTipoPago,
            int idComprobanteEgreso, int idReciboCaja, float valor, int idFacturaProveedor, String idFactura, String cuatroDigitos, String voucher, int idFranquicia, String transacion, String recepcionista, int idHospedaje) {
        this.idPago = idPago;
        this.cliente = getCliente();
        this.cliente.setIdCliente(idCliente);
        this.cuenta = getCuenta();
        this.cuenta.setIdCuenta(idCuenta);
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.notaEgreso = notaEgreso;
        this.pago = getPago();
        this.pago.setIdTipoPago(idTipoPago);
        this.comprobante = getComprobante();
        this.comprobante.setIdComprobanteEgreso(idComprobanteEgreso);
        this.reciboCaja = getReciboCaja();
        this.reciboCaja.setIdReciboCaja(idReciboCaja);
        this.valor = valor;
        this.factura = getFactura();
        this.factura.setIdFacturaProveedor(idFacturaProveedor);
        this.factura2 = getFactura2();
        this.factura2.setIdFactura(idFactura);
        this.cuatroDigitos = cuatroDigitos;
        this.voucher = voucher;
        this.transacion = transacion;
        this.franquicia = getFranquicia();
        this.franquicia.setIdFranquicia(idFranquicia);
        this.recepcionista = getRecepcionista();
        this.recepcionista.setIdUsuario(recepcionista);
        this.hospedaje = getHospedaje();
        this.hospedaje.setIdHospedaje(idHospedaje);
    }

    public Facturas getFactura2() {
        if (factura2 == null) {
            factura2 = new Facturas();
        }
        return factura2;
    }

    public void setFactura2(Facturas factura2) {
        this.factura2 = factura2;
    }

    public RecibosCaja getReciboCaja() {
        if (reciboCaja == null) {
            reciboCaja = new RecibosCaja();
        }
        return reciboCaja;
    }

    public void setReciboCaja(RecibosCaja reciboCaja) {
        this.reciboCaja = reciboCaja;
    }

    public String getCuatroDigitos() {
        return cuatroDigitos;
    }

    public void setCuatroDigitos(String cuatroDigitos) {
        this.cuatroDigitos = cuatroDigitos;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public Franquicias getFranquicia() {
        if (franquicia == null) {
            franquicia = new Franquicias();
        }
        return franquicia;
    }

    public void setFranquicia(Franquicias franquicia) {
        this.franquicia = franquicia;
    }

    public UsuariosApp getRecepcionista() {
        if (recepcionista == null) {
            recepcionista = new UsuariosApp();
        }
        return recepcionista;
    }

    public void setRecepcionista(UsuariosApp recepcionista) {
        this.recepcionista = recepcionista;
    }

    public Hospedajes getHospedaje() {
        if (hospedaje == null) {
            hospedaje = new Hospedajes();
        }
        return hospedaje;
    }

    public void setHospedaje(Hospedajes hospedaje) {
        this.hospedaje = hospedaje;
    }

}
