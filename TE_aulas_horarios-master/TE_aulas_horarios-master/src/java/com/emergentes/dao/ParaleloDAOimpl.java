package com.emergentes.dao;

import com.emergentes.modelo.Paralelo;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ParaleloDAOimpl extends ConexionDB implements ParaleloDAO {

    @Override
    public void insert(Paralelo paralelo) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO paralelo (id,nombre_paralelo) values (?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, paralelo.getId());
            ps.setString(2, paralelo.getNombre_paralelo());

            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Paralelo paralelo) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE paralelo SET nombre_paralelo=?  WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setString(1, paralelo.getNombre_paralelo());
            ps.setInt(2, paralelo.getId());
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
            String sql = "DELETE FROM paralelo WHERE id = ?";
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
    public Paralelo getById(int id) throws Exception {
        Paralelo pa = new Paralelo();

        try {
            this.conectar();
            String sql = "SELECT * FROM paralelo WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pa.setId(rs.getInt("id"));
                pa.setNombre_paralelo(rs.getString("nombre_paralelo"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return pa;
    }

    @Override
    public List<Paralelo> getAll() throws Exception {
        List<Paralelo> lista = null;
        try {
            this.conectar();

            String sql = "SELECT * FROM paralelo";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Paralelo>();
            while (rs.next()) {
                Paralelo pa = new Paralelo();

                pa.setId(rs.getInt("id"));
                pa.setNombre_paralelo(rs.getString("nombre_paralelo"));

                lista.add(pa);
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
