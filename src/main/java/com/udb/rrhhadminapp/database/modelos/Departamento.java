package com.udb.rrhhadminapp.database.modelos;

public class Departamento {

    // Definimos los atributos que hacen referencia a la tabla Cargos
    private Integer idDepartamento;
    private String departamento;
    private String descripcion;

    public Departamento(String departamento, String descripcion) {
        this.departamento = departamento;
        this.descripcion = descripcion;
    }

    public Departamento(Integer idDepartamento, String departamento, String descripcion) {
        this.idDepartamento = idDepartamento;
        this.departamento = departamento;
        this.descripcion = descripcion;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
