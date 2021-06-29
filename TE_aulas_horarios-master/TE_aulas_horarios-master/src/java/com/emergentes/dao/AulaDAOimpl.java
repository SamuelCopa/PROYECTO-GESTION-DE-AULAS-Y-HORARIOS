package com.emergentes.dao;

import com.emergentes.utiles.ConexionDB;
import com.emergentes.modelo.Aula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AulaDAOimpl extends ConexionDB implements AulaDAO {

    @Override
    public void insert(Aula objeto) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO aulas(descripcion, ubicacion) VALUES (?,?)");
            ps.setString(1, objeto.getDescripcion());
            ps.setString(2, objeto.getUbicacion());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Aula objeto) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE aulas SET descripcion=?, ubicacion=? WHERE id=?");
            ps.setString(1, objeto.getDescripcion());
            ps.setString(2, objeto.getUbicacion());
            ps.setInt(3, objeto.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM aulas WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Aula getById(int id) throws Exception {
        Aula objeto = new Aula();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM aulas where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                objeto.setId(rs.getInt("id"));
                objeto.setDescripcion(rs.getString("descripcion"));
                objeto.setUbicacion(rs.getString("ubicacion"));

            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return objeto;
    }

    @Override
    public List<Aula> getAll() throws Exception {
        List<Aula> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM aulas");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Aula>();
            while (rs.next()) {
                Aula objeto = new Aula();
                objeto.setId(rs.getInt("id"));
                objeto.setDescripcion(rs.getString("descripcion"));
                objeto.setUbicacion(rs.getString("ubicacion"));
                lista.add(objeto);
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
