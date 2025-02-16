package com.udb.rrhhadminapp.controladores.departamentos;

import com.udb.rrhhadminapp.database.modelos.Cargo;
import com.udb.rrhhadminapp.database.modelos.Departamento;
import com.udb.rrhhadminapp.database.repositorios.IDepartamentoRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/departamentos", name = "departamentosControlador")
public class DepartamentoControlador extends HttpServlet {

    @Inject
    IDepartamentoRepositorio departamentoRepositorio;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Departamento> departamentos = this.departamentoRepositorio.listarDepartamentos(0, 8);
        request.setAttribute("departamentos", departamentos);
        request.getRequestDispatcher("/WEB-INF/views/modulo-detalles_contrataciones/departamentos.jsp").forward(request, response);
    }
}
