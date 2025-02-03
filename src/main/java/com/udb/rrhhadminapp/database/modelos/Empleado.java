package com.udb.rrhhadminapp.database.modelos;

import java.util.Date;

public class Empleado {

    private Integer idEmpleado;
    private String numeroDui;
    private String nombre;
    private String usuario;
    private String numeroTelefono;
    private String correoInstitucional;
    private Date fechaNacimiento;


    public Empleado(String numeroDui, String nombre, String usuario, String numeroTelefono, String correoInstitucional, Date fechaNacimiento) {
        this.numeroDui = numeroDui;
        this.nombre = nombre;
        this.usuario = usuario;
        this.numeroTelefono = numeroTelefono;
        this.correoInstitucional = correoInstitucional;
        this.fechaNacimiento = fechaNacimiento;
    }

    // este contructor sera util cuando traigamos empleados desde los repositorios a la base de datos
    // ya que en esa parte si contaremos con el id que genera la base de datos
    public  Empleado(Integer idEmpleado, String numeroDui, String nombre, String usuario, String numeroTelefono, String correoInstitucional, Date fechaNacimiento){
        this.idEmpleado = idEmpleado;
        this.numeroDui = numeroDui;
        this.nombre = nombre;
        this.usuario = usuario;
        this.numeroTelefono = numeroTelefono;
        this.correoInstitucional = correoInstitucional;
        this.fechaNacimiento = fechaNacimiento;
    }


    public String getNumeroDui() {
        return numeroDui;
    }

    public void setNumeroDui(String numeroDui) {
        this.numeroDui = numeroDui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
