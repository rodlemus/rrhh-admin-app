package com.udb.rrhhadminapp.database.repositorios;

import com.udb.rrhhadminapp.database.ConexionBaseDeDatos;
import com.udb.rrhhadminapp.database.modelos.TipoContratacion;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TipoContratoRepositorio implements ITipoContratoRepositorio{

    // Metodo para mostrar los cargos desde la tabla cargos en la base
    @Override
    public List<TipoContratacion> listarTiposContratos(int offset, int limit) {
        String query = "SELECT * FROM tipo_contratacion ORDER BY id ASC LIMIT ? OFFSET ?;";
        List<TipoContratacion> tiposC = new ArrayList<>();

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tiposC.add(new TipoContratacion(
                        rs.getInt("id"),
                        rs.getString("tipoContratacion")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al listar los tipos de contratos disponibles" ,ex);
        }

        return tiposC;
    }

    // Metodo para registrar un nuevo tipo de contrato en la base
    @Override
    public TipoContratacion agregarTipoC(TipoContratacion tipoC){
        String query = "INSERT INTO tipo_contratacion(tipoContratacion) values(?);";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, tipoC.getTipoC());
            ps.executeUpdate();
            return tipoC;
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException("Error al agregar cargo" ,ex);
        }
    }

    // Metodo para actualizar un tipo de contrato en la base
    @Override
    public void actualizarTiposContratos(TipoContratacion tipoC) {
        String query = "UPDATE tipo_contratacion SET tipoContratacion = ? WHERE id = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, tipoC.getTipoC());
            ps.setInt(2, tipoC.getIdTipoC());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Error: No se pudo encontrar el cargo con ID: " + tipoC.getIdTipoC());
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al actualizar cargo" ,ex);
        }
    }

    // Metodo para elimianr un tipo de contrato en la base
    @Override
    public void eliminarTiposContratos(Integer id) {
        String query = "DELETE FROM tipo_contratacion WHERE id = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al eliminar cargo" ,ex);
        }
    }

    // Metodo para buscar un tipo de contrato segun su id en la base
    @Override
    public TipoContratacion buscarTipoContratoPorId(Integer id) {
        String query = "SELECT * FROM tipo_contratacion WHERE id = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    TipoContratacion tipoC = new TipoContratacion(
                            rs.getInt("id"),
                            rs.getString("tipoContratacion")
                    );

                    return tipoC;
                } else {
                    return null;
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al buscar cargo" ,ex);
        }
    }

    @Override
    public int contarTiposContratos() {
        String query = "SELECT COUNT(*) FROM tipo_contratacion;";
        int contador = 0;

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if(rs.next()) {
                contador = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al contar el tipo de contratacion" ,ex);
        }
        return contador;
    }
}
