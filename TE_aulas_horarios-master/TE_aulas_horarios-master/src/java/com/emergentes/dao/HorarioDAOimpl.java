package com.emergentes.dao;

import com.emergentes.utiles.ConexionDB;
import com.emergentes.modelo.Horario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HorarioDAOimpl extends ConexionDB implements HorarioDAO {

    @Override
    public void insert(Horario objeto) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO horario(gestion, turno, dia, id_horas, id_aula, id_mat_par, id_docente) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, objeto.getGestion());
            ps.setString(2, objeto.getTurno());
            ps.setString(3, objeto.getDia());
            ps.setInt(4, objeto.getId_horas());
            ps.setInt(5, objeto.getId_aula());
            ps.setInt(6, objeto.getId_mat_par());
            ps.setInt(7, objeto.getId_docente());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Horario objeto) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE horario SET gestion=?, turno=?,dia=?,id_horas=?,id_aula=?,id_mat_par=?, id_docente=? WHERE id=?");
            ps.setString(1, objeto.getGestion());
            ps.setString(2, objeto.getTurno());
            ps.setString(3, objeto.getDia());
            ps.setInt(4, objeto.getId_horas());
            ps.setInt(5, objeto.getId_aula());
            ps.setInt(6, objeto.getId_mat_par());
            ps.setInt(7, objeto.getId_docente());
            ps.setInt(8, objeto.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM horario WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Horario getById(int id) throws Exception {
        Horario objeto = new Horario();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM horario where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                objeto.setId(rs.getInt("id"));
                objeto.setGestion(rs.getString("gestion"));
                objeto.setTurno(rs.getString("turno"));
                objeto.setDia(rs.getString("dia"));
                objeto.setId_horas(rs.getInt("id_horas"));
                objeto.setId_aula(rs.getInt("id_aula"));
                objeto.setId_mat_par(rs.getInt("id_mat_par"));
                objeto.setId_docente(rs.getInt("id_docente"));

            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return objeto;
    }

    @Override
    public List<Horario> getAll() throws Exception {
        List<Horario> lista = null;
        try {
            this.conectar();
            String sql = "SELECT h.*, m.nivel,m.nombre as nom_materia, m.sigla,p.nombre_paralelo as paralelo, d.apellidos as ap_docente, d.nombre as nom_docente, concat(d.apellidos,' ',d.nombre) as docente, concat(m.nombre,' (',m.sigla,')-Par. ',p.nombre_paralelo) as mat_par ";
            sql += "FROM materia m, paralelo p, mat_par mp, horario h, docente d ";
            sql += "where m.id=mp.id_materia and p.id=mp.id_paralelo and mp.id=h.id_mat_par and d.id=h.id_docente ";
            sql += "ORDER BY m.nivel, p.nombre_paralelo, m.nombre, h.turno ";

            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Horario>();
            while (rs.next()) {
                Horario objeto = new Horario();
                objeto.setId(rs.getInt("id"));
                objeto.setGestion(rs.getString("gestion"));
                objeto.setTurno(rs.getString("turno"));
                objeto.setDia(rs.getString("dia"));
                objeto.setId_horas(rs.getInt("id_horas"));
                objeto.setId_aula(rs.getInt("id_aula"));
                objeto.setId_mat_par(rs.getInt("id_mat_par"));
                objeto.setId_docente(rs.getInt("id_docente"));
                objeto.setNivel(rs.getInt("nivel"));
                objeto.setNom_materia(rs.getString("nom_materia"));
                objeto.setSigla(rs.getString("sigla"));
                objeto.setParalelo(rs.getString("paralelo"));
                objeto.setAp_docente(rs.getString("ap_docente"));
                objeto.setNom_docente(rs.getString("nom_docente"));
                objeto.setDocente(rs.getString("docente"));
                objeto.setMat_par(rs.getString("mat_par"));
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

    @Override
    public List<Horario> getAll_dias_horas() throws Exception {
        List<Horario> lista = null;
        try {
            this.conectar();
            String sql = "SELECT h.*, m.nivel,m.nombre as nom_materia, m.sigla,p.nombre_paralelo as paralelo, d.apellidos as ap_docente, d.nombre as nom_docente, concat(d.apellidos,' ',d.nombre) as docente, concat(m.nombre,' (',m.sigla,')-Par. ',p.nombre_paralelo) as mat_par ";
            sql += "FROM materia m, paralelo p, mat_par mp, horario h, docente d ";
            sql += "where m.id=mp.id_materia and p.id=mp.id_paralelo and mp.id=h.id_mat_par and d.id=h.id_docente ";
            sql += "ORDER BY dia, id_horas ";

            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Horario>();
            while (rs.next()) {
                Horario objeto = new Horario();
                objeto.setId(rs.getInt("id"));
                objeto.setGestion(rs.getString("gestion"));
                objeto.setTurno(rs.getString("turno"));
                objeto.setDia(rs.getString("dia"));
                objeto.setId_horas(rs.getInt("id_horas"));
                objeto.setId_aula(rs.getInt("id_aula"));
                objeto.setId_mat_par(rs.getInt("id_mat_par"));
                objeto.setId_docente(rs.getInt("id_docente"));
                objeto.setNivel(rs.getInt("nivel"));
                objeto.setNom_materia(rs.getString("nom_materia"));
                objeto.setSigla(rs.getString("sigla"));
                objeto.setParalelo(rs.getString("paralelo"));
                objeto.setAp_docente(rs.getString("ap_docente"));
                objeto.setNom_docente(rs.getString("nom_docente"));
                objeto.setDocente(rs.getString("docente"));
                objeto.setMat_par(rs.getString("mat_par"));
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

    @Override
    public List<Horario> getAll_aulas() throws Exception {
        List<Horario> lista = null;
        try {
            this.conectar();
            String sql = "SELECT h.*, m.nivel,m.nombre as nom_materia, m.sigla,p.nombre_paralelo as paralelo, d.apellidos as ap_docente, d.nombre as nom_docente, concat(d.apellidos,' ',d.nombre) as docente, concat(m.nombre,' (',m.sigla,')-Par. ',p.nombre_paralelo) as mat_par, a.descripcion as nom_aula ";
            sql += "FROM materia m, paralelo p, mat_par mp, horario h, docente d, aulas a ";
            sql += "where m.id=mp.id_materia and p.id=mp.id_paralelo and mp.id=h.id_mat_par and d.id=h.id_docente and a.id=h.id_aula ";
            sql += "ORDER BY nom_aula, h.dia, h.id_horas ";

            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Horario>();
            while (rs.next()) {
                Horario objeto = new Horario();
                objeto.setId(rs.getInt("id"));
                objeto.setGestion(rs.getString("gestion"));
                objeto.setTurno(rs.getString("turno"));
                objeto.setDia(rs.getString("dia"));
                objeto.setId_horas(rs.getInt("id_horas"));
                objeto.setId_aula(rs.getInt("id_aula"));
                objeto.setId_mat_par(rs.getInt("id_mat_par"));
                objeto.setId_docente(rs.getInt("id_docente"));
                objeto.setNivel(rs.getInt("nivel"));
                objeto.setNom_materia(rs.getString("nom_materia"));
                objeto.setSigla(rs.getString("sigla"));
                objeto.setParalelo(rs.getString("paralelo"));
                objeto.setAp_docente(rs.getString("ap_docente"));
                objeto.setNom_docente(rs.getString("nom_docente"));
                objeto.setDocente(rs.getString("docente"));
                objeto.setMat_par(rs.getString("mat_par"));
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

    @Override
    public List<Horario> getAll_docentes() throws Exception {
        List<Horario> lista = null;
        try {
            this.conectar();
            String sql = "SELECT h.*, m.nivel,m.nombre as nom_materia, m.sigla,p.nombre_paralelo as paralelo, d.apellidos as ap_docente, d.nombre as nom_docente, concat(d.apellidos,' ',d.nombre) as docente, concat(m.nombre,' (',m.sigla,')-Par. ',p.nombre_paralelo) as mat_par, a.descripcion ";
            sql += "FROM materia m, paralelo p, mat_par mp, horario h, docente d, aulas a ";
            sql += "where m.id=mp.id_materia and p.id=mp.id_paralelo and mp.id=h.id_mat_par and d.id=h.id_docente and h.id_aula=a.id ";
            sql += "ORDER BY d.apellidos, d.nombre, h.dia, h.id_horas ";

            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Horario>();
            while (rs.next()) {
                Horario objeto = new Horario();
                objeto.setId(rs.getInt("id"));
                objeto.setGestion(rs.getString("gestion"));
                objeto.setTurno(rs.getString("turno"));
                objeto.setDia(rs.getString("dia"));
                objeto.setId_horas(rs.getInt("id_horas"));
                objeto.setId_aula(rs.getInt("id_aula"));
                objeto.setId_mat_par(rs.getInt("id_mat_par"));
                objeto.setId_docente(rs.getInt("id_docente"));
                objeto.setNivel(rs.getInt("nivel"));
                objeto.setNom_materia(rs.getString("nom_materia"));
                objeto.setSigla(rs.getString("sigla"));
                objeto.setParalelo(rs.getString("paralelo"));
                objeto.setAp_docente(rs.getString("ap_docente"));
                objeto.setNom_docente(rs.getString("nom_docente"));
                objeto.setDocente(rs.getString("docente"));
                objeto.setMat_par(rs.getString("mat_par"));
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
