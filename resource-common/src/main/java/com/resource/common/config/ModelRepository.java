package com.resource.common.config;

import java.util.List;

public interface ModelRepository<T> {

	public void create(T model);
    public List<T> get(Integer page, Integer size);
    public List<T> getAll();
    public T findById(int id);
    public T update(T user, int id);
    public void delete(int id);
}
