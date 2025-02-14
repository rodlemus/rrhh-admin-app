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
        Integer idCargo = Integer.parseInt(request.getParameter("idCargo"));

        Optional<Cargo> cargo = Optional.of(this.cargoRepositorio.buscarCargoPorId(idCargo));
        // Aqui configuramos la respuesta para que sea de tipo JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if(cargo.isPresent()){
            cargoRepositorio.eliminarCargo(idCargo);
            // Si el id se encuentra, enviamos un mensaje de exito
            String jsonResponse = "{\"message\": \"Cargo eliminado correctamente\", \"id\": \"" + idCargo + "\"}";
            response.getWriter().write(jsonResponse);
        } else {
            // Si no se encuentra el id, enviamos un mensaje de error
            String jsonResponse = "{\"message\": \"Error: ID de cargo no encontrado\"}";
            response.getWriter().write(jsonResponse);
        }
    }
}
