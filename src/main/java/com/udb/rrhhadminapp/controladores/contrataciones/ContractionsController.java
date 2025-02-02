package com.udb.rrhhadminapp.controladores.contrataciones;

import com.udb.rrhhadminapp.database.modelos.Empleado;
import com.udb.rrhhadminapp.database.repositorios.EmpleadoRepositorio;
import com.udb.rrhhadminapp.database.repositorios.IEmpleadosRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet(value = "/contrataciones", name = "contratacionesController")
public class ContractionsController extends HttpServlet {

    @Inject
    private IEmpleadosRepositorio empleadoRepositorio;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Empleado> empleados = this.empleadoRepositorio.listar(0,5);
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("/WEB-INF/views/modulo-contrataciones/registrar-contratacion.jsp").forward(request, response);
    }
}
