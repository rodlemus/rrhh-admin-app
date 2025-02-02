package com.udb.rrhhadminapp.database.repositorios;

import com.udb.rrhhadminapp.database.modelos.Empleado;

import java.util.List;

public interface IEmpleadosRepositorio {
     Empleado guardar(Empleado empleado);
     void eliminar(Empleado empleado);
     Empleado buscar(Empleado empleado);
     List<Empleado> listar(int offset, int limit);
     Empleado buscarPorId(Long id);
     Empleado buscarPorDui(String dui);
     void actualizar(Empleado empleado);

}
