package com.softsofware.javiercalette.proyecto;

public class Evento {
    private String evento,fecha,hora,status,contacto;
    private int img;

    public Evento(String evento, String fecha, String hora, String status, String contacto,int img) {
        this.evento = evento;
        this.fecha = fecha;
        this.hora = hora;
        this.status = status;
        this.contacto = contacto;
        this.img=img;
    }

    public Evento() {
    }

    public String getEvento() {
        return evento;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getStatus() {
        return status;
    }

    public String getContacto() {
        return contacto;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
