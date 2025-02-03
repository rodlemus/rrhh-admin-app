package com.udb.rrhhadminapp.controladores;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "routerPrincipal", value = "/router-app")
public class RouterInicial extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String moduloDeseado = request.getParameter("modulo");

        switch (moduloDeseado) {
            case "contrataciones":
                response.sendRedirect(request.getContextPath() + "/contrataciones");
                break;
            case "empleados":
                response.sendRedirect(request.getContextPath() + "/empleados");
                break;
            case "propiedades":
                response.sendRedirect(request.getContextPath() + "/propiedades");
                break;
        }

    }

}
