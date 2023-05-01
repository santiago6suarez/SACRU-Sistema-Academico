/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author ADMIN
 */
public class Registro_semestre {
    
    private int codigo;
    private int semestre;
    private int periodo;
    private int año;
    private int cantidad_materias_perdidas;
    private float porcentaje_materias_perdidas;
    private float promedio_semestre;

    public Registro_semestre(int codigo, int semestre, int periodo, int año, int cantidad_materias_perdidas, float porcentaje_materias_perdidas, float promedio_semestre) {
        this.codigo = codigo;
        this.semestre = semestre;
        this.periodo = periodo;
        this.año = año;
        this.cantidad_materias_perdidas = cantidad_materias_perdidas;
        this.porcentaje_materias_perdidas = porcentaje_materias_perdidas;
        this.promedio_semestre = promedio_semestre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getCantidad_materias_perdidas() {
        return cantidad_materias_perdidas;
    }

    public void setCantidad_materias_perdidas(int cantidad_materias_perdidas) {
        this.cantidad_materias_perdidas = cantidad_materias_perdidas;
    }

    public float getPorcentaje_materias_perdidas() {
        return porcentaje_materias_perdidas;
    }

    public void setPorcentaje_materias_perdidas(float porcentaje_materias_perdidas) {
        this.porcentaje_materias_perdidas = porcentaje_materias_perdidas;
    }

    public float getPromedio_semestre() {
        return promedio_semestre;
    }

    public void setPromedio_semestre(float promedio_semestre) {
        this.promedio_semestre = promedio_semestre;
    }
    
    
    
    
}
