package com.raphlys.common;

import java.util.Collection;
import java.util.List;

public abstract class ConverterDtoModel<M extends IModel<K>, D extends IDto<K>, K> {

	private final Class<D> dtoClass;
	private final Class<M> modelClass;

	public ConverterDtoModel(Class<D> dtoClass, Class<M> modelClass) {
		this.dtoClass = dtoClass;
		this.modelClass = modelClass;
	}

	protected abstract D internalToDto(M model, List<Class<?>> classes);

	protected abstract M internalToModel(D dto, List<Class<?>> classes);

	public final D toDto(M model, List<Class<?>> classes) {
		return internalToDto(model, classes.stream().filter(cla -> !cla.equals(this.dtoClass)).toList());
	}

	public final M toModel(D dto, List<Class<?>> classes) {
		return internalToModel(dto, classes.stream().filter(cla -> !cla.equals(this.modelClass)).toList());

	}

	public final D toDto(M model) {
		if (model == null) {
			return null;
		}
		return toDto(model, List.of());
	}

	public final M toModel(D dto) {
		if (dto == null) {
			return null;
		}
		return toModel(dto, List.of());
	}

	public List<D> toDtos(Collection<M> models, List<Class<?>> classes) {
		return models.stream().map(m -> toDto(m, classes)).toList();
	}

	public List<M> toModels(Collection<D> dtos, List<Class<?>> classes) {
		return dtos.stream().map(m -> toModel(m, classes)).toList();
	}

	public List<D> toDtos(Collection<M> models) {
		return toDtos(models, List.of());
	}

	public List<M> toModels(Collection<D> dtos) {
		return toModels(dtos, List.of());
	}
}
