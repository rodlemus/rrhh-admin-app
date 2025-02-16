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

@WebServlet(value = "/actualizar-departamento", name = "actualizarDepartamentoControlador")
public class ActualizarDepartamentoControlador extends HttpServlet {

    @Inject
    IDepartamentoRepositorio departamentoRepositorio;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Departamento departamento = this.obtenerDatosDepartamento(request);

            if(departamento.getIdDepartamento() == null || departamento.getIdDepartamento() <= 0) {
                throw new ServletException("El departamento no existe");
            }

            this.departamentoRepositorio.actualizarDepartamento(departamento);
            response.sendRedirect(request.getContextPath() + "/departamentos");
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    private Departamento obtenerDatosDepartamento(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("idDepartamento"));
        String departamento = request.getParameter("departamento");
        String descripcion = request.getParameter("descripcionDepartamento");

        return new Departamento(id, departamento, descripcion);
    }
}
