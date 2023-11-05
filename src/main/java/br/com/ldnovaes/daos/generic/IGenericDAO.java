package br.com.ldnovaes.daos.generic;

import java.util.List;

import br.com.ldnovaes.models.IModel;

public interface IGenericDAO<T extends IModel> {
	
	public void persistir(T model);
	public void deletar(T model);
	public void editar(T model);
	public List<T> buscarTodos();

}
