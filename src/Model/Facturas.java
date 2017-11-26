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
public class Facturas {

    private String idFactura;
    private Date fecha;
    private ResolucionesFacturas resolucion;
    private Clientes comprador;
    private float subtotal;
    private float iva;
    private float total;
    private EstadosFacturas estado;
    private Empresas vendedor;
    private UsuariosApp usuario;
    private int validezDias;
    private String plazo;
    private Date vencimiento;
    private String nota;
    private String transacion;
    private int terminosdepago;
    private float descuentoTotal;
    private float abonos;
    private float retenciones;

    public Facturas() {

    }

    public ResolucionesFacturas getResolucion() {
        if (resolucion == null) {
            resolucion = new ResolucionesFacturas();
        }
        return resolucion;
    }

    public void setResolucion(ResolucionesFacturas resolucion) {
        this.resolucion = resolucion;
    }

    public Clientes getComprador() {
        if (comprador == null) {
            comprador = new Clientes();
        }

        return comprador;
    }

    public void setComprador(Clientes comprador) {
        this.comprador = comprador;
    }

    public EstadosFacturas getEstado() {
        if (estado == null) {
            estado = new EstadosFacturas();
        }
        return estado;
    }

    public void setEstado(EstadosFacturas estado) {
        this.estado = estado;
    }

    public Empresas getVendedor() {
        if (vendedor == null) {
            vendedor = new Empresas();
        }
        return vendedor;
    }

    public void setVendedor(Empresas vendedor) {
        this.vendedor = vendedor;
    }

    public UsuariosApp getUsuario() {
        if (usuario == null) {
            usuario = new UsuariosApp();
        }
        return usuario;
    }

    public void setUsuario(UsuariosApp usuario) {
        this.usuario = usuario;
    }

    public int getValidezDias() {
        return validezDias;
    }

    public void setValidezDias(int validezDias) {
        this.validezDias = validezDias;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getTerminosdepago() {
        return terminosdepago;
    }

    public void setTerminosdepago(int terminosdepago) {
        this.terminosdepago = terminosdepago;
    }

    public float getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(float descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public Facturas(String idFactura,
            Date fecha,
            String idResolucion,
            int idCliente,
            float subtotal,
            float iva,
            float total,
            int idEstadoFactura,
            String idEmpresa,
            String idUsuario,
            int validezDias,
            Date vencimiento,
            String nota,
            String transacion,
            float abonos, 
            float retenciones) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.resolucion = getResolucion();
        this.resolucion.setIdResolucion(idResolucion);
        this.comprador = getComprador();
        this.comprador.setIdCliente(idCliente);
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.estado = getEstado();
        this.estado.setIdEstadoFactura(idEstadoFactura);
        this.vendedor = getVendedor();
        this.vendedor.setIdEmpresa(idEmpresa);
        this.usuario = getUsuario();
        this.usuario.setIdUsuario(idUsuario);
        this.validezDias = validezDias;
        this.plazo = plazo;
        this.vencimiento = vencimiento;
        this.nota = nota;
        this.transacion = transacion;
        this.abonos = abonos;
        this.retenciones = retenciones;
    }
    
    
    public Facturas(String idFactura,
            Date fecha,
            String idResolucion,
            int idCliente,
            float subtotal,
            float iva,
            float total,
            int idEstadoFactura,
            String idEmpresa,
            String idUsuario,
            int validezDias,
            Date vencimiento,
            String nota,
            String transacion, String identificacionCliente) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.resolucion = getResolucion();
        this.resolucion.setIdResolucion(idResolucion);
        this.comprador = getComprador();
        this.comprador.setIdCliente(idCliente);
        this.comprador.setIdentificacion(identificacionCliente);
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.estado = getEstado();
        this.estado.setIdEstadoFactura(idEstadoFactura);
        this.vendedor = getVendedor();
        this.vendedor.setIdEmpresa(idEmpresa);
        this.usuario = getUsuario();
        this.usuario.setIdUsuario(idUsuario);
        this.validezDias = validezDias;
        this.plazo = plazo;
        this.vencimiento = vencimiento;
        this.nota = nota;
        this.transacion = transacion;
    }

    @Override
    public String toString() {
        return "Facturas{" + "idFactura=" + idFactura + ", fecha=" + fecha + ", resolucion=" + resolucion + ", comprador=" + comprador + ", subtotal=" + subtotal + ", iva=" + iva + ", total=" + total + ", estado=" + estado + ", vendedor=" + vendedor + ", usuario=" + usuario + ", validezDias=" + validezDias + ", plazo=" + plazo + ", vencimiento=" + vencimiento + ", nota=" + nota + ", transacion=" + transacion + ", terminosdepago=" + terminosdepago + ", descuentoTotal=" + descuentoTotal + '}';
    }

    public float getAbonos() {
        return abonos;
    }

    public void setAbonos(float abonos) {
        this.abonos = abonos;
    }

    public float getRetenciones() {
        return retenciones;
    }

    public void setRetenciones(float retenciones) {
        this.retenciones = retenciones;
    }

    
    
}
