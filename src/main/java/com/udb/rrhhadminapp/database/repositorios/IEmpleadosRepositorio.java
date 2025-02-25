package com.udb.rrhhadminapp.database.repositorios;

import com.udb.rrhhadminapp.database.modelos.Empleado;

import java.util.List;

public interface IEmpleadosRepositorio {
     Empleado guardar(Empleado empleado);
     void eliminar(Integer empleadoId);
     List<Empleado> listar(int offset, int limit);
     Empleado buscarPorId(Integer id);
     List<Empleado> buscarPorDui(String dui);
     void actualizar(Empleado empleado);

}
