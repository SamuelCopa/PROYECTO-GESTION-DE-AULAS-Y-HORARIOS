package com.emergentes.dao;

import com.emergentes.modelo.Horas;
import java.util.List;

public interface HorasDAO {

    public void insert(Horas objeto) throws Exception;

    public void update(Horas objeto) throws Exception;

    public void delete(int id) throws Exception;

    public Horas getById(int id) throws Exception;

    public List<Horas> getAll() throws Exception;

}
