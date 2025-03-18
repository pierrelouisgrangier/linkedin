package com.raphlys.common;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudService<D extends IDto<K>, M extends IModel<K>, K> {

	@Autowired
	private ConverterDtoModel<M, D, K> converter;

	@Autowired
	private JpaRepository<M, K> repository;

	protected JpaRepository<M, K> getRepository() {
		return repository;
	}

	public List<D> findAll() {
		return toDtos(repository.findAll());
	}

	public D get(K id) {
		return toDto(repository.findById(id).orElse(null), getPropertiesReadDto());
	}

	public boolean delete(K id) {
		repository.deleteById(id);
		return true;
	}

	public D create(D dto) {
		return toDto(repository.save(toModel(dto, getPropertiesModel())), getPropertiesCreateDto());
	}

	public D update(D dto) {
		return toDto(repository.save(toModel(dto, getPropertiesModel())), getPropertiesUpdateDto());
	}

	public List<Class<?>> getPropertiesUpdateDto() {
		return Collections.emptyList();
	}

	public List<Class<?>> getPropertiesCreateDto() {
		return Collections.emptyList();
	}

	public List<Class<?>> getPropertiesReadDto() {
		return Collections.emptyList();
	}

	public List<Class<?>> getPropertiesModel() {
		return Collections.emptyList();
	}

	protected D toDto(M model, List<Class<?>> classes) {
		return converter.toDto(model, classes);
	}

	protected M toModel(D model, List<Class<?>> classes) {
		return converter.toModel(model, classes);
	}

	protected D toDto(M model) {
		return converter.toDto(model);
	}

	protected M toModel(D model) {
		return converter.toModel(model);
	}

	protected List<D> toDtos(List<M> models) {
		return converter.toDtos(models);
	}

	protected List<M> toModels(List<D> dtos) {
		return converter.toModels(dtos);
	}

}
