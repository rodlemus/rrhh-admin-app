package com.udb.rrhhadminapp.controladores.departamentos;

import com.udb.rrhhadminapp.database.modelos.Cargo;
import com.udb.rrhhadminapp.database.modelos.Departamento;
import com.udb.rrhhadminapp.database.repositorios.IDepartamentoRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/departamentos", name = "departamentosControlador")
public class DepartamentoControlador extends HttpServlet {

    @Inject
    IDepartamentoRepositorio departamentoRepositorio;

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

        // Obtenemos la lista de departamentos con paginación
        List<Departamento> departamentos = this.departamentoRepositorio.listarDepartamentos(offset, tamanio);

        // Obtenemos el total de departamentos para calcular páginas totales
        int totalDepartamentos = this.departamentoRepositorio.contarDepartamentos();
        int totalPages = (int) Math.ceil((double) totalDepartamentos / tamanio);

        // Pasamos los datos a la vista
        request.setAttribute("departamentos", departamentos);
        request.setAttribute("paginaActual", pagina);
        request.setAttribute("totalPaginas", totalPages);

        request.getRequestDispatcher("/WEB-INF/views/modulo-detalles_contrataciones/departamentos.jsp").forward(request, response);
    }
}
