package com.emergentes.dao;

import com.emergentes.modelo.MatParalelo;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sammy
 */
public class MatParaleloDAOimpl extends ConexionDB implements MatParaleloDAO {

    @Override
    public void insert(MatParalelo matparalelo) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO mat_par (id_materia,id_paralelo) values (?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            //ps.setInt(1, matparalelo.getId());
            ps.setInt(1, matparalelo.getId_materia());
            ps.setInt(2, matparalelo.getId_paralelo());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(MatParalelo matparalelo) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE mat_par SET id_materia=?, id_paralelo=? WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, matparalelo.getId_materia());
            ps.setInt(2, matparalelo.getId_paralelo());
            ps.setInt(3, matparalelo.getId());
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
            String sql = "DELETE FROM mat_par WHERE id = ?";
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
    public MatParalelo getById(int id) throws Exception {
        MatParalelo mp = new MatParalelo();

        try {
            this.conectar();
            String sql = "SELECT * FROM mat_par WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mp.setId(rs.getInt("id"));
                mp.setId_materia(rs.getInt("id_materia"));
                mp.setId_paralelo(rs.getInt("id_paralelo"));
      

            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return mp;
    }

    @Override
    public List<MatParalelo> getAll() throws Exception {
        List<MatParalelo> lista = null;
        try {
            this.conectar();

            String sql = "select mp.*, concat('Nivel ',m.nivel,' - ', m.nombre,' (',m.sigla,') ',' - Par. ',p.nombre_paralelo) as mat_par ";
            sql += "from materia m, paralelo p, mat_par mp ";
            sql += "where m.id=mp.id_materia AND p.id=mp.id_paralelo ";
            sql += "ORDER BY m.nivel, m.nombre, p.nombre_paralelo ";
           
           //String sql="select mp.*, concat( m.nombre,' - Par. ',p.nombre_paralelo) as mat_par from materia m, paralelo p, mat_par mp where m.id=mp.id_materia AND p.id=mp.id_paralelo";

            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<MatParalelo>();
            while (rs.next()) {
                MatParalelo mp = new MatParalelo();

                mp.setId(rs.getInt("id"));
                mp.setId_materia(rs.getInt("id_materia"));
                mp.setId_paralelo(rs.getInt("id_paralelo"));
                mp.setMat_par(rs.getString("mat_par"));

                lista.add(mp);
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
