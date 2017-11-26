/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Mauricio Herrera
 */
public class DetalleFacturaDataSource implements JRDataSource {

    private List<DetalleFacturaCotizacion> listaDetalle = new ArrayList();
    private int indiceDetalle = -1;

    public DetalleFacturaDataSource() {
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceDetalle < listaDetalle.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if (null != jrf.getName()) {
            switch (jrf.getName()) {
                case "item":
                    valor = listaDetalle.get(indiceDetalle).getItem().getIdProductoServicio();
                    break;
                case "cantidad":
                    valor = listaDetalle.get(indiceDetalle).getCantidad();
                    break;
                case "factura":
                    valor = listaDetalle.get(indiceDetalle).getFactura().getIdFactura();
                    break;
                case "impuesto":
                    valor = listaDetalle.get(indiceDetalle).getImpuesto().getImpuesto2();
                    break;
                case "valor":
                    valor = listaDetalle.get(indiceDetalle).getValor();
                    break;
                case "nomprod":
                    valor = listaDetalle.get(indiceDetalle).getNomprod();
                    break;
                case "descuento":
                    valor = listaDetalle.get(indiceDetalle).getDescuento();
                    break;
                case "subtotal":
                    valor = listaDetalle.get(indiceDetalle).getSubtotal();
                    break;
                case "unidad":
                    valor = listaDetalle.get(indiceDetalle).getUnidad_medida();
                    break;
                default:
                    break;
            }
        }
        return valor;
    }

    public List<DetalleFacturaCotizacion> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<DetalleFacturaCotizacion> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

}
