package com.udb.rrhhadminapp.database.modelos;

public class TipoContratacion {

    // Definimos los atributos que hacen referencia a la tabla Cargos
    private Integer idTipoC;
    private String tipoC;

    // Definimos los constructores
    public TipoContratacion(String tipoC) {
        this.tipoC = tipoC;
    }

    public TipoContratacion(Integer idTipoC, String tipoC) {
        this.idTipoC = idTipoC;
        this.tipoC = tipoC;
    }

    // Definimos los metodos GET y SET para los atributos
    public Integer getIdTipoC() {
        return idTipoC;
    }

    public void setIdTipoC(Integer idTipoC) {
        this.idTipoC = idTipoC;
    }

    public String getTipoC() {
        return tipoC;
    }

    public void setTipoC(String tipoC) {
        this.tipoC = tipoC;
    }
}
