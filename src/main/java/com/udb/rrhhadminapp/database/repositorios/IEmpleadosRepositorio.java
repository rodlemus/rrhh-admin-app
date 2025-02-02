package com.udb.rrhhadminapp.database.repositorios;

import com.udb.rrhhadminapp.database.entidades.Empleado;

import java.util.List;

public interface IEmpleadosRepositorio {
    public Empleado guardar(Empleado empleado);
    public void eliminar(Empleado empleado);
    public Empleado buscar(Empleado empleado);
    public List<Empleado> listar(int offset, int limit);
}
