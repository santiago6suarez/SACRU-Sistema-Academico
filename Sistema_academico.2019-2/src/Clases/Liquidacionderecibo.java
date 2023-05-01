package Clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

este es el proyecto actual**********______________
 */
import conexion.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago Suárez
 */
public class Liquidacionderecibo {

    private Double derechos_matricula;
    private Double servicios_universitarios;
    private Double derechos_academicos;
    private Double servicios_transporte;
    private Double seguro_estudiantil;
    private Double total_pagar;
    private int codigo;
    private String estado;
    private Date fecha_limite;
    private Date fecha_creacion;
    private int año_creacion;
    private int periodo_creacion;
    private Time hora_liquidacion;
    private Estudiante objestudiante;

    public Liquidacionderecibo(Double derechos_matricula, Double servicios_universitarios, Double derechos_academicos, Double servicios_transporte, Double seguro_estudiantil, Double total_pagar, int codigo, String estado, Date fecha_limite, Date fecha_creacion, int año_creacion, int periodo_creacion, Time hora_liquidacion, Estudiante objestudiante) {
        this.derechos_matricula = derechos_matricula;
        this.servicios_universitarios = servicios_universitarios;
        this.derechos_academicos = derechos_academicos;
        this.servicios_transporte = servicios_transporte;
        this.seguro_estudiantil = seguro_estudiantil;
        this.total_pagar = total_pagar;
        this.codigo = codigo;
        this.estado = estado;
        this.fecha_limite = fecha_limite;
        this.fecha_creacion = fecha_creacion;
        this.año_creacion = año_creacion;
        this.periodo_creacion = periodo_creacion;
        this.hora_liquidacion = hora_liquidacion;
        this.objestudiante = objestudiante;
    }

    public Liquidacionderecibo() {
        //To change body of generated methods, choose Tools | Templates.
    }

    public ResultSet listar_estudiantes(conexion objconexion) {
        ResultSet resultado = null;
        try {
            String consulta = "select codigo from estudiante";
            java.sql.Statement st = objconexion.getConexion().createStatement();
            resultado = st.executeQuery(consulta);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return resultado;
        }
        return resultado;
    }

    public void generar_liquidacion(conexion objconexion, int año, int periodo) {

        ResultSet resultado = listar_estudiantes(objconexion);

        //prueba    
        boolean liquidacion = true;
        String estado_liquidacion = "";
        //
        if (periodo == 1) {
            año = año - 1;
            periodo = periodo + 1;

        }
        if (periodo == 2) {
            periodo = periodo - 1;
        }

        try {
            while (resultado.next()) {
                int codigo = Integer.parseInt(resultado.getString("codigo"));

                boolean estado = validar_estados(codigo, objconexion);

                //verificar el estado del estudiante
                if (!estado) {

                    boolean cursos_inscritos = cursos_inscritos(codigo, objconexion);
                    //verificar que hayan cursos pre-inscritos
                    if (cursos_inscritos) {

                        boolean multas = multas_y_sanciones(codigo, objconexion);
                        //verificar que el estudiante no tenga multas
                        if (!multas) {

                            boolean becado = matricula_honor(codigo, objconexion, año, periodo);
                            //verificar si fue el estudiante se becó
                            if (!becado) {

                                boolean validar_porcentaje_y_materias = porcentaje_y_materias(codigo, objconexion, año, periodo);
                                //verificar si su promedio de semestre el mayor a 3.2 y no perdió más del 50% de las materias
                                if (!validar_porcentaje_y_materias) {

                                    //derecho de matrícula según programa
                                    double derecho_matricula = derecho_matricula(codigo, objconexion);
                                    double derecho_matricula_materiasp = 0;
                                    double derecho_matricula_descuento = 0;

                                    int cantidad_materias_perdidas = cantidad_materias_perdidas(codigo, objconexion, año, periodo);
                                    //aumentar 0,1 a la matricula por cada materia perdida
                                    if (cantidad_materias_perdidas != 0) {
                                        derecho_matricula_materiasp = (cantidad_materias_perdidas * 0.1) * consulta_salario_minimo(objconexion);

                                    }

                                    //verificar descuento
                                    double porcentaje_descuentos = porcentaje_descuento(codigo, objconexion);
                                    if (porcentaje_descuentos != 0) {

                                        derecho_matricula_descuento = (porcentaje_descuentos / 100) * derecho_matricula;

                                    }//sale de descuentos 

                                    derecho_matricula = derecho_matricula + derecho_matricula_materiasp - derecho_matricula_descuento;
                                    liquidacion = true;
                                    estado_liquidacion = "activa";
                                    insercion_recibo(codigo, derecho_matricula, año, periodo, liquidacion, estado_liquidacion, objconexion);

                                } //perdio mas de la mitad y su promedio esta por debajo de 3.2
                                else {
                                    liquidacion = false;
                                    estado_liquidacion = "perdió más del 50% de materias";
                                    insercion_recibo(codigo, 0, año, periodo, liquidacion, estado_liquidacion, objconexion);
                                }

                            } //SE BECÓ
                            else {
                                liquidacion = true;
                                estado_liquidacion = "Matricula de honor";
                                insercion_recibo(codigo, 0, año, periodo, liquidacion, estado_liquidacion, objconexion);

                            }

                        } //multas
                        else {
                            liquidacion = false;
                            estado_liquidacion = "tiene multas activas";
                            insercion_recibo(codigo, 0, año, periodo, liquidacion, estado_liquidacion, objconexion);
                        }
                    } //no cursos inscritos
                    else {
                        liquidacion = false;
                        estado_liquidacion = "no cuenta con cursos pre-inscritos";
                        insercion_recibo(codigo, 0, año, periodo, liquidacion, estado_liquidacion, objconexion);
                    }

                } //estado incorrecto
                else {
                    liquidacion = false;
                    estado_liquidacion = "se encuentra suspendido";
                    insercion_recibo(codigo, 0, año, periodo, liquidacion, estado_liquidacion, objconexion);
                }

            }
            resultado.close();

        } catch (Exception e) {

        }
    }

    public void insercion_recibo(int codigo_estudiante, double derechos_matricula, int año, int periodo, boolean liquidacion, String estado_liquidacion, conexion objconexion) {

        if (periodo == 1) {
            periodo = periodo + 1;

        }
        if (periodo == 2) {
            periodo = periodo - 1;
            año = año + 1;
        }
        Calendar fecha = new GregorianCalendar();
        int año1 = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        int dia_pago = dia + 15;

        if (!liquidacion) {
            String consulta = "insert into recibo_liquidacion(codigo_estudiante,derechos_matricula,servicios_universitarios,derechos_academicos,servicio_transporte,seguro_estudiantil,total_pagar,estado,fecha_limite,fecha_creacion,ano_creacion,periodo_creacion,hora_liquidacion) values(" + codigo_estudiante + ",0,0,0,0,0,0,'" + estado_liquidacion + "','" + año1 + "-" + mes + "-" + dia_pago + "','" + año1 + "-" + mes + "-" + dia + "'," + año1 + "," + periodo + ",'" + hora + ":" + minuto + ":" + segundo + "')";
            try {
                java.sql.Statement st = objconexion.getConexion().createStatement();
                st.execute(consulta);
                st.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } else {

            ResultSet parametros = consulta_parametros(objconexion);
            double servicios_universitarios = 0;
            double derechos_academicos = 0;
            double servicio_transporte = 0;
            double seguro_estudiantil = 0;
            double salario_minimo = consulta_salario_minimo(objconexion);

            try {
                while (parametros.next()) {
                    if (parametros.getString("nombre").equals("servicios_universitarios")) {
                        servicios_universitarios = Double.parseDouble(parametros.getString("valor"));
                    }
                    if (parametros.getString("nombre").equals("derechos_academicos")) {
                        derechos_academicos = Double.parseDouble(parametros.getString("valor"));
                    }
                    if (parametros.getString("nombre").equals("servicios_transporte")) {
                        servicio_transporte = Double.parseDouble(parametros.getString("valor"));
                    }
                    if (parametros.getString("nombre").equals("seguro_estudiantil")) {
                        seguro_estudiantil = Double.parseDouble(parametros.getString("valor"));
                    }

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());

            }

            servicios_universitarios = servicios_universitarios * salario_minimo;
            derechos_academicos = derechos_academicos * salario_minimo;
            servicio_transporte = servicio_transporte * salario_minimo;
            seguro_estudiantil = seguro_estudiantil * salario_minimo;
            double total = derechos_matricula + servicios_universitarios + derechos_academicos + servicio_transporte + seguro_estudiantil;
            String consulta = "insert into recibo_liquidacion(codigo_estudiante,derechos_matricula,servicios_universitarios,derechos_academicos,servicio_transporte,seguro_estudiantil,total_pagar,estado,fecha_limite,fecha_creacion,ano_creacion,periodo_creacion,hora_liquidacion) values(" + codigo_estudiante + "," + derechos_matricula + "," + servicios_universitarios + "," + derechos_academicos + "," + servicio_transporte + "," + seguro_estudiantil + "," + total + ",'" + estado_liquidacion + "','" + año1 + "-" + mes + "-" + dia_pago + "','" + año1 + "-" + mes + "-" + dia + "'," + año1 + "," + periodo + ",'" + hora + ":" + minuto + ":" + segundo + "')";
            try {
                java.sql.Statement st = objconexion.getConexion().createStatement();
                st.execute(consulta);
                st.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }

    }

    public ResultSet consulta_parametros(conexion objconexion) {
        ResultSet resultado = null;
        try {
            String consulta = "select nombre,valor from parametros where codigo != 1";
            java.sql.Statement st = objconexion.getConexion().createStatement();
            resultado = st.executeQuery(consulta);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return resultado;
        }
        return resultado;
    }

    public boolean matricula_honor(int codigo, conexion objconexion, int año, int periodo) {
        boolean bandera = false;
        String consulta = "SELECT EXISTS (SELECT codigo FROM registro_semestre WHERE codigo_estudiante=" + codigo + " AND ano=" + año + " AND periodo=" + periodo + " AND promedio_semestre >= 4.0 AND cantidad_materias_perdidas=0)";
        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                if (resultado.getString("exists").equals("t")) {
                    bandera = true;

                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return bandera;
        }

        return bandera;
    }

    public double porcentaje_descuento(int codigo, conexion objconexion) {
        double porcentaje = 0;
        String consulta = "SELECT porcentaje FROM bienestar WHERE codigo_estudiante=" + codigo + "";
        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                String porcentaje1 = resultado.getString("porcentaje");
                double porcentaje2 = Double.parseDouble(porcentaje1);
                porcentaje = porcentaje2;
            }
            st.close();
            resultado.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return porcentaje;
        }
        return porcentaje;
    }

    public int cantidad_materias_perdidas(int codigo, conexion objconexion, int año, int periodo) {
        int cantidad_materias = 0;
        String consulta = "SELECT cantidad_materias_perdidas FROM registro_semestre WHERE codigo_estudiante=" + codigo + " and ano=" + año + " and periodo=" + periodo;

        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                String cantidad = resultado.getString("cantidad_materias_perdidas");
                int cantidad1 = Integer.parseInt(cantidad);
                cantidad_materias = cantidad1;

            }
            st.close();
            resultado.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return cantidad_materias;
    }

    public boolean porcentaje_y_materias(int codigo, conexion objconexion, int año, int periodo) {
        boolean bandera = false;
        String consulta = "SELECT promedio_semestre, porcentaje_materias_perdidas FROM registro_semestre WHERE codigo_estudiante=" + codigo + " and ano=" + año + " and periodo=" + periodo;

        try {

            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                String materias_perdidas = resultado.getString("porcentaje_materias_perdidas");
                String promedio_semestre = resultado.getString("promedio_semestre");
                double materias_perdidas1 = Double.parseDouble(materias_perdidas);
                double promedio_semestre1 = Double.parseDouble(promedio_semestre);

                if (materias_perdidas1 >= 50 && promedio_semestre1 < 3.2) {
                    bandera = true;

                }

            }
            st.close();
            resultado.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return bandera;
    }

    public double consulta_salario_minimo(conexion objconexion) {
        double salario = 0;
        String consulta_salario = "SELECT valor FROM parametros WHERE nombre = 'salario_minimo'";

        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta_salario);
            while (resultado.next()) {
                double salario1 = Double.parseDouble(resultado.getString("valor"));
                salario = salario1;

            }
            st.close();
            resultado.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return salario;
        }
        return salario;
    }

    public double derecho_matricula(int codigo, conexion objconexion) {
        double derecho = 0;

        String consulta = "SELECT codigo_programa FROM estudiante WHERE codigo=" + codigo + " ";
        try {

            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);
            double salario = consulta_salario_minimo(objconexion);
            while (resultado.next()) {
                if (resultado.getString("codigo_programa").equals("435167") || resultado.getString("codigo_programa").equals("345521") || resultado.getString("codigo_programa").equals("234091") || resultado.getString("codigo_programa").equals("987665")) {
                    derecho = (0.8 * salario);

                } else {
                    derecho = (1.4 * salario);
                }
            }

            st.close();
            resultado.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return derecho;

    }

    public boolean validar_estados(int codigo, conexion objconexion) {

        boolean bandera = false;
        String consulta = "SELECT EXISTS(SELECT codigo FROM estudiante WHERE codigo=" + codigo + " AND estado='suspendido' or estado='expulsado')";
        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                if (resultado.getString("exists").equals("t")) {

                    bandera = true;

                }
            }
            st.close();
            resultado.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return bandera;
    }

    public boolean cursos_inscritos(int codigo, conexion objconexion) {

        boolean bandera = false;
        String consulta = "SELECT EXISTS(SELECT codigo FROM curso_inscrito WHERE codigo_estudiante=" + codigo + " AND estado='preinscrito')";
        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                if (resultado.getString("exists").equals("t")) {

                    bandera = true;

                }
            }
            st.close();
            resultado.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return bandera;

    }

    public boolean multas_y_sanciones(int codigo, conexion objconexion) {

        boolean bandera = false;
        String consulta = "SELECT EXISTS(SELECT codigo FROM multas_sanciones WHERE codigo_estudiante=" + codigo + " AND estado='activa')";
        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                if (resultado.getString("exists").equals("t")) {

                    bandera = true;

                }
            }
            st.close();
            resultado.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return bandera;

    }
    
   // public String estado(String numero_documento, conexion objconexion){
    
   // }
    

    public boolean validar_recibo(String numero_documento, conexion objconexion) {
        boolean bandera = false;
        String consulta = "select exists(select persona.nombre_1,persona.nombre_2,persona.apellido_1,persona.apellido_2,persona.numero_documento,recibo_liquidacion.derechos_matricula,recibo_liquidacion.servicios_universitarios,recibo_liquidacion.derechos_academicos,recibo_liquidacion.servicio_transporte,recibo_liquidacion.seguro_estudiantil,recibo_liquidacion.total_pagar,recibo_liquidacion.fecha_limite,facultad.nombre as nombre_facultad,programa.nombre as nombre_programa,recibo_liquidacion.estado from persona join estudiante on persona.numero_documento=estudiante.numero_documento join programa on programa.codigo=estudiante.codigo_programa join facultad on facultad.codigo=programa.codigo_facultad  join recibo_liquidacion on recibo_liquidacion.codigo_estudiante=estudiante.codigo where recibo_liquidacion.estado='activa' and persona.numero_documento='" + numero_documento + "')";
        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                if (resultado.getString("exists").equals("t")) {
                    bandera = true;
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return bandera;

        }

        return bandera;
    }

    public String informacion_recibo_pdf(String numero_documento, conexion objconexion) {
        String consulta = "select persona.nombre_1,persona.nombre_2,persona.apellido_1,persona.apellido_2,persona.numero_documento,recibo_liquidacion.derechos_matricula,recibo_liquidacion.servicios_universitarios,recibo_liquidacion.derechos_academicos,recibo_liquidacion.servicio_transporte,recibo_liquidacion.seguro_estudiantil,recibo_liquidacion.total_pagar,recibo_liquidacion.fecha_limite,facultad.nombre as nombre_facultad,programa.nombre as nombre_programa,recibo_liquidacion.estado from persona join estudiante on persona.numero_documento=estudiante.numero_documento join programa on programa.codigo=estudiante.codigo_programa join facultad on facultad.codigo=programa.codigo_facultad  join recibo_liquidacion on recibo_liquidacion.codigo_estudiante=estudiante.codigo where recibo_liquidacion.estado='activa' and persona.numero_documento='" + numero_documento + "'";
        String salida = "";
        try {
            java.sql.Statement st = objconexion.getConexion().createStatement();
            ResultSet resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                String nombre = "nombre :" + resultado.getString("nombre_1") + resultado.getString("nombre_2") + resultado.getString("apellido_1") + resultado.getString("apellido_2") + " \n";
                String numero_documento1 = "numero_documento :" + resultado.getString("numero_documento") + " \n";
                String derechos_matricula = "derechos_matricula :                    " + resultado.getString("derechos_matricula") + " \n";
                String servicios_universitarios = "servicios_universitarios :                    " + resultado.getString("servicios_universitarios") + " \n";
                String derechos_academicos = "derechos_academicos :                    " + resultado.getString("derechos_academicos") + " \n";
                String servicio_transporte = "servicio_transporte :                    " + resultado.getString("servicio_transporte") + " \n";
                String seguro_estudiantil = "seguro_estudiantil :                    " + resultado.getString("seguro_estudiantil") + " \n";
                String total_pagar = "total_pagar :                          " + resultado.getString("total_pagar") + " \n";
                String fecha_limite = "fecha_limite :                      " + resultado.getString("fecha_limite") + " \n";
                String nombre_facultad = "nombre_facultad :                    " + resultado.getString("nombre_facultad") + " \n";
                String nombre_programa = "nombre_programa :                    " + resultado.getString("nombre_programa") + " \n";
                salida = nombre + numero_documento1 + derechos_matricula + servicios_universitarios + derechos_academicos + servicio_transporte + seguro_estudiantil + total_pagar + fecha_limite + nombre_facultad + nombre_programa;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return salida;
        }
        return salida;
    }

    public Double getDerechos_matricula() {
        return derechos_matricula;
    }

    public void setDerechos_matricula(Double derechos_matricula) {
        this.derechos_matricula = derechos_matricula;
    }

    public Double getServicios_universitarios() {
        return servicios_universitarios;
    }

    public void setServicios_universitarios(Double servicios_universitarios) {
        this.servicios_universitarios = servicios_universitarios;
    }

    public Double getDerechos_academicos() {
        return derechos_academicos;
    }

    public void setDerechos_academicos(Double derechos_academicos) {
        this.derechos_academicos = derechos_academicos;
    }

    public Double getServicios_transporte() {
        return servicios_transporte;
    }

    public void setServicios_transporte(Double servicios_transporte) {
        this.servicios_transporte = servicios_transporte;
    }

    public Double getSeguro_estudiantil() {
        return seguro_estudiantil;
    }

    public void setSeguro_estudiantil(Double seguro_estudiantil) {
        this.seguro_estudiantil = seguro_estudiantil;
    }

    public Double getTotal_pagar() {
        return total_pagar;
    }

    public void setTotal_pagar(Double total_pagar) {
        this.total_pagar = total_pagar;
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

    public Date getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(Date fecha_limite) {
        this.fecha_limite = fecha_limite;
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

    public Time getHora_liquidacion() {
        return hora_liquidacion;
    }

    public void setHora_liquidacion(Time hora_liquidacion) {
        this.hora_liquidacion = hora_liquidacion;
    }

    public Estudiante getObjestudiante() {
        return objestudiante;
    }

    public void setObjestudiante(Estudiante objestudiante) {
        this.objestudiante = objestudiante;
    }

}
