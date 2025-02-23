package com.udb.rrhhadminapp.controladores.contrataciones;

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
import java.util.List;

@WebServlet(value = "/buscar-dui", name = "buscarDuiController")
public class BuscarDuiWebServlet extends HttpServlet {
    @Inject
    private IEmpleadosRepositorio empleadosRepositorio;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List<Empleado> empleados = this.empleadosRepositorio.buscarPorDui(query);
        // Crear una respuesta JSON
        response.setContentType("application/json"); // Establecer el tipo de contenido como JSON
        response.setCharacterEncoding("UTF-8");

        // Crear un objeto Gson para convertir la lista a JSON
        Gson gson = new Gson();

        // Si la lista de empleados está vacía, enviar un array vacío
        if (empleados == null || empleados.isEmpty()) {
            response.getWriter().write("[]"); // Array vacío
        } else {
            // Convertir la lista de empleados a JSON
            String jsonResponse = gson.toJson(empleados);
            response.getWriter().write(jsonResponse); // Enviar el JSON como respuesta
        }
    }
}
