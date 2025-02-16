package com.udb.rrhhadminapp.controladores.tipos_contrataciones;

import com.udb.rrhhadminapp.database.modelos.TipoContratacion;
import com.udb.rrhhadminapp.database.repositorios.ITipoContratoRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/registrar-tipoC", name = "registrarTipoContratoControlador")
public class RegistrarTipoContratoControlador extends HttpServlet {

    @Inject
    ITipoContratoRepositorio tipoContratoRepositorio;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TipoContratacion nuevoTipoC = this.crearInstanciaTipoC(request);
            this.tipoContratoRepositorio.agregarTipoC(nuevoTipoC);
            response.sendRedirect(request.getContextPath() + "/propiedades");
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    private TipoContratacion crearInstanciaTipoC(HttpServletRequest request) {
        String tipoC = request.getParameter("tipoC");

        return new TipoContratacion(tipoC);
    }
}
