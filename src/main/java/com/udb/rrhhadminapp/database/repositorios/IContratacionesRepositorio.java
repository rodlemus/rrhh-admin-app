package com.udb.rrhhadminapp.database.repositorios;


import com.udb.rrhhadminapp.database.modelos.Contrataciones;

import java.util.List;

public interface IContratacionesRepositorio {

    // Listar todas las contrataciones
    List<Contrataciones> listarContrataciones();

    // Listar contrataciones con paginación
    List<Contrataciones> listarContrataciones(int offset, int limit);

    // Agregar una nueva contratación
    Contrataciones agregarContratacion(Contrataciones contratacion);

    // Actualizar una contratación existente
    void actualizarContratacion(Contrataciones contratacion);

    // Eliminar una contratación por ID
    void eliminarContratacion(Integer id);

    // Buscar una contratación por ID
    Contrataciones buscarContratacionPorId(Integer id);

    // Contar la cantidad total de contrataciones
    int contarContrataciones();


}
