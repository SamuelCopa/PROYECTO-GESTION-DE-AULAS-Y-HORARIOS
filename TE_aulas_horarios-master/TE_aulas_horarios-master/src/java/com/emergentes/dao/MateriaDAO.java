package com.emergentes.dao;

import com.emergentes.modelo.Materia;
import java.util.List;

public interface MateriaDAO {
    
    public void insert(Materia materia) throws Exception;
    public void update(Materia materia) throws Exception;
    public void delete(int id) throws Exception;
    public Materia getById(int id) throws Exception;
    public List<Materia> getAll() throws Exception;  
}
