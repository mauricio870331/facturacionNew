/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Adalberto Gomez
 */
public class Hospedajes {

    private int idHospedaje;
    private MotivosEstadia motivo;
    private Date fechaIngreso;
    private Date fechaSalida;
    private Habitaciones habitacion;
    private Clientes cliente;
    private int numeroAdultos;
    private int numeroNinos;
    private Date fechaRegistro;
    private Facturas factura;
    private UsuariosApp recepcionista;
    private float valorNoche;
    private String transacion;
    private int totalPesonas;
    private EstadosFacturas estado;

    public Hospedajes(int idHospedaje, Date fechaIngreso, Date fechaSalida, int totalPersonas, String nombreHabitacion, int idCliente, String nombreCliente, 
            int idHabitacion, String estado, String idFactura, int numeroAdultos, int numeroNinos) {
        this.idHospedaje = idHospedaje;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.totalPesonas = totalPersonas;
        this.habitacion = getHabitacion();
        this.habitacion.setNombre(nombreHabitacion);
        this.cliente = getCliente();
        this.cliente.setNombreRazon(nombreCliente);
        this.cliente.setIdCliente(idCliente);
        this.habitacion = getHabitacion();
        this.habitacion.setIdHabitacion(idHabitacion);
        this.estado = getEstado();
        this.estado.setNombre(estado);
        this.factura = getFactura();
        this.factura.setIdFactura(idFactura);
        this.numeroAdultos = numeroAdultos;
        this.numeroNinos = numeroNinos;
    }

    Hospedajes() {
    }

    public int getIdHospedaje() {
        return idHospedaje;
    }

    public void setIdHospedaje(int idHospedaje) {
        this.idHospedaje = idHospedaje;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Habitaciones getHabitacion() {
        if (habitacion == null) {
            habitacion = new Habitaciones();
        }
        return habitacion;
    }

    public void setHabitacion(Habitaciones habitacion) {
        this.habitacion = habitacion;
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

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public Hospedajes(int idHospedaje, int idMotivo, Date fechaIngreso, Date fechaSalida, int idHabitacion, int idCliente, int numeroAdultos,
            int numeroNinos, Date fecha_registro, String idFactura, String recepcionista, float ValorNoche, String transacion) {
        this.idHospedaje = idHospedaje;
        this.motivo = getMotivo();
        this.motivo.setIdMotivo(idMotivo);
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.habitacion = getHabitacion();
        this.habitacion.setIdHabitacion(idHabitacion);
        this.cliente = getCliente();
        this.cliente.setIdCliente(idCliente);
        this.numeroAdultos = numeroAdultos;
        this.numeroNinos = numeroNinos;
        this.fechaRegistro = fecha_registro;
        this.factura = getFactura();
        this.factura.setIdFactura(idFactura);
        this.recepcionista = getRecepcionista();
        this.recepcionista.setIdUsuario(recepcionista);
        this.valorNoche = ValorNoche;
        this.transacion = transacion;
    }

    public MotivosEstadia getMotivo() {
        if (motivo == null) {
            motivo = new MotivosEstadia();
        }
        return motivo;
    }

    public void setMotivo(MotivosEstadia motivo) {
        this.motivo = motivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public UsuariosApp getRecepcionista() {
        if (recepcionista == null) {
            recepcionista = new UsuariosApp();
        }
        return recepcionista;
    }

    public void setRecepcionista(UsuariosApp recepcionista) {
        this.recepcionista = recepcionista;
    }

    public float getValorNoche() {
        return valorNoche;
    }

    public void setValorNoche(float valorNoche) {
        this.valorNoche = valorNoche;
    }

    public int getNumeroAdultos() {
        return numeroAdultos;
    }

    public void setNumeroAdultos(int numeroAdultos) {
        this.numeroAdultos = numeroAdultos;
    }

    public int getNumeroNinos() {
        return numeroNinos;
    }

    public void setNumeroNinos(int numeroNinos) {
        this.numeroNinos = numeroNinos;
    }

    public int getTotalPesonas() {
        return totalPesonas;
    }

    public void setTotalPesonas(int totalPesonas) {
        this.totalPesonas = totalPesonas;
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

}
