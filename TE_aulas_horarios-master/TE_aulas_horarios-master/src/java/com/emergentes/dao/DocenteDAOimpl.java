package com.emergentes.dao;

import com.emergentes.modelo.Docente;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAOimpl extends ConexionDB implements DocenteDAO {

    @Override
    public void insert(Docente docente) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO docente (id,nombre,apellidos, grado_estudio) values (?,?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, docente.getId());
            ps.setString(2, docente.getNombre());
            ps.setString(3, docente.getApellidos());
            ps.setString(4, docente.getGrado_estudio());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Docente docente) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE docente SET nombre = ?, apellidos = ?, grado_estudio = ? WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setString(1, docente.getNombre());
            ps.setString(2, docente.getApellidos());
            ps.setString(3, docente.getGrado_estudio());
            ps.setInt(4, docente.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM docente WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Docente getById(int id) throws Exception {
        Docente doc = new Docente();
        try {
            this.conectar();
            String sql = "SELECT * FROM docente WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                doc.setId(rs.getInt("id"));
                doc.setNombre(rs.getString("nombre"));
                doc.setApellidos(rs.getString("apellidos"));
                doc.setGrado_estudio(rs.getString("grado_estudio"));

            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return doc;
    }

    @Override
    public List<Docente> getAll() throws Exception {
        List<Docente> lista = null;
        try {
            this.conectar();

            String sql = "SELECT * FROM docente d ORDER BY d.apellidos, d.nombre";

            PreparedStatement ps = this.conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Docente>();
            while (rs.next()) {
                Docente doc = new Docente();

                doc.setId(rs.getInt("id"));
                doc.setNombre(rs.getString("nombre"));
                doc.setApellidos(rs.getString("apellidos"));
                doc.setGrado_estudio(rs.getString("grado_estudio"));

                lista.add(doc);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

}
