package com.udb.rrhhadminapp.database.repositorios;

import com.udb.rrhhadminapp.database.ConexionBaseDeDatos;
import com.udb.rrhhadminapp.database.modelos.Contrataciones;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ContratacionesRepositorio implements IContratacionesRepositorio {

    @Override
    public List<Contrataciones> listarContrataciones() {
        String query = "SELECT * FROM contrataciones;";
        List<Contrataciones> contrataciones = new ArrayList<>();

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contrataciones.add(new Contrataciones(
                        rs.getInt("id_contratacion"),
                        rs.getInt("id_empleado"),
                        rs.getInt("id_departamento"),
                        rs.getInt("id_tipo_contratacion"),
                        rs.getInt("id_cargo"),
                        rs.getString("fecha_contratacion"),
                        rs.getDouble("salario"),
                        rs.getBoolean("estado")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al listar las contrataciones", ex);
        }

        return contrataciones;
    }

    @Override
    public List<Contrataciones> listarContrataciones(int offset, int limit) {
        String query = """
        SELECT
            c.id,
            c.idempleado, e.nombrepersona AS nombreEmpleado,
            c.iddepartamento, d.nombredepartamento AS nombreDepartamento,
            c.idtipocontratacion, t.tipocontratacion AS nombreTipoContratacion,
            c.idcargo, ca.cargo AS nombreCargo,
            c.fechacontratacion,
            c.salario,
            c.estado
        FROM contrataciones c
        JOIN empleados e ON c.idempleado = e.id
        JOIN departamento d ON c.iddepartamento = d.id
        JOIN tipo_contratacion t ON c.idtipocontratacion = t.id
        JOIN cargos ca ON c.idcargo = ca.id
        ORDER BY c.fechacontratacion ASC
        LIMIT ? OFFSET ?;
    """;

        List<Contrataciones> contrataciones = new ArrayList<>();

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contrataciones contratacion = new Contrataciones();
                contratacion.setIdContratacion(rs.getInt("id"));

                contratacion.setIdEmpleado(rs.getInt("idempleado"));
                contratacion.setNombreEmpleado(rs.getString("nombreEmpleado"));

                contratacion.setIdDepartamento(rs.getInt("iddepartamento"));
                contratacion.setNombreDepartamento(rs.getString("nombreDepartamento"));

                contratacion.setIdTipoContratacion(rs.getInt("idtipocontratacion"));
                contratacion.setNombreTipoContratacion(rs.getString("nombreTipoContratacion"));

                contratacion.setIdCargo(rs.getInt("idcargo"));
                contratacion.setNombreCargo(rs.getString("nombreCargo"));

                contratacion.setFechaContratacion(rs.getString("fechacontratacion"));
                contratacion.setSalario(rs.getDouble("salario"));
                contratacion.setEstado(rs.getBoolean("estado"));

                contrataciones.add(contratacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al listar las contrataciones con paginación", ex);
        }

        return contrataciones;
    }


    @Override
    public Contrataciones agregarContratacion(Contrataciones contratacion) {
        String query = "INSERT INTO contrataciones (idEmpleado, idDepartamento, idTipoContratacion, idCargo, fechaContratacion, salario) VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = formatter.parse(contratacion.getFechaContratacion()); // 📌 java.util.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); //

            ps.setInt(1, contratacion.getIdEmpleado());
            ps.setInt(2, contratacion.getIdDepartamento());
            ps.setInt(3, contratacion.getIdTipoContratacion());
            ps.setInt(4, contratacion.getIdCargo());
            ps.setDate(5, sqlDate);
            ps.setDouble(6, contratacion.getSalario());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contratacion.setIdContratacion(generatedKeys.getInt(1));
                }
            }

            return contratacion;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al agregar la contratación", ex);
        } catch (ParseException e) {
            throw new RuntimeException("Error al castear fecha");
        }
    }

    @Override
    public void actualizarContratacion(Contrataciones contratacion) {
        String query = "UPDATE contrataciones SET id_empleado = ?, id_departamento = ?, id_tipo_contratacion = ?, id_cargo = ?, fecha_contratacion = ?, salario = ?, estado = ? WHERE id_contratacion = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, contratacion.getIdEmpleado());
            ps.setInt(2, contratacion.getIdDepartamento());
            ps.setInt(3, contratacion.getIdTipoContratacion());
            ps.setInt(4, contratacion.getIdCargo());
            ps.setString(5, contratacion.getFechaContratacion());
            ps.setDouble(6, contratacion.getSalario());
            ps.setBoolean(7, contratacion.getEstado());
            ps.setInt(8, contratacion.getIdContratacion());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Error: No se pudo encontrar la contratación con ID: " + contratacion.getIdContratacion());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al actualizar la contratación", ex);
        }
    }

    @Override
    public void eliminarContratacion(Integer id) {
        String query = "DELETE FROM contrataciones WHERE id_contratacion = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al eliminar la contratación", ex);
        }
    }

    @Override
    public Contrataciones buscarContratacionPorId(Integer id) {
        String query = "SELECT * FROM contrataciones WHERE id_contratacion = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Contrataciones(
                            rs.getInt("id_contratacion"),
                            rs.getInt("id_empleado"),
                            rs.getInt("id_departamento"),
                            rs.getInt("id_tipo_contratacion"),
                            rs.getInt("id_cargo"),
                            rs.getString("fecha_contratacion"),
                            rs.getDouble("salario"),
                            rs.getBoolean("estado")
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al buscar la contratación por ID", ex);
        }
    }

    @Override
    public int contarContrataciones() {
        String query = "SELECT COUNT(*) FROM contrataciones;";
        int contador = 0;

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                contador = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al contar las contrataciones", ex);
        }

        return contador;
    }


}
