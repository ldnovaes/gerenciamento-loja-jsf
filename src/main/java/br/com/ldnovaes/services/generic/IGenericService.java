package br.com.ldnovaes.services.generic;

import java.util.List;

import br.com.ldnovaes.models.IModel;

public interface IGenericService<T extends IModel> {
	
	public void persistir(T model);
	public void editar(T model);
	public void deletar(T model);
	
	public List<T> buscarTodos();


}
