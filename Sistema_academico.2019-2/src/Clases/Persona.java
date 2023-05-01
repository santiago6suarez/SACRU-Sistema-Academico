/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import conexion.conexion;
import java.sql.ResultSet;
import javax.swing.JOptionPane;



/**
 *
 * @author Santiago Suárez
 */
 public class Persona {

    protected String nombre_1;
    protected String nombre_2;
    protected String apellido_1;
    protected String apellido_2;
    protected String tipo_documento;
    protected String numero_documento;
    protected int edad;
    protected String sexo;
    protected String correo_personal;
    protected String correo_institucional;

    public Persona(String nombre_1, String nombre_2, String apellido_1, String apellido_2, String tipo_documento, String numero_documento, int edad, String sexo, String correo_personal,String correo_institucional) {
        this.nombre_1 = nombre_1;
        this.nombre_2 = nombre_2;
        this.apellido_1 = apellido_1;
        this.apellido_2 = apellido_2;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
        this.edad = edad;
        this.sexo = sexo;
        this.correo_personal = correo_personal;
        this.correo_institucional=correo_institucional;
    }

    public Persona() {
    }

    public Persona obtener_informacionpersona() {
        Persona objpersona = new Persona();
        return objpersona;
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

    public String getCorreo_institucional() {
        return correo_institucional;
    }

    public void setCorreo_institucional(String correo_institucional) {
        this.correo_institucional = correo_institucional;
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
    
   

}
