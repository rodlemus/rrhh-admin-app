package com.udb.rrhhadminapp.database.repositorios;

import com.udb.rrhhadminapp.database.modelos.TipoContratacion;

import java.util.List;

public interface ITipoContratoRepositorio {

    List<TipoContratacion> listarTiposContratos(int offset, int limit);
    TipoContratacion agregarTipoC(TipoContratacion tipoC);
    void actualizarTiposContratos(TipoContratacion tipoC);
    void eliminarTiposContratos(Integer id);

    TipoContratacion buscarTipoContratoPorId(Integer id);
    int contarTiposContratos();
}
