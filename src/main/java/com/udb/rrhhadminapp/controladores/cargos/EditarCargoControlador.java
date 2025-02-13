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
import java.util.List;

@WebServlet(value = "/editar-cargo", name = "editarCargoControlador")
public class EditarCargoControlador extends HttpServlet {

    @Inject
    private ICargoRepositorio cargoRepositorio;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCargo = request.getParameter("id");
        if(idCargo != null) {
            try {
                int id = Integer.parseInt(idCargo);
                Cargo cargo = cargoRepositorio.buscarCargoPorId(id);
                request.setAttribute("cargo", cargo);
            } catch(NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID cargo invalido");
                return;
            }
        }
        request.getRequestDispatcher("/mostrar-detalles.jsp").forward(request, response);
    }
}
