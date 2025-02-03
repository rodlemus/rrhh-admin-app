package com.udb.rrhhadminapp.controladores.empleados;

import com.udb.rrhhadminapp.database.modelos.Empleado;
import com.udb.rrhhadminapp.database.repositorios.IEmpleadosRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = "/registrar-empleado", name = "registrarEmpleadoController")
public class RegistrarEmpleadoControlador extends HttpServlet {
    @Inject
    IEmpleadosRepositorio empleadosRepositorio;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Empleado empleado = this.crearInstanciaEmpleado(request);
            this.empleadosRepositorio.guardar(empleado);
            response.sendRedirect(request.getContextPath() + "/empleados");

        }catch (Exception e){
            throw new ServletException(e);
        }
    }

    private Empleado crearInstanciaEmpleado(HttpServletRequest request) throws ParseException {
        String nombre = request.getParameter("nombre");
        String dui = request.getParameter("dui");
        String usuario = request.getParameter("usuario");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String fechaNacimiento = request.getParameter("fechaNacimiento");

        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date fecha = formatter.parse(fechaNacimiento);
        Empleado empleado = new Empleado(
                dui,
                nombre,
                usuario,
                telefono,
                correo,
                fecha
        );
        return empleado;
    }
}
