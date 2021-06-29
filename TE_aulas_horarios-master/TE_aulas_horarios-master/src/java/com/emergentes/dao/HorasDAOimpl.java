package com.emergentes.dao;

import com.emergentes.utiles.ConexionDB;
import com.emergentes.modelo.Horas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HorasDAOimpl extends ConexionDB implements HorasDAO {

    @Override
    public void insert(Horas objeto) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO horas(rango) VALUES (?)");
            ps.setString(1, objeto.getRango());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Horas objeto) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE horas SET rango=? WHERE id=?");
            ps.setString(1, objeto.getRango());
            ps.setInt(2, objeto.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM horas WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Horas getById(int id) throws Exception {
        Horas objeto = new Horas();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM horas where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                objeto.setId(rs.getInt("id"));
                objeto.setRango(rs.getString("rango"));

            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return objeto;
    }

    @Override
    public List<Horas> getAll() throws Exception {
        List<Horas> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM horas");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Horas>();
            while (rs.next()) {
                Horas objeto = new Horas();
                objeto.setId(rs.getInt("id"));
                objeto.setRango(rs.getString("rango"));
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
