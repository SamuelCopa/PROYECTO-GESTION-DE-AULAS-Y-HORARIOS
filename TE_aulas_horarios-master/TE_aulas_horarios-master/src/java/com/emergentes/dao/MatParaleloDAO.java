package com.emergentes.dao;

import com.emergentes.modelo.MatParalelo;
import java.util.List;

public interface MatParaleloDAO {

public void insert(MatParalelo matparalelo) throws Exception;
    public void update(MatParalelo matparalelo) throws Exception;
    public void delete(int id) throws Exception;
    public MatParalelo getById(int id) throws Exception;
    public List<MatParalelo> getAll() throws Exception;          
}
