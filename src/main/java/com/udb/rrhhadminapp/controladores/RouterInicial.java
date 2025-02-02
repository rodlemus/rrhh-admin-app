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

        ServletContext ctx = getServletContext();
        switch (moduloDeseado) {
            case "contrataciones":
                ctx.getRequestDispatcher("/WEB-INF/views/modulo-contrataciones/registrar-contratacion.jsp").forward(request, response);
                response.sendRedirect(request.getContextPath() + "/contrataciones");
                break;
            case "empleados":
                ctx.getRequestDispatcher("/WEB-INF/views/modulo-contrataciones/registrar-contratacion.jsp").forward(request, response);
                break;
            case "propiedades":
                ctx.getRequestDispatcher("/WEB-INF/views/modulo-contrataciones/registrar-contratacion.jsp").forward(request, response);
                break;
        }

    }

}
