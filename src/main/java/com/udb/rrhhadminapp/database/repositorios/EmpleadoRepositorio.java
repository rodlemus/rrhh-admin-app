package com.udb.rrhhadminapp.database.repositorios;

import com.udb.rrhhadminapp.database.ConexionBaseDeDatos;
import com.udb.rrhhadminapp.database.modelos.Empleado;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmpleadoRepositorio implements IEmpleadosRepositorio{

    @Override
    public Empleado guardar(Empleado empleado) {
        String sql = "INSERT INTO empleados (numerodui, nombrepersona, usuario, numerotelefono, correoinstitucional, fechanacimiento) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection connection = ConexionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, empleado.getNumeroDui());
            statement.setString(2, empleado.getNombre());
            statement.setString(3, empleado.getUsuario());
            statement.setString(4, empleado.getNumeroTelefono());
            statement.setString(5, empleado.getCorreoInstitucional());
            statement.setDate(6, new java.sql.Date(empleado.getFechaNacimiento().getTime()));

            statement.executeUpdate();
            return empleado;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el empleado", e);
        }
    }

    @Override
    public void eliminar(Integer empleadoId) {
        String sql = "DELETE FROM empleados WHERE id = ?;";

        try (Connection connection = ConexionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, empleadoId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar el empleado con ID: " + empleadoId, e);
        }
    }

    @Override
    public List<Empleado> listar(int offset, int limit) {
        String sql =
                "SELECT * FROM empleados LIMIT ? OFFSET ?;";
        List<Empleado> empleados = new ArrayList<>();

        try (Connection connection = ConexionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, limit);
            statement.setInt(2, offset);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                empleados.add(new Empleado(
                        rs.getInt("id"),
                        rs.getString("numerodui"),
                        rs.getString("nombrepersona"),
                        rs.getString("usuario"),
                        rs.getString("numerotelefono"),
                        rs.getString("correoinstitucional"),
                        rs.getDate("fechanacimiento")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al listar empleados", e);
        }

        return empleados;
    }

    @Override
    public Empleado buscarPorId(Integer id) {
        String sql = "SELECT * FROM empleados WHERE id = ?";

        try (Connection connection = ConexionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Establecer el parámetro 'id' en la consulta
            statement.setInt(1, id);

            // Ejecutar la consulta
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Si encontramos un registro, lo mapeamos a un objeto Empleado
                    Empleado empleado = new Empleado(
                            rs.getInt("id"),
                            rs.getString("numerodui"),
                            rs.getString("nombrepersona"),
                            rs.getString("usuario"),
                            rs.getString("numerotelefono"),
                            rs.getString("correoinstitucional"),
                            rs.getDate("fechanacimiento")
                    );

                    return empleado;
                } else {
                    // Si no se encuentra el empleado, retornamos null
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el empleado", e);
        }
    }


    @Override
    public List<Empleado> buscarPorDui(String dui) {
        String sql = "SELECT * FROM empleados WHERE numerodui LIKE ?";
        List<Empleado> empleados = new ArrayList<>();

        try (Connection connection = ConexionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Establecer el parámetro 'id' en la consulta
            statement.setString(1, dui + "%");

            // Ejecutar la consulta
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                empleados.add(new Empleado(
                        rs.getInt("id"),
                        rs.getString("numerodui"),
                        rs.getString("nombrepersona"),
                        rs.getString("usuario"),
                        rs.getString("numerotelefono"),
                        rs.getString("correoinstitucional"),
                        rs.getDate("fechanacimiento")
                ));
            }

        return empleados;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el empleado", e);
        }
    }

    @Override
    public void actualizar(Empleado empleado) {

    }
}
