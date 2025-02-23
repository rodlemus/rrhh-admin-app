package com.udb.rrhhadminapp.controladores.contrataciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udb.rrhhadminapp.database.modelos.*;
import com.udb.rrhhadminapp.database.repositorios.*;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/contrataciones", name = "contratacionesController")
public class ContractionsController extends HttpServlet {

    @Inject
    private ICargoRepositorio cargoRepositorio;
    @Inject
    private IDepartamentoRepositorio departamentoRepositorio;
    @Inject
    private ITipoContratoRepositorio tipoContratoRepositorio;

    @Inject
    private IContratacionesRepositorio contratacionesRepositorio;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cargo> cargos = this.cargoRepositorio.listarCargos();
        List<Departamento> departamentos = this.departamentoRepositorio.listarDepartamentos();
        List<TipoContratacion> tiposContrataciones = this.tipoContratoRepositorio.listarTiposContratos();

        request.setAttribute("cargos", cargos);
        request.setAttribute("departamentos", departamentos);
        request.setAttribute("tiposContrataciones", tiposContrataciones);

        request.getRequestDispatcher("/WEB-INF/views/modulo-contrataciones/registrar-contratacion.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Leer el cuerpo de la petición
        StringBuilder jsonBody = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
        }

        try {
            // Convertir JSON a objeto Contrataciones
            Contrataciones nuevaContratacion = objectMapper.readValue(jsonBody.toString(), Contrataciones.class);
            nuevaContratacion.setEstado(true); // Asignar estado activo por defecto

            // Guardar en la base de datos
            Contrataciones contratacionGuardada = contratacionesRepositorio.agregarContratacion(nuevaContratacion);

            // Responder con JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_CREATED);

            response.getWriter().write(objectMapper.writeValueAsString(contratacionGuardada));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(e.getMessage());
        }
    }
}
