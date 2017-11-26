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
public class Habitaciones {
    
    private int idHabitacion;
    private String nombre;
    private int camaDoble;
    private int camaSencilla;
    private int totalCamas;
    private String transacion;
    private TiposDeHabitacion tipoCama;
    private boolean disponible;

    Habitaciones() {
        
    }

    public Habitaciones(int idHabitacion, String nombre, int camaDoble, int camaSencilla, String tipoHabitacion) {
        this.idHabitacion = idHabitacion;
        this.nombre = nombre;
        this.camaDoble = camaDoble;
        this.camaSencilla = camaSencilla;             
        this.tipoCama = getTipoCama();
        this.tipoCama.setNombre(tipoHabitacion);        
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCamaDoble() {
        return camaDoble;
    }

    public void setCamaDoble(int camaDoble) {
        this.camaDoble = camaDoble;
    }

    public int getCamaSencilla() {
        return camaSencilla;
    }

    public void setCamaSencilla(int camaSencilla) {
        this.camaSencilla = camaSencilla;
    }

    public int getTotalCamas() {
        return totalCamas;
    }

    public void setTotalCamas(int totalCamas) {
        this.totalCamas = totalCamas;
    }

    public String getTransacion() {
        return transacion;
    }

    public void setTransacion(String transacion) {
        this.transacion = transacion;
    }

    public TiposDeHabitacion getTipoCama() {
        if (tipoCama == null) {
            tipoCama = new TiposDeHabitacion();
        }
        return tipoCama;
    }

    public void setTipoCama(TiposDeHabitacion tipoCama) {
        this.tipoCama = tipoCama;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }    

    public Habitaciones(int idHabitacion, String nombre, int camaDoble, int camaSencilla, int totalCamas, String transacion, int idTipoHabitacion, boolean disponible) {
        this.idHabitacion = idHabitacion;
        this.nombre = nombre;
        this.camaDoble = camaDoble;
        this.camaSencilla = camaSencilla;
        this.totalCamas = totalCamas;
        this.transacion = transacion;
        this.tipoCama = getTipoCama();
        this.tipoCama.setIdTipoHabitacion(idTipoHabitacion);
        this.disponible = disponible;
    }
    
    
}
