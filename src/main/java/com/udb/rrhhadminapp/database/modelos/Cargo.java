package com.udb.rrhhadminapp.database.modelos;

public class Cargo {
    // Definimos los atributos que hacen referencia a la tabla Cargos
    private Integer idCargo;
    private String cargo;
    private String descripcionCargo;
    private boolean jefatura;

    // Definimos el constructor para leer los datos
    public Cargo(String cargo, String descripcionCargo, boolean jefatura) {
        this.cargo = cargo;
        this.descripcionCargo = descripcionCargo;
        this.jefatura = jefatura;
    }

    // Definimos otro constructor que ayuda a traer los cargos desde el repositorio a la base de datos
    public Cargo(Integer idCargo, String cargo, String descripcionCargo, boolean jefatura) {
        this.idCargo = idCargo;
        this.cargo = cargo;
        this.descripcionCargo = descripcionCargo;
        this.jefatura = jefatura;
    }

    // Aqui Establecemos los metodos Get y Set para el manejo de los atributos(campos) de la tabla Cargos
    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }

    public boolean isJefatura() {
        return jefatura;
    }

    public void setJefatura(boolean jefatura) {
        this.jefatura = jefatura;
    }
}
