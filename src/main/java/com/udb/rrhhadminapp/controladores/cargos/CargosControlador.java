package com.udb.rrhhadminapp.controladores.cargos;

import com.udb.rrhhadminapp.database.modelos.Cargo;
import com.udb.rrhhadminapp.database.repositorios.ICargoRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/propiedades", name = "cargosController")
public class CargosControlador extends HttpServlet {

    @Inject
    private ICargoRepositorio cargoRepositorio;

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

        List<Cargo> cargos = this.cargoRepositorio.listarCargos(offset, tamanio);

        // Obtenemos el total de cargos para calcular páginas totales
        int totalDepartamentos = this.cargoRepositorio.contarCargos();
        int totalPages = (int) Math.ceil((double) totalDepartamentos / tamanio);

        request.setAttribute("cargos", cargos);
        request.setAttribute("paginaActual", pagina);
        request.setAttribute("totalPaginas", totalPages);

        request.getRequestDispatcher("/WEB-INF/views/modulo-detalles_contrataciones/cargos.jsp").forward(request, response);
    }
}
