package com.emergentes.dao;

import com.emergentes.utiles.ConexionDB;
import com.emergentes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOimpl extends ConexionDB implements UsuarioDAO {

    @Override
    public void insert(Usuario objeto) throws Exception {

        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("insert into usuario (usuario, password, id_cat_usuario) values (?,?,?);");
            ps.setString(1, objeto.getUsuario());
            ps.setString(2, objeto.getPassword());
            ps.setInt(3, objeto.getId_cat_usuario());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void update(Usuario objeto) throws Exception {

        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE usuario SET usuario=?, password=?, id_cat_usuario=? WHERE id=?");
            ps.setString(1, objeto.getUsuario());
            ps.setString(2, objeto.getPassword());
            ps.setInt(3, objeto.getId_cat_usuario());
            ps.setInt(4, objeto.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void update_pas(int id, String pas) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps=this.conn.prepareStatement("UPDATE usuario SET password=? WHERE id=?");;
            ps.setString(1, pas);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
            this.desconectar();
        }
        
        
    }

    @Override
    public void delete(int id) throws Exception {

        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("delete from usuario where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public List<Usuario> verifica_usuario2(String us, String pas) throws Exception {

        List<Usuario> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuario where usuario=? and password=?");
            ps.setString(1, us);
            ps.setString(2, pas);

            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Usuario>();
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id"));
                usu.setUsuario(rs.getString("usuario"));
                usu.setPassword(rs.getString("password"));
                usu.setId_cat_usuario(rs.getInt("id_cat_usuario"));
                lista.add(usu);
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

    @Override
    public Usuario getById(int id) throws Exception {

        Usuario usu = new Usuario();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuario where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usu.setId(rs.getInt("id"));
                usu.setUsuario(rs.getString("usuario"));
                usu.setPassword(rs.getString("password"));
                usu.setId_cat_usuario(rs.getInt("id_cat_usuario"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return usu;

    }

    @Override
    public List<Usuario> getAll() throws Exception {
        List<Usuario> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Usuario>();
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id"));
                usu.setUsuario(rs.getString("usuario"));
                usu.setPassword(rs.getString("password"));
                usu.setId_cat_usuario(rs.getInt("id_cat_usuario"));
                lista.add(usu);
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
