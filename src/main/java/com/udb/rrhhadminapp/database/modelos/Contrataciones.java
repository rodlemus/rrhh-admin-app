package com.udb.rrhhadminapp.database.modelos;

public class Contrataciones {

    // Atributos que hacen referencia a la tabla Contrataciones
    private Integer idContratacion;
    private Integer idEmpleado;
    private Integer idDepartamento;
    private Integer idTipoContratacion;
    private Integer idCargo;
    private String fechaContratacion;
    private Double salario;
    private Boolean estado;
    public Contrataciones(){}
    // Constructor para leer los datos
    public Contrataciones(Integer idEmpleado, Integer idDepartamento, Integer idTipoContratacion, Integer idCargo,
                          String fechaContratacion, Double salario, Boolean estado) {
        this.idEmpleado = idEmpleado;
        this.idDepartamento = idDepartamento;
        this.idTipoContratacion = idTipoContratacion;
        this.idCargo = idCargo;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
        this.estado = estado;
    }

    // Constructor para traer los datos desde la base de datos
    public Contrataciones(Integer idContratacion, Integer idEmpleado, Integer idDepartamento, Integer idTipoContratacion,
                          Integer idCargo, String fechaContratacion, Double salario, Boolean estado) {
        this.idContratacion = idContratacion;
        this.idEmpleado = idEmpleado;
        this.idDepartamento = idDepartamento;
        this.idTipoContratacion = idTipoContratacion;
        this.idCargo = idCargo;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
        this.estado = estado;
    }


    // Métodos Get y Set para los atributos
    public Integer getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(Integer idContratacion) {
        this.idContratacion = idContratacion;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
