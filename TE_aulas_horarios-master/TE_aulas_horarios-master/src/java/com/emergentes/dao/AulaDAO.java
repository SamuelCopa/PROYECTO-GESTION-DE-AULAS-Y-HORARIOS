package com.emergentes.dao;

import com.emergentes.modelo.Aula;
import java.util.List;

public interface AulaDAO {

    public void insert(Aula objeto) throws Exception;

    public void update(Aula objeto) throws Exception;

    public void delete(int id) throws Exception;

    public Aula getById(int id) throws Exception;

    public List<Aula> getAll() throws Exception;

}
