package com.udb.rrhhadminapp.controladores.departamentos;

import com.udb.rrhhadminapp.database.modelos.Departamento;
import com.udb.rrhhadminapp.database.repositorios.IDepartamentoRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/registrar-departamento", name = "registrarDepartamentoControlador")
public class RegistrarDepartamentoControlador extends HttpServlet {

    @Inject
    IDepartamentoRepositorio departamentoRepositorio;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Departamento nuevoDepartamento = this.crearInstanciaDepartamento(request);
            this.departamentoRepositorio.agregarDepartamento(nuevoDepartamento);
            response.sendRedirect(request.getContextPath() + "/departamentos");
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    private Departamento crearInstanciaDepartamento(HttpServletRequest request) {
        String departamento = request.getParameter("departamento");
        String descripcion = request.getParameter("descripcionDepartamento");

        return new Departamento(departamento, descripcion);
    }
}
