package br.com.ldnovaes.services.generic;

import java.util.List;

import br.com.ldnovaes.daos.generic.IGenericDAO;
import br.com.ldnovaes.models.IModel;


public abstract class GenericService<T extends IModel> implements IGenericService<T>{
	
	protected IGenericDAO<T> dao;
	
	public GenericService(IGenericDAO<T> dao) { 
		this.dao = dao;
	}

	@Override
	public void persistir(T model) {
		this.dao.persistir(model);
		
	}
	
	@Override
	public void editar(T model) {
		this.dao.editar(model);
	}

	@Override
	public void deletar(T model) {
		this.dao.deletar(model);
	}

	@Override
	public List<T> buscarTodos() {
		return this.dao.buscarTodos();
	}

}
