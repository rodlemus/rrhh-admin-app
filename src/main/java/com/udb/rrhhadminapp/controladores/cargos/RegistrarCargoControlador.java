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
import java.text.ParseException;

@WebServlet(value = "/registrar-cargo", name = "registrarCargoControlador")
public class RegistrarCargoControlador extends HttpServlet {

    @Inject
    ICargoRepositorio cargoRepositorio;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cargo nuevoCargo = this.crearInstanciaCargo(request);
            this.cargoRepositorio.agregarCargo(nuevoCargo);
            response.sendRedirect(request.getContextPath() + "/cargos");
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    private Cargo crearInstanciaCargo(HttpServletRequest request) {
        String nombreCargo = request.getParameter("cargo");
        String descripcion = request.getParameter("descripcionCargo");
        String jefatura = request.getParameter("jefatura");

        boolean jefaturaCargo = Boolean.valueOf(jefatura);

        Cargo cargos = new Cargo(
                nombreCargo,
                descripcion,
                jefaturaCargo
        );
        return cargos;
    }
}
