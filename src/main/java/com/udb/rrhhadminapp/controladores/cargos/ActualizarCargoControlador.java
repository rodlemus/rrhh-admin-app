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

@WebServlet(value = "/actualizar-cargo", name = "actualizarCargoControlador")
public class ActualizarCargoControlador extends HttpServlet {

    @Inject
    ICargoRepositorio cargoRepositorio;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cargo cargo = this.obtenerDatosCargo(request);

            if(cargo.getIdCargo() == null || cargo.getIdCargo() <= 0) {
                throw new ServletException("El cargo no existe");
            }

            this.cargoRepositorio.actualizarCargo(cargo);
            response.sendRedirect(request.getContextPath() + "/propiedades");
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    private Cargo obtenerDatosCargo(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("idCargo"));
        String nombre = request.getParameter("cargo");
        String descripcion = request.getParameter("descripcionCargo");
        Boolean jefatura = Boolean.parseBoolean(request.getParameter("jefatura"));

        return new Cargo(id, nombre, descripcion, jefatura);
    }
}
