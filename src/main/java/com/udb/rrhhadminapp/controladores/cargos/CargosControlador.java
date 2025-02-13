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

@WebServlet(value = "/propiedades", name = "cargosController")
public class CargosControlador extends HttpServlet {

    @Inject
    private ICargoRepositorio cargoRepositorio;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cargo> cargos = this.cargoRepositorio.listarCargos(0, 8);
        request.setAttribute("cargos", cargos);
        request.getRequestDispatcher("/WEB-INF/views/modulo-detalles_contrataciones/mostrar-detalles.jsp").forward(request, response);
    }
}
