package com.udb.rrhhadminapp.database.repositorios;

import com.udb.rrhhadminapp.database.modelos.Cargo;

import java.util.List;

public interface ICargoRepositorio {

    List<Cargo> listarCargos(int offset, int limit);
    List<Cargo> listarCargos();
    Cargo agregarCargo(Cargo cargo);
    void actualizarCargo(Cargo cargo);
    void eliminarCargo(Integer id);

    Cargo buscarCargoPorId(Integer id);
    List<Cargo> buscarCargoPorNombre(String cargo);
    int contarCargos();
}
