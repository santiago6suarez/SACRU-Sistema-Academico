package Clases;

import conexion.conexion;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *

 import java.util.Date;

 /**
 *
 * @author Santiago Suárez
 */
public class Login {

    private String usuario;
    private String contraseña;
    private Persona objpersona;
    private int año_creacion;
    private int periodo_creacion;
    private Date fecha_creacion;
    private int codigo;

    public Login(String usuario, String contraseña, Persona objpersona, int año_creacion, int periodo_creacion, Date fecha_creacion, int codigo) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.objpersona = objpersona;
        this.año_creacion = año_creacion;
        this.periodo_creacion = periodo_creacion;
        this.fecha_creacion = fecha_creacion;
        this.codigo = codigo;
    }

    public Login() {

    }

    public boolean crear_usuario(String usuario, String contraseña, Persona objpersona, int año_creacion, int periodo_creacion, Date fecha_creacion, int codigo) {
        return true;
    }

    public boolean modificar_usuario(int codigo) {
        return true;
    }

    public boolean eliminar_usuario(int codigo) {
        return true;
    }

    public Estudiante obtener_info_estudiante(String usuario, conexion objconexion) {

        //OBJETO FACULTAD
        Facultad objfacultad = new Facultad();
        //OBJETO PROGRAMA que contiene n objeto facultad
        Programa objprograma = new Programa();
        //OBJETO ESTUDIANTE que contiene un objeto programa
        Estudiante objestudiante = new Estudiante();
        try {

            String consulta = "SELECT * FROM login JOIN persona ON login.numero_documento = persona.numero_documento JOIN estudiante ON persona.numero_documento = estudiante.numero_documento WHERE usuario ='" + usuario + "'";
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);

            while (resultado.next()) {
                //ATRIBUTOS DE PERSONA Y ESTUDIANTE
                String nombre_1 = resultado.getString("nombre_1");
                String nombre_2 = resultado.getString("nombre_2");
                String apellido_1 = resultado.getString("apellido_1");
                String apellido_2 = resultado.getString("apellido_2");
                String numero_documento = resultado.getString("numero_documento");
                String tipo_documento = resultado.getString("tipo_documento");
                String edad = resultado.getString("edad");
                String sexo = resultado.getString("sexo");
                String correo_personal = resultado.getString("correo_personal");
                String correo_institucional_estudiante = resultado.getString("correo_institucional");
                String codigo_estudiante = resultado.getString("codigo");
                String estado_estudiante = resultado.getString("estado");
                String periodo_ingreso = resultado.getString("periodo_ingreso");

                String año_ingreso = resultado.getString("ano_ingreso");
                String codigo_programa = resultado.getString("codigo_programa");

                //INT ESTUDIANTE
                int edad1 = Integer.parseInt(edad);
                int codigo_estudiante1 = Integer.parseInt(codigo_estudiante);
                int año_ingreso1 = Integer.parseInt(año_ingreso);
                int periodo_ingreso1 = Integer.parseInt(periodo_ingreso);
                //INT PROGRAMA
                int codigo_programa1 = Integer.parseInt(codigo_programa);

                //SETTEAR ESTUDIANTE
                objestudiante.setNombre_1(nombre_1);
                objestudiante.setNombre_2(nombre_2);
                objestudiante.setApellido_1(apellido_1);
                objestudiante.setApellido_2(apellido_2);
                objestudiante.setNumero_documento(numero_documento);
                objestudiante.setTipo_documento(tipo_documento);
                objestudiante.setEdad(edad1);
                objestudiante.setSexo(sexo);
                objestudiante.setCorreo_personal(correo_personal);
                objestudiante.setCorreo_institucional(correo_institucional_estudiante);
                objestudiante.setCodigo(codigo_estudiante1);
                objestudiante.setEstado(estado_estudiante);
                objestudiante.setPeriodo_ingreso(periodo_ingreso1);

                objestudiante.setAño_ingreso(año_ingreso1);

                //SETTEAR PROGRAMA
                objprograma.setCodigo(codigo_programa1);
            }
            st.close();
            resultado.close();

        } catch (Exception e) {

        }

        try {
            String consulta2 = "SELECT * from programa where codigo='" + objprograma.getCodigo() + "'";
            java.sql.Statement st2 = objconexion.getConexion().createStatement();
            ResultSet resultado2 = st2.executeQuery(consulta2);
            while (resultado2.next()) {

                String nombre_programa = resultado2.getString("nombre");
                String codigo_facultad = resultado2.getString("codigo_facultad");
                // settear facultad
                objprograma.setNombre(nombre_programa);

                // int facultad;
                int codigo_facultad1 = Integer.parseInt(codigo_facultad);
                // settear facultad
                objfacultad.setCodigo(codigo_facultad1);
            }

            st2.close();
            resultado2.close();
        } catch (Exception e) {
        }

        try {
            String consulta3 = "SELECT * from facultad where codigo='" + objfacultad.getCodigo() + "'";
            java.sql.Statement st3 = objconexion.getConexion().createStatement();
            ResultSet resultado3 = st3.executeQuery(consulta3);
            while (resultado3.next()) {
                String nombre_facultad = resultado3.getString("nombre");
                objfacultad.setNombre(nombre_facultad);
            }

            st3.close();
            resultado3.close();
        } catch (Exception e) {
        }
        objprograma.setObjfacultad(objfacultad);
        objestudiante.setObjprograma(objprograma);
        return objestudiante;
    }

    //CREA EL OBJETO DE TIPO ADMINISTRADOR
    public Administrador obtener_info_administrador(String usuario, conexion objconexion) {
        Administrador objadmin = new Administrador();
        String consulta = "SELECT * from login WHERE usuario='" + usuario + "'";

        try {

            //CONSULTA SEGUN NUMERO DE DOCUMENTO
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);

            while (resultado.next()) {
                //NUMERO DOCUMENTO DESDE LOGIN
                String numero_documento = resultado.getString("numero_documento");
                //SETTEAR EN EL OBJETO
                objadmin.setNumero_documento(numero_documento);
            }
            String consulta1 = "SELECT * from persona WHERE numero_documento='" + objadmin.getNumero_documento() + "'";
            java.sql.Statement st2 = objconexion.getConexion().createStatement();
            ResultSet resultado2 = st2.executeQuery(consulta1);
            while (resultado2.next()) {

                //DATOS DE PERSONA
                String nombre_1 = resultado2.getString("nombre_1");
                String nombre_2 = resultado2.getString("nombre_2");
                String apellido_1 = resultado2.getString("apellido_1");
                String apellido_2 = resultado2.getString("apellido_2");
                String tipo_documento = resultado2.getString("tipo_documento");
                String edad = resultado2.getString("edad");
                String sexo = resultado2.getString("sexo");
                String correo_personal = resultado2.getString("correo_personal");
                String correo_institucional = resultado2.getString("correo_institucional");

                //CONVERTIR A INT
                int edad1 = Integer.parseInt(edad);

                //SETEAR EN EL OBJETO
                objadmin.setNombre_1(nombre_1);
                objadmin.setNombre_2(nombre_2);
                objadmin.setApellido_1(apellido_1);
                objadmin.setApellido_2(apellido_2);
                objadmin.setTipo_documento(tipo_documento);
                objadmin.setEdad(edad1);
                objadmin.setSexo(sexo);
                objadmin.setCorreo_personal(correo_personal);
                objadmin.setCorreo_institucional(correo_institucional);

            }

            String consulta2 = "SELECT * from admin WHERE numero_documento='" + objadmin.getNumero_documento() + "'";
            java.sql.Statement st3 = objconexion.getConexion().createStatement();
            ResultSet resultado3 = st3.executeQuery(consulta2);

            while (resultado3.next()) {
                //DATOS ADMIN
                String codigo = resultado3.getString("codigo");
                String cargo = resultado3.getString("cargo");
                String numero_celular = resultado3.getString("numero_celular");

                String periodo_ingreso = resultado3.getString("periodo_ingreso");

                //CONVERTIR A INT
                long numero_celular1 = Long.parseLong(numero_celular);

                int periodo_ingreso1 = Integer.parseInt(periodo_ingreso);
                int codigo1 = Integer.parseInt(codigo);

                //SETTEAR OBJETOADMIN
                objadmin.setCodigo(codigo1);
                objadmin.setCargo(cargo);
                objadmin.setNumero_celular(numero_celular1);

                objadmin.setPeriodo_ingreso(periodo_ingreso1);

            }
            st3.close();
            resultado3.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(objadmin.getNumero_celular());
        System.out.println(objadmin.getCargo());
        return objadmin;
    }

    public boolean validar_usuario(String usuario, String contraseña, conexion objconexion) {
        boolean bandera = false;
        String contraseña_encriptada = DigestUtils.md5Hex(contraseña);
        System.out.println(contraseña_encriptada);

        String consulta = "SELECT exists (SELECT usuario FROM login WHERE password='" + contraseña_encriptada + "' AND usuario='" + usuario + "')";

        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);

            while (resultado.next()) {
                if (resultado.getString("exists").equals("t")) {
                    System.out.println("EXISTE EL USUARIO");
                    bandera = true;
                    break;
                }
            }

            resultado.close();
            st.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error " + e.getMessage());
            return bandera;
        }
        return bandera;
    }

    public boolean tipo_usuario(String usuario, conexion objconexion) {
        boolean bandera = false;
        String verificar = "SELECT exists(SELECT estudiante.codigo FROM estudiante JOIN persona ON estudiante.numero_documento=persona.numero_documento JOIN login ON persona.numero_documento=login.numero_documento WHERE login.usuario='" + usuario + "')";

        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(verificar);

            while (resultado.next()) {
                if (resultado.getString("exists").equals("t")) {
                    bandera = true;
                    break;
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error " + e.getMessage());
            return bandera;
        }
        return bandera;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Persona getObjpersona() {
        return objpersona;
    }

    public void setObjpersona(Persona objpersona) {
        this.objpersona = objpersona;
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

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
