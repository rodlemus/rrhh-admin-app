package com.udb.rrhhadminapp.controladores.cargos;

import com.udb.rrhhadminapp.database.modelos.Cargo;
import com.udb.rrhhadminapp.database.repositorios.ICargoRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/eliminar-cargo", name = "eliminarCargoControlador")
public class EliminarCargoControlador extends HttpServlet {
    @Inject
    ICargoRepositorio cargoRepositorio;

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificar que el parámetro 'idCargo' esté presente
        String idCargoParam = request.getParameter("idCargo");

        // Imprimir el parámetro recibido para depuración
        System.out.println("Parametro recibido idCargo: " + idCargoParam);

        if (idCargoParam == null || idCargoParam.isEmpty()) {
            // Si no se recibe el parámetro, retornamos un error 400 (Bad Request)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String jsonResponse = "{\"message\": \"Error: Parámetro 'idCargo' no proporcionado\"}";
            response.getWriter().write(jsonResponse);
            return;
        }

        try {
            Integer idCargo = Integer.parseInt(idCargoParam);  // Convertir el parámetro a Integer

            Optional<Cargo> cargo = Optional.ofNullable(cargoRepositorio.buscarCargoPorId(idCargo));

            // Aquí configuramos la respuesta para que sea de tipo JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (cargo.isPresent()) {
                cargoRepositorio.eliminarCargo(idCargo);
                // Si el id se encuentra, enviamos un mensaje de éxito
                String jsonResponse = "{\"message\": \"Cargo eliminado correctamente\", \"id\": \"" + idCargo + "\"}";
                response.getWriter().write(jsonResponse);
            } else {
                // Si no se encuentra el id, enviamos un mensaje de error
                String jsonResponse = "{\"message\": \"Error: ID de cargo no encontrado\"}";
                response.getWriter().write(jsonResponse);
            }
        } catch (NumberFormatException e) {
            // Si el parámetro no es un número válido
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String jsonResponse = "{\"message\": \"Error: El parámetro 'idCargo' no es un número válido\"}";
            response.getWriter().write(jsonResponse);
        }
    }
}
