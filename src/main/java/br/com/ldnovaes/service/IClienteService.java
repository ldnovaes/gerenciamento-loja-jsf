package br.com.ldnovaes.service;

import java.util.List;

import br.com.ldnovaes.exception.NaoEncontradoBancoDados;
import br.com.ldnovaes.model.Cliente;
import br.com.ldnovaes.service.generic.IGenericService;

public interface IClienteService extends IGenericService<Cliente>{
	
	Cliente buscarPorNome(String nome);

	List<Cliente> listarPorNome(String query) throws NoSuchFieldException, SecurityException, NaoEncontradoBancoDados;
}
