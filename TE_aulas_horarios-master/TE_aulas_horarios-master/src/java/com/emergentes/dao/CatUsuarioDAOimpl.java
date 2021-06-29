
package com.emergentes.dao;

import com.emergentes.utiles.ConexionDB;
import com.emergentes.modelo.CatUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CatUsuarioDAOimpl extends ConexionDB implements CatUsuarioDAO {

    @Override
    public void insert(CatUsuario objeto) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO cat_usuario(descripcion) VALUES (?)");
            ps.setString(1, objeto.getDescripcion());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(CatUsuario objeto) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE cat_usuario SET descripcion=? WHERE id=?");
            ps.setString(1, objeto.getDescripcion());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM cat_usuario WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public CatUsuario getById(int id) throws Exception {
        CatUsuario objeto = new CatUsuario();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM cat_usuario where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                objeto.setId(rs.getInt("id"));
                objeto.setDescripcion(rs.getString("descripcion"));

            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return objeto;
    }

    @Override
    public List<CatUsuario> getAll() throws Exception {
        List<CatUsuario> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM cat_usuario");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<CatUsuario>();
            while (rs.next()) {
                CatUsuario objeto = new CatUsuario();
                objeto.setId(rs.getInt("id"));
                objeto.setDescripcion(rs.getString("descripcion"));
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
