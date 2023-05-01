package Clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import conexion.conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Santiago Suárez
 */
public class Facultad {

    private String nombre;
    private int codigo;
    private String correo_institucional;
    private Date fecha_creacion;
    private String estado;
    private String sede;
    private int año_creacion;
    private int periodo_creacion;

    public Facultad(String nombre, int codigo, String correo_institucional, Date fecha_creacion, String estado, String sede, int año_creacion, int periodo_creacion) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.correo_institucional = correo_institucional;
        this.fecha_creacion = fecha_creacion;
        this.estado = estado;
        this.sede = sede;
        this.año_creacion = año_creacion;
        this.periodo_creacion = periodo_creacion;
    }

    public Facultad() {

    }
    
    public void listar_por_facultad(JTable tabla, String facultad, conexion objconexion) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        //ArrayList<String >  array= new ArrayList<String >();
        //array.add("");
        String consulta_codigo_facultad = " select codigo from facultad where nombre='" + facultad+ "'";

        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta_codigo_facultad);
            while (resultado.next()) {
                int codigo_facultad = Integer.parseInt(resultado.getString("codigo"));
                String consulta2 = "select persona.nombre_1,persona.nombre_2,persona.apellido_1,persona.apellido_2,estudiante.codigo,persona.tipo_documento, persona.numero_documento,programa.nombre as nombre_programa,facultad.nombre as nombre_facultad,estudiante.estado,persona.correo_personal,persona.correo_institucional  from persona join estudiante on persona.numero_documento=estudiante.numero_documento join programa on programa.codigo=estudiante.codigo_programa join facultad on facultad.codigo=programa.codigo_facultad where facultad.codigo=" + codigo_facultad;
                java.sql.Statement st1 = objconexion.getConexion().createStatement();
                ResultSet resultado2 = st1.executeQuery(consulta2);

                while (resultado2.next()) {
                    String nombre = resultado2.getString("nombre_1") + " " + resultado2.getString("nombre_2") + " " + resultado2.getString("apellido_1") + " " + resultado2.getString("apellido_2");
                    String codigo = resultado2.getString("codigo");
                    String tipo_documento = resultado2.getString("tipo_documento");
                    String numero_documento = resultado2.getString("numero_documento");
                    String nombre_programa = resultado2.getString("nombre_programa");
                    String nombre_facultad=resultado2.getString("nombre_facultad");
                    String estado=resultado2.getString("estado");
                    String correo_personal=resultado2.getString("correo_personal");
                    String correo_institucional=resultado2.getString("correo_institucional");
                    
                    String datos[] = {nombre,codigo,tipo_documento,numero_documento,nombre_programa,nombre_facultad,estado,correo_personal,correo_institucional};
                    modelo.addRow(datos);
             
                        
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }
    
    public void listar_facultad(JComboBox combo,conexion objconexion){
     String consulta = "SELECT nombre  FROM facultad ORDER BY nombre ASC ";
        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);

            while (resultado.next()) {
                combo.addItem(resultado.getString("nombre"));
            }

            resultado.close();
            st.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error " + e.getMessage());
        }
    
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

    public String getCorreo_institucional() {
        return correo_institucional;
    }

    public void setCorreo_institucional(String correo_institucional) {
        this.correo_institucional = correo_institucional;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
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

    void setCodigo(String codigo_facultad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
