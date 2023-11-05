package br.com.ldnovaes.daos.generic;

import java.util.List;

import javax.inject.Inject;

import br.com.ldnovaes.models.IModel;
import jakarta.persistence.EntityManager;

public abstract class GenericDAO<T extends IModel> implements IGenericDAO<T> {

	private Class<T> modelClass;

	@Inject
	EntityManager entityManager;

	public GenericDAO(Class<T> modelClass) {
		this.modelClass = modelClass;
	}

	@Override
	public void persistir(T model) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(model);
		this.entityManager.getTransaction().commit();

	}
	
	@Override
	public void editar(T model) {
		System.out.println(model);
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(model);
		this.entityManager.getTransaction().commit();
	}

	@Override
	public void deletar(T model) {
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(model);
		this.entityManager.getTransaction().commit();
	}

	@Override
	public List<T> buscarTodos() {
		this.entityManager.getTransaction().begin();
		List<T> lista = this.entityManager.createQuery(getSelectSql(), this.modelClass).getResultList();
		this.entityManager.getTransaction().commit();
		return lista;

	}

	private String getSelectSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT obj FROM ");
		sb.append(this.modelClass.getSimpleName());
		sb.append(" obj");
		return sb.toString();
	}

}
