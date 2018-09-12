package hr.crm.studentproject.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.crm.studentproject.model.RootEntity;

public interface Service<T extends RootEntity> {
	public List<T> getAll();
	public T getById(int entityId);
	public T save(T entity);
	public T update(T entity);
	public T delete(int entityId);
	public JpaRepository<T, Integer> getRepository();
}
