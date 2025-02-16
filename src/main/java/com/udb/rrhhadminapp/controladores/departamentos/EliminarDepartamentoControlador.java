package com.udb.rrhhadminapp.controladores.departamentos;

import com.udb.rrhhadminapp.database.modelos.Departamento;
import com.udb.rrhhadminapp.database.repositorios.IDepartamentoRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/eliminar-departamento", name = "eliminarDepartamentoControlador")
public class EliminarDepartamentoControlador extends HttpServlet {

    @Inject
    IDepartamentoRepositorio departamentoRepositorio;

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificar que el parámetro 'idDepartamento' esté presente
        String idDepartamento = request.getParameter("idDepartamento");

        if (idDepartamento == null || idDepartamento.isEmpty()) {
            // Si no se recibe el parámetro, retornamos un error 400 (Bad Request)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String jsonResponse = "{\"message\": \"Error: Parámetro 'idDepartamento' no proporcionado\"}";
            response.getWriter().write(jsonResponse);
            return;
        }

        try {
            Integer idDepa = Integer.parseInt(idDepartamento);  // Convertir el parámetro a Integer

            Optional<Departamento> departamento = Optional.ofNullable(departamentoRepositorio.buscarDepartamentoPorId(idDepa));

            // Aquí configuramos la respuesta para que sea de tipo JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (departamento.isPresent()) {
                departamentoRepositorio.eliminarDepartamento(idDepa);
                // Si el id se encuentra, enviamos un mensaje de éxito
                String jsonResponse = "{\"message\": \"Departamento eliminado correctamente\", \"id\": \"" + idDepa + "\"}";
                response.getWriter().write(jsonResponse);
            } else {
                // Si no se encuentra el id, enviamos un mensaje de error
                String jsonResponse = "{\"message\": \"Error: ID de departamento no encontrado\"}";
                response.getWriter().write(jsonResponse);
            }
        } catch (NumberFormatException e) {
            // Si el parámetro no es un número válido
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String jsonResponse = "{\"message\": \"Error: El parámetro 'idDepartamento' no es un número válido\"}";
            response.getWriter().write(jsonResponse);
        }
    }
}
