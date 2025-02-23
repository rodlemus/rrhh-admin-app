package com.udb.rrhhadminapp.database.repositorios;

import com.udb.rrhhadminapp.database.ConexionBaseDeDatos;
import com.udb.rrhhadminapp.database.modelos.Cargo;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CargoRepositorio implements ICargoRepositorio {

    // Metodo para mostrar los cargos desde la tabla cargos en la base
    @Override
    public List<Cargo> listarCargos(int offset, int limit) {
        String query = "SELECT * FROM cargos ORDER BY id ASC LIMIT ? OFFSET ?;";
        List<Cargo> cargos = new ArrayList<>();

        try (Connection conn = ConexionBaseDeDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cargos.add(new Cargo(
                        rs.getInt("id"),
                        rs.getString("cargo"),
                        rs.getString("descripcionCargo"),
                        rs.getBoolean("jefatura")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al listar los cargos disponibles" ,ex);
        }

        return cargos;
    }

    @Override
    public List<Cargo> listarCargos() {
        String query = "SELECT * FROM cargos;";
        List<Cargo> cargos = new ArrayList<>();

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cargos.add(new Cargo(
                        rs.getInt("id"),
                        rs.getString("cargo"),
                        rs.getString("descripcionCargo"),
                        rs.getBoolean("jefatura")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al listar los cargos disponibles" ,ex);
        }

        return cargos;
    }

    @Override
    public Cargo agregarCargo(Cargo cargo){
        String query = "INSERT INTO cargos(cargo, descripcionCargo, jefatura) values(?,?,?);";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, cargo.getCargo());
            ps.setString(2, cargo.getDescripcionCargo());
            ps.setBoolean(3, cargo.isJefatura());
            ps.executeUpdate();
            return cargo;
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException("Error al agregar cargo" ,ex);
        }
    }

    @Override
    public void actualizarCargo(Cargo cargo) {
        String query = "UPDATE cargos SET cargo = ? , descripcionCargo = ?, jefatura = ? WHERE id = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, cargo.getCargo());
            ps.setString(2, cargo.getDescripcionCargo());
            ps.setBoolean(3, cargo.isJefatura());
            ps.setInt(4, cargo.getIdCargo());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Error: No se pudo encontrar el cargo con ID: " + cargo.getIdCargo());
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al actualizar cargo" ,ex);
        }
    }

    @Override
    public void eliminarCargo(Integer id) {
        String query = "DELETE FROM cargos WHERE id = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al eliminar cargo" ,ex);
        }
    }

    @Override
    public Cargo buscarCargoPorId(Integer id) {
        String query = "SELECT * FROM cargos WHERE id = ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Cargo cargos = new Cargo(
                            rs.getInt("id"),
                            rs.getString("cargo"),
                            rs.getString("descripcionCargo"),
                            rs.getBoolean("jefatura")
                    );

                    return cargos;
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
    public int contarCargos() {
        String query = "SELECT COUNT(*) FROM cargos;";
        int contador = 0;

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if(rs.next()) {
                contador = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al contar el cargos" ,ex);
        }
        return contador;
    }

    @Override
    public List<Cargo> buscarCargoPorNombre(String buscador) {
        List<Cargo> cargos = new ArrayList<>();
        String query = "SELECT * FROM cargos WHERE cargo LIKE ? OR descripcionCargo LIKE ?;";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, "%" + buscador + "%");
            ps.setString(2, "%" + buscador + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("id"));
                cargo.setCargo(rs.getString("cargo"));
                cargo.setDescripcionCargo(rs.getString("descripcionCargo"));
                cargo.setJefatura(rs.getBoolean("jefatura"));

                cargos.add(cargo);
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return cargos;
    }
}
