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
public class Bitacora {

    private int codigo;
    private String usuario;
    private Date fecha;
    private Time hora;
    private String ip;
    private String operacion;
    private String tabla;
    private String atributo;

    public Bitacora(int codigo, String usuario, Date fecha, Time hora, String ip, String operacion, String tabla, String atributo) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.ip = ip;
        this.operacion = operacion;
        this.tabla = tabla;
        this.atributo = atributo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

}
