package com.resource.common.service;

public interface ModelService<T> {
	
	public T create(T model);
    public T findById(int id);
    public T update(T model);
    public void delete(int id);

}
