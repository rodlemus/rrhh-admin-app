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

@WebServlet(value = "/actualizar-tipoC", name = "actualizarTipoContratoControlador")
public class ActualizarTipoContratoControlador extends HttpServlet {

    @Inject
    ITipoContratoRepositorio tipoContratoRepositorio;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TipoContratacion tipoC = this.obtenerDatosTipoC(request);

            if(tipoC.getIdTipoC() == null || tipoC.getIdTipoC() <= 0) {
                throw new ServletException("El tipo de contrato no existe");
            }

            this.tipoContratoRepositorio.actualizarTiposContratos(tipoC);
            response.sendRedirect(request.getContextPath() + "/tipos_contratos");
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    private TipoContratacion obtenerDatosTipoC(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("idTipoC"));
        String tipoC = request.getParameter("tipoC");

        return new TipoContratacion(id, tipoC);
    }
}
