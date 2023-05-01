package Clases;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;

/**
 *
 * @author Santiago Suárez
 */
public class Administrador extends Persona {

    private String cargo;
    private long numero_celular;
    private int año_ingreso;
    private int periodo_ingreso;
    private Date fecha_ingreso;
    private int codigo;

    public Administrador(String cargo, int numero_celular, int año_ingreso, int periodo_ingreso, Date fecha_ingreso, int codigo, String nombre_1, String nombre_2, String apellido_1, String apellido_2, String tipo_documento, String numero_documento, int edad, String sexo, String correo_personal, String correo_institucional) {
        super(nombre_1, nombre_2, apellido_1, apellido_2, tipo_documento, numero_documento, edad, sexo, correo_personal, correo_institucional);
        this.cargo = cargo;
        this.numero_celular = numero_celular;
        this.año_ingreso = año_ingreso;
        this.periodo_ingreso = periodo_ingreso;
        this.fecha_ingreso = fecha_ingreso;
        this.codigo = codigo;
    }

    public Administrador() {
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public long getNumero_celular() {
        return numero_celular;
    }
    

    public void setNumero_celular(long numero_celular) {
        this.numero_celular = numero_celular;
    }

    public int getAño_ingreso() {
        return año_ingreso;
    }

    public void setAño_ingreso(int año_ingreso) {
        this.año_ingreso = año_ingreso;
    }

    public int getPeriodo_ingreso() {
        return periodo_ingreso;
    }

    public void setPeriodo_ingreso(int periodo_ingreso) {
        this.periodo_ingreso = periodo_ingreso;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre_1() {
        return nombre_1;
    }

    public void setNombre_1(String nombre_1) {
        this.nombre_1 = nombre_1;
    }

    public String getNombre_2() {
        return nombre_2;
    }

    public void setNombre_2(String nombre_2) {
        this.nombre_2 = nombre_2;
    }

    public String getApellido_1() {
        return apellido_1;
    }

    public void setApellido_1(String apellido_1) {
        this.apellido_1 = apellido_1;
    }

    public String getApellido_2() {
        return apellido_2;
    }

    public void setApellido_2(String apellido_2) {
        this.apellido_2 = apellido_2;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo_personal() {
        return correo_personal;
    }

    public void setCorreo_personal(String correo_personal) {
        this.correo_personal = correo_personal;
    }

    public String getCorreo_institucional() {
        return correo_institucional;
    }

    public void setCorreo_institucional(String correo_institucional) {
        this.correo_institucional = correo_institucional;
    }

}
