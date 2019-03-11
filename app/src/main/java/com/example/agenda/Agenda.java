package com.example.agenda;

public class Agenda {

    private int id_agenda;
    private String nombre_contacto;
    private String telefono;
    private String cumpleanos;
    private String Nota;

    public Agenda(int id_agenda, String nombre_contacto, String telefono, String cumpleanos, String nota) {
        this.id_agenda = id_agenda;
        this.nombre_contacto = nombre_contacto;
        this.telefono = telefono;
        this.cumpleanos = cumpleanos;
        Nota = nota;
    }

    public int getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(int id_agenda) {
        this.id_agenda = id_agenda;
    }

    public String getNombre_contacto() {
        return nombre_contacto;
    }

    public void setNombre_contacto(String nombre_contacto) {
        this.nombre_contacto = nombre_contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCumpleanos() {
        return cumpleanos;
    }

    public void setCumpleanos(String cumpleanos) {
        this.cumpleanos = cumpleanos;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
        Nota = nota;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "id_agenda=" + id_agenda +
                ", nombre_contacto='" + nombre_contacto + '\'' +
                ", telefono='" + telefono + '\'' +
                ", cumpleanos='" + cumpleanos + '\'' +
                ", Nota='" + Nota + '\'' +
                '}';
    }
}
