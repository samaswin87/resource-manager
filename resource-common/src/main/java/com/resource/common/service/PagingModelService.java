package com.resource.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagingModelService<T> {

	public T create(T model);
    public List<T> get();
    public Page<T> get(Pageable page);
    public T findById(int id);
    public T update(T model);
    public void delete(int id);
}
