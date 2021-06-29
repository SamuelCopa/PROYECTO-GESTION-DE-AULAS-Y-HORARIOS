package com.emergentes.dao;

import com.emergentes.modelo.Materia;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAOimpl extends ConexionDB implements MateriaDAO {

    @Override
    public void insert(Materia materia) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO materia (id,nombre,sigla, nivel) values (?,?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, materia.getId());
            ps.setString(2, materia.getNombre());
            ps.setString(3, materia.getSigla());
            ps.setInt(4, materia.getNivel());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Materia materia) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE materia SET nombre = ?, sigla = ?, nivel = ? WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setString(1, materia.getNombre());
            ps.setString(2, materia.getSigla());
            ps.setInt(3, materia.getNivel());
            ps.setInt(4, materia.getId());
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
            String sql = "DELETE FROM materia WHERE id = ?";
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
    public Materia getById(int id) throws Exception {
        Materia mat = new Materia();
        try {
            this.conectar();
            String sql = "SELECT * FROM materia WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mat.setId(rs.getInt("id"));
                mat.setNombre(rs.getString("nombre"));
                mat.setSigla(rs.getString("sigla"));
                mat.setNivel(rs.getInt("nivel"));

            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return mat;
    }

    @Override
    public List<Materia> getAll() throws Exception {
        List<Materia> lista = null;
        try {
            this.conectar();
          
            String sql = "SELECT * FROM materia ORDER BY nivel, nombre";

            PreparedStatement ps = this.conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Materia>();
            while (rs.next()) {
                Materia mat = new Materia();

                mat.setId(rs.getInt("id"));
                mat.setNombre(rs.getString("nombre"));
                mat.setSigla(rs.getString("sigla"));
                mat.setNivel(rs.getInt("nivel"));
   

                lista.add(mat);
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
