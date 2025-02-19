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
        int pagina = 1;
        int tamanio = 5;

        // Verificar si se pasó un parámetro de página
        if (request.getParameter("pagina") != null) {
            try {
                pagina = Integer.parseInt(request.getParameter("pagina"));
                if (pagina < 1) pagina = 1;
            } catch (NumberFormatException e) {
                pagina = 1;
            }
        }

        // Calculamos el desplazamiento (offset)
        int offset = (pagina - 1) * tamanio;

        List<TipoContratacion> tiposC = this.tipoContratoRepositorio.listarTiposContratos(offset, tamanio);

        // Obtenemos el total de tipos de contrataciones para calcular páginas totales
        int totalDepartamentos = this.tipoContratoRepositorio.contarTiposContratos();
        int totalPages = (int) Math.ceil((double) totalDepartamentos / tamanio);

        request.setAttribute("tiposC", tiposC);
        request.setAttribute("paginaActual", pagina);
        request.setAttribute("totalPaginas", totalPages);

        request.getRequestDispatcher("/WEB-INF/views/modulo-detalles_contrataciones/tipos_contratos.jsp").forward(request, response);
    }
}
