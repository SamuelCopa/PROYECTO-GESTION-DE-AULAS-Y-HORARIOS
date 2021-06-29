package com.emergentes.dao;

import com.emergentes.modelo.CatUsuario;
import java.util.List;

public interface CatUsuarioDAO {

    public void insert(CatUsuario objeto) throws Exception;

    public void update(CatUsuario objeto) throws Exception;

    public void delete(int id) throws Exception;

    public CatUsuario getById(int id) throws Exception;

    public List<CatUsuario> getAll() throws Exception;

}
