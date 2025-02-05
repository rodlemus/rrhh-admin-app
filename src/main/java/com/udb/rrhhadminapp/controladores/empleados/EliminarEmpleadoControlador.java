package com.udb.rrhhadminapp.controladores.empleados;

import com.google.gson.Gson;
import com.udb.rrhhadminapp.database.modelos.Empleado;
import com.udb.rrhhadminapp.database.repositorios.IEmpleadosRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(value = "/eliminar-empleado", name = "eliminarEmpleadoControlador")
public class EliminarEmpleadoControlador extends HttpServlet {

    @Inject
    IEmpleadosRepositorio empleadosRepositorio;

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer empleadoId = Integer.parseInt(request.getParameter("id"));

        Optional<Empleado> empleado = Optional.of(this.empleadosRepositorio.buscarPorId(empleadoId));
        // Configurar la respuesta para que sea de tipo JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (empleado.isPresent()) {
            empleadosRepositorio.eliminar(empleadoId);
            // Si el ID fue proporcionado, enviamos una respuesta de éxito
            String jsonResponse = "{\"message\": \"Empleado eliminado exitosamente\", \"id\": \"" + empleadoId + "\"}";
            response.getWriter().write(jsonResponse);
        }else{
            // Si no se proporcionó un ID, enviamos un mensaje de error
            String jsonResponse = "{\"message\": \"Error: ID de empleado no proporcionado\"}";
            response.getWriter().write(jsonResponse);
        }

    }
}
