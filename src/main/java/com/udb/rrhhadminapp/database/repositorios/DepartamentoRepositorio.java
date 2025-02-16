package com.udb.rrhhadminapp.database.repositorios;

import com.udb.rrhhadminapp.database.ConexionBaseDeDatos;
import com.udb.rrhhadminapp.database.modelos.Departamento;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DepartamentoRepositorio implements IDepartamentoRepositorio{

    // Metodo para mostrar los cargos desde la tabla cargos en la base
    @Override
    public List<Departamento> listarDepartamentos(int offset, int limit) {
        String query = "SELECT * FROM departamento ORDER BY id ASC LIMIT ? OFFSET ?;";
        List<Departamento> departamento = new ArrayList<>();

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                departamento.add(new Departamento(
                        rs.getInt("id"),
                        rs.getString("departamento"),
                        rs.getString("descripcionDepartamento")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al listar los departamentos disponibles" ,ex);
        }

        return departamento;
    }

    // Metodo para registrar un nuevo tipo de contrato en la base
    @Override
    public Departamento agregarDepartamento(Departamento departamento){
        String query = "INSERT INTO departamento(departamento, descripcionDepartamento) values(?,?);";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, departamento.getDepartamento());
            ps.setString(2, departamento.getDescripcion());
            ps.executeUpdate();
            return departamento;
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException("Error al agregar departamento" ,ex);
        }
    }

    // Metodo para actualizar un tipo de contrato en la base
    @Override
    public void actualizarDepartamento(Departamento departamento) {
        String query = "UPDATE departamento SET departamento = ?, descripcionDepartamento = ? WHERE id = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, departamento.getDepartamento());
            ps.setString(2, departamento.getDescripcion());
            ps.setInt(3, departamento.getIdDepartamento());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Error: No se pudo encontrar el departamento con ID: " + departamento.getIdDepartamento());
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al actualizar el departamento" ,ex);
        }
    }

    // Metodo para elimianr un tipo de contrato en la base
    @Override
    public void eliminarDepartamento(Integer id) {
        String query = "DELETE FROM departamento WHERE id = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al eliminar departamento" ,ex);
        }
    }

    // Metodo para buscar un tipo de contrato segun su id en la base
    @Override
    public Departamento buscarDepartamentoPorId(Integer id) {
        String query = "SELECT * FROM departamento WHERE id = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Departamento departamento = new Departamento(
                            rs.getInt("id"),
                            rs.getString("departamento"),
                            rs.getString("descripcionDepartamento")
                    );

                    return departamento;
                } else {
                    return null;
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al buscar departamento" ,ex);
        }
    }
}
