package com.resource.common.config;

import java.util.List;

public interface ModelRepository<T> {

	public void create(T model);
    public List<T> get();
    public T findById(int id);
    public T update(T user, int id);
    public void delete(int id);
}
