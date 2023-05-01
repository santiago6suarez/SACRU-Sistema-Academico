package Clases;

import java.sql.Time;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Santiago Suárez
 */
public class Bienestar {

    private String nombre_descuento;
    private int codigo;
    private String tipo_descuento;
    private float porcentaje;
    private String estado;
    private Time hora;
    private Date fecha_creacion;
    private int año_creacion;
    private int periodo_creacion;
    private Estudiante objestudiante;

    public Bienestar(String nombre_descuento, int codigo, String tipo_descuento, float porcentaje, String estado, Time hora, Date fecha_creacion, int año_creacion, int periodo_creacion, Estudiante objestudiante) {
        this.nombre_descuento = nombre_descuento;
        this.codigo = codigo;
        this.tipo_descuento = tipo_descuento;
        this.porcentaje = porcentaje;
        this.estado = estado;
        this.hora = hora;
        this.fecha_creacion = fecha_creacion;
        this.año_creacion = año_creacion;
        this.periodo_creacion = periodo_creacion;
        this.objestudiante = objestudiante;
    }

    public String getNombre_descuento() {
        return nombre_descuento;
    }

    public void setNombre_descuento(String nombre_descuento) {
        this.nombre_descuento = nombre_descuento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo_descuento() {
        return tipo_descuento;
    }

    public void setTipo_descuento(String tipo_descuento) {
        this.tipo_descuento = tipo_descuento;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getAño_creacion() {
        return año_creacion;
    }

    public void setAño_creacion(int año_creacion) {
        this.año_creacion = año_creacion;
    }

    public int getPeriodo_creacion() {
        return periodo_creacion;
    }

    public void setPeriodo_creacion(int periodo_creacion) {
        this.periodo_creacion = periodo_creacion;
    }

    public Estudiante getObjestudiante() {
        return objestudiante;
    }

    public void setObjestudiante(Estudiante objestudiante) {
        this.objestudiante = objestudiante;
    }
    
    

    
}
