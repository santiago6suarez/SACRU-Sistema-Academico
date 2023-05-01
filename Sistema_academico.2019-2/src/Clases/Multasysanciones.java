package Clases;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Santiago Suárez
 */
public class Multasysanciones {

    private String nombre;
    private int codigo;
    private String estado;
    private float valor;
    private String descripcion;
    private String dependencia;
    private Date fecha_creacion;
    private int año_creacion;
    private Time hora_creacion;
    private int periodo_creacion;
    private Estudiante objestudiante;

    public Multasysanciones(String nombre, int codigo, String estado, float valor, String descripcion, String dependencia, Date fecha_creacion, int año_creacion, Time hora_creacion, int periodo_creacion, Estudiante objestudiante) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.estado = estado;
        this.valor = valor;
        this.descripcion = descripcion;
        this.dependencia = dependencia;
        this.fecha_creacion = fecha_creacion;
        this.año_creacion = año_creacion;
        this.hora_creacion = hora_creacion;
        this.periodo_creacion = periodo_creacion;
        this.objestudiante = objestudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
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

    public Time getHora_creacion() {
        return hora_creacion;
    }

    public void setHora_creacion(Time hora_creacion) {
        this.hora_creacion = hora_creacion;
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
