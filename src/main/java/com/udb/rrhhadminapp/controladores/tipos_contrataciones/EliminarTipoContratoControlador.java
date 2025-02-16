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
import java.util.Optional;

@WebServlet(value = "/eliminar-tipoC", name = "eliminarTipoContratoControlador")
public class EliminarTipoContratoControlador extends HttpServlet {

    @Inject
    ITipoContratoRepositorio tipoContratoRepositorio;

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificar que el parámetro 'idCargo' esté presente
        String idTipoCParam = request.getParameter("idTipoC");

        // Imprimir el parámetro recibido para depuración
        System.out.println("Parametro recibido idTipoC: " + idTipoCParam);

        if (idTipoCParam == null || idTipoCParam.isEmpty()) {
            // Si no se recibe el parámetro, retornamos un error 400 (Bad Request)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String jsonResponse = "{\"message\": \"Error: Parámetro 'idTipoC' no proporcionado\"}";
            response.getWriter().write(jsonResponse);
            return;
        }

        try {
            Integer idTipoC = Integer.parseInt(idTipoCParam);  // Convertir el parámetro a Integer

            Optional<TipoContratacion> tipoC = Optional.ofNullable(tipoContratoRepositorio.buscarTipoContratoPorId(idTipoC));

            // Aquí configuramos la respuesta para que sea de tipo JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (tipoC.isPresent()) {
                tipoContratoRepositorio.eliminarTiposContratos(idTipoC);
                // Si el id se encuentra, enviamos un mensaje de éxito
                String jsonResponse = "{\"message\": \"Tipo de contrato eliminado correctamente\", \"id\": \"" + idTipoC + "\"}";
                response.getWriter().write(jsonResponse);
            } else {
                // Si no se encuentra el id, enviamos un mensaje de error
                String jsonResponse = "{\"message\": \"Error: ID de Tipo de contrato no encontrado\"}";
                response.getWriter().write(jsonResponse);
            }
        } catch (NumberFormatException e) {
            // Si el parámetro no es un número válido
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String jsonResponse = "{\"message\": \"Error: El parámetro 'idTipoC' no es un número válido\"}";
            response.getWriter().write(jsonResponse);
        }
    }
}
