package Clases;

import conexion.conexion;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *

import java.util.Date;
import javax.swing.text.Document;

/**
 *
 * @author Santiago Suárez
 */
public class Estudiante extends Persona {

    private int codigo;
    private String estado;
    private Programa objprograma;
    private int año_ingreso;
    private int periodo_ingreso;
    private Date fecha_ingreso;
    private Registro_semestre objregistrosemestre;

    public Estudiante(int codigo, String estado, Programa objprograma, int año_ingreso, int periodo_ingreso, Date fecha_ingreso, Registro_semestre objregistrosemestre, String nombre_1, String nombre_2, String apellido_1, String apellido_2, String tipo_documento, String numero_documento, int edad, String sexo, String correo_personal, String correo_institucional) {
        super(nombre_1, nombre_2, apellido_1, apellido_2, tipo_documento, numero_documento, edad, sexo, correo_personal, correo_institucional);
        this.codigo = codigo;
        this.estado = estado;
        this.objprograma = objprograma;
        this.año_ingreso = año_ingreso;
        this.periodo_ingreso = periodo_ingreso;
        this.fecha_ingreso = fecha_ingreso;
        this.objregistrosemestre = objregistrosemestre;
    }

    

   
    public Estudiante() {

    }
    
    public void caragar_estudiantes(){
    
    
    }

    public boolean eliminar_estudiante(int codigo) {
        return true;
    }

    public boolean modificar_estudiante(int codigo) {
        return true;
    }
    
    public void listar_por_identificacion(JTable tabla, int numero_identificacion, conexion objconexion) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        //ArrayList<String >  array= new ArrayList<String >();
        //array.add("");
        try {
           
            
                
                String consulta2 = "select persona.nombre_1,persona.nombre_2,persona.apellido_1,persona.apellido_2,estudiante.codigo,persona.tipo_documento, persona.numero_documento,programa.nombre as nombre_programa,facultad.nombre as nombre_facultad,estudiante.estado,persona.correo_personal,persona.correo_institucional  from persona join estudiante on persona.numero_documento=estudiante.numero_documento join programa on programa.codigo=estudiante.codigo_programa join facultad on facultad.codigo=programa.codigo_facultad where persona.numero_documento=" +numero_identificacion;
                java.sql.Statement st1 = objconexion.getConexion().createStatement();
                ResultSet resultado2 = st1.executeQuery(consulta2);

                while (resultado2.next()) {
                    String nombre = resultado2.getString("nombre_1") + " " + resultado2.getString("nombre_2") + " " + resultado2.getString("apellido_1") + " " + resultado2.getString("apellido_2");
                    String codigo1 = resultado2.getString("codigo");
                    String tipo_documento = resultado2.getString("tipo_documento");
                    String numero_documento = resultado2.getString("numero_documento");
                    String nombre_programa = resultado2.getString("nombre_programa");
                    String nombre_facultad=resultado2.getString("nombre_facultad");
                    String estado=resultado2.getString("estado");
                    String correo_personal=resultado2.getString("correo_personal");
                    String correo_institucional=resultado2.getString("correo_institucional");
                    
                    String datos[] = {nombre,codigo1,tipo_documento,numero_documento,nombre_programa,nombre_facultad,estado,correo_personal,correo_institucional};
                    modelo.addRow(datos);
             
                        
                }

            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }
    
    
     public void listar_por_codigo(JTable tabla, int codigo, conexion objconexion) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        //ArrayList<String >  array= new ArrayList<String >();
        //array.add("");
        try {
           
            
                
                String consulta2 = "select persona.nombre_1,persona.nombre_2,persona.apellido_1,persona.apellido_2,estudiante.codigo,persona.tipo_documento, persona.numero_documento,programa.nombre as nombre_programa,facultad.nombre as nombre_facultad,estudiante.estado,persona.correo_personal,persona.correo_institucional  from persona join estudiante on persona.numero_documento=estudiante.numero_documento join programa on programa.codigo=estudiante.codigo_programa join facultad on facultad.codigo=programa.codigo_facultad where estudiante.codigo=" + codigo;
                java.sql.Statement st1 = objconexion.getConexion().createStatement();
                ResultSet resultado2 = st1.executeQuery(consulta2);

                while (resultado2.next()) {
                    String nombre = resultado2.getString("nombre_1") + " " + resultado2.getString("nombre_2") + " " + resultado2.getString("apellido_1") + " " + resultado2.getString("apellido_2");
                    String codigo1 = resultado2.getString("codigo");
                    String tipo_documento = resultado2.getString("tipo_documento");
                    String numero_documento = resultado2.getString("numero_documento");
                    String nombre_programa = resultado2.getString("nombre_programa");
                    String nombre_facultad=resultado2.getString("nombre_facultad");
                    String estado=resultado2.getString("estado");
                    String correo_personal=resultado2.getString("correo_personal");
                    String correo_institucional=resultado2.getString("correo_institucional");
                    
                    String datos[] = {nombre,codigo1,tipo_documento,numero_documento,nombre_programa,nombre_facultad,estado,correo_personal,correo_institucional};
                    modelo.addRow(datos);
             
                        
                }

            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }

    public void cargar_estudiantes(String nombre_1, String nombre_2, String apellido_1, String apellido_2, String tipo_documento, String numero_documento, int edad, String sexo, String correo_personal, String correo_institucional, int codigo, String estado, String nombre_programa, int año_ingreso, int periodo_ingreso, String fecha_ingreso, conexion objconexion) {
        boolean verificar_insercion = false;

        String consulta_codigo_programa = " select codigo from programa where nombre='" + nombre_programa + "'";
        String insercion_persona = "insert into persona values ('" + numero_documento + "','" + nombre_1 + "','" + nombre_2 + "','" + apellido_1 + "','" + apellido_2 + "','" + tipo_documento + "'," + edad + ",'" + sexo + "','" + correo_personal + "','" + correo_institucional + "')";
        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            java.sql.Statement st2 = objconexion.getConexion().createStatement();
            java.sql.Statement st3 = objconexion.getConexion().createStatement();

            ResultSet resultado_codigos = st.executeQuery(consulta_codigo_programa);
            st2.execute(insercion_persona);
            while (resultado_codigos.next()) {
                String insercion_estudiante = "insert into estudiante values (" + codigo + ",'" + estado + "'," + periodo_ingreso + ",'" + fecha_ingreso + "'," + año_ingreso + ",'" + numero_documento + "'," + Integer.parseInt(resultado_codigos.getString("codigo")) + ")";

                verificar_insercion = st3.execute(insercion_estudiante);
            }

            resultado_codigos.close();
            st.close();
            st2.close();
            st3.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error " + e.getMessage());

        } finally {
            if (!verificar_insercion) {
                JOptionPane.showMessageDialog(null, " estudiante insertado");
            }

        }

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

    public Programa getObjprograma() {
        return objprograma;
    }

    public void setObjprograma(Programa objprograma) {
        this.objprograma = objprograma;
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

    public String validar_correo_institucional(String nombre_1, String apellido_1, String apellido_2, conexion objconexion) {
        String dominio = "@unillanos.edu.co";
        String correo = nombre_1 + "." + apellido_1 + dominio;

        String consulta = "SELECT correo_institucional FROM persona ";
        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);

            if (resultado != null) {
                while (resultado.next()) {
                    if (correo.equals(resultado.getString("correo_institucional"))) {
                        correo = nombre_1 + "." + apellido_1 + "." + apellido_2 + dominio;
                        break;
                    }
                }
                resultado.close();
            }

            st.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error " + e.getMessage());
            return correo;
        }
        return correo;

    }

}
