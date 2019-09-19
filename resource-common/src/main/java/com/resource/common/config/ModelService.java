package com.resource.common.config;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ModelService<T> {

	public T create(T model);
    public List<T> get();
    public Page<T> get(Pageable page);
    public T findById(int id);
    public T update(T user);
    public void delete(int id);
}
