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
import java.util.List;

@WebServlet(value = "/tipos_contratos", name = "tiposContratosControlador")
public class TiposContratoControlador extends HttpServlet{

    @Inject
    ITipoContratoRepositorio tipoContratoRepositorio;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TipoContratacion> tiposC = this.tipoContratoRepositorio.listarTiposContratos(0, 8);
        request.setAttribute("tiposC", tiposC);
        request.getRequestDispatcher("/WEB-INF/views/modulo-detalles_contrataciones/tipos_contratos.jsp").forward(request, response);
    }
}
