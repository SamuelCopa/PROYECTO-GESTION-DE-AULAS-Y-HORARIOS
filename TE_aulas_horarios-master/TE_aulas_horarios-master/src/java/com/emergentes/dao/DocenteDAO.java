package com.emergentes.dao;

import com.emergentes.modelo.Docente;
import java.util.List;

public interface DocenteDAO {    
    public void insert(Docente docente) throws Exception;
    public void update(Docente docente) throws Exception;
    public void delete(int id) throws Exception;
    public Docente getById(int id) throws Exception;
    public List<Docente> getAll() throws Exception;  
}
