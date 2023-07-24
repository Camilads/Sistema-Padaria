package com.ifpe.edu.paokentin.model.repositories;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository <T, I>{

    public void create(T t) throws SQLException;
    public void update(T t) throws SQLException;
    public T read(I key) throws SQLException;
    public void delete(I key) throws SQLException;
    public List<T> readAll() throws SQLException;
}
