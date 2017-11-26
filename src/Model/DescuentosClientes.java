/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Innova
 */
public class DescuentosClientes {

    private int idDescuentosClientes;
    private Clientes cliente;
    private Descuentos descuento;
    private String transacion;

    public Clientes getCliente() {
        if (cliente == null) {
            cliente = new Clientes();
        }
        return cliente;
    }

    public void setComprador(Clientes cliente) {
        this.cliente = cliente;
    }

    public Descuentos getDescuento() {
        if (descuento == null) {
            descuento = new Descuentos();
        }
        return descuento;
    }

    public void setDescuento(Descuentos descuento) {
        this.descuento = descuento;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public DescuentosClientes(int idDescuentosClientes, int idCliente, int idDescuento, String transacion) {
        this.idDescuentosClientes = idDescuentosClientes;
        this.cliente = getCliente();
        this.cliente.setIdCliente(idCliente);
        this.descuento = getDescuento();
        this.descuento.setIdDescuento(idDescuento);
        this.transacion = transacion;
    }

    public int getIdDescuentosClientes() {
        return idDescuentosClientes;
    }

    public void setIdDescuentosClientes(int idDescuentosClientes) {
        this.idDescuentosClientes = idDescuentosClientes;
    }

}
