package com.emergentes.dao;

import com.emergentes.modelo.Horario;
import java.util.List;

public interface HorarioDAO {

    public void insert(Horario objeto) throws Exception;

    public void update(Horario objeto) throws Exception;

    public void delete(int id) throws Exception;

    public Horario getById(int id) throws Exception;

    public List<Horario> getAll() throws Exception;
    
    public List<Horario> getAll_dias_horas() throws Exception;
    public List<Horario> getAll_aulas() throws Exception;
    public List<Horario> getAll_docentes() throws Exception;

}
