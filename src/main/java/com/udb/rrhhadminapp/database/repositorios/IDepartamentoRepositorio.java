package com.udb.rrhhadminapp.database.repositorios;

import com.udb.rrhhadminapp.database.modelos.Departamento;

import java.util.List;

public interface IDepartamentoRepositorio {

    List<Departamento> listarDepartamentos(int offset, int limit);
    Departamento agregarDepartamento(Departamento departamento);
    void actualizarDepartamento(Departamento departamento);
    void eliminarDepartamento(Integer id);

    Departamento buscarDepartamentoPorId(Integer id);
}
