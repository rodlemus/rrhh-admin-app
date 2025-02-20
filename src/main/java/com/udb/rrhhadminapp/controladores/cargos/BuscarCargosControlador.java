package com.udb.rrhhadminapp.controladores.cargos;

import com.udb.rrhhadminapp.database.modelos.Cargo;
import com.udb.rrhhadminapp.database.repositorios.ICargoRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/buscar-cargo", name = "buscarCargosController")
public class BuscarCargosControlador extends HttpServlet {

    @Inject
    ICargoRepositorio cargoRepositorio;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String busqueda = request.getParameter("busqueda");

        List<Cargo> cargos = cargoRepositorio.buscarCargoPorNombre(busqueda);
        Gson gson = new Gson();
        String json = gson.toJson(cargos);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
