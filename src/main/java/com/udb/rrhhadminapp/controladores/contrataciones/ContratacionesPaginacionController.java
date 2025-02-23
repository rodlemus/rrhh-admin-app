package com.udb.rrhhadminapp.controladores.contrataciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udb.rrhhadminapp.database.modelos.Contrataciones;
import com.udb.rrhhadminapp.database.repositorios.IContratacionesRepositorio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(value = "/listar-contrataciones", name = "contratacionesPaginationController")
public class ContratacionesPaginacionController extends HttpServlet {
    @Inject
    private IContratacionesRepositorio contratacionesRepositorio;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

       try{
           Integer pagina = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));
           Integer limit = Integer.parseInt(Optional.ofNullable(request.getParameter("limit")).orElse("5"));
           Integer totalContrataciones = this.contratacionesRepositorio.contarContrataciones();
           Integer offset = (pagina - 1) * limit;
           Integer totalPaginas = (int) Math.ceil((double) totalContrataciones / limit);

           List<Contrataciones> contrataciones = this.contratacionesRepositorio.listarContrataciones(offset, limit);

           // Crear objeto de respuesta
           var respuesta = new JsonResponse(totalPaginas, contrataciones);

           // Configurar la respuesta HTTP

           response.setStatus( HttpServletResponse.SC_OK );

           // Convertir a JSON y enviarlo
           ObjectMapper objectMapper = new ObjectMapper();
           PrintWriter out = response.getWriter();
           out.print(objectMapper.writeValueAsString(respuesta));
           out.flush();
       }catch (Exception e){
           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
           response.getWriter().write("{\"error\": \"Ocurrió un error en el servidor.\"}");
       }

    }

    static class JsonResponse {
        public Integer totalPaginas;
        public List<Contrataciones> contrataciones;

        public JsonResponse(Integer totalPaginas, List<Contrataciones> contrataciones) {
            this.totalPaginas = totalPaginas;
            this.contrataciones = contrataciones;
        }
    }
}
