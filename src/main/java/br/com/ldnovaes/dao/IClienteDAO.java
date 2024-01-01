package br.com.ldnovaes.dao;

import java.util.List;

import br.com.ldnovaes.dao.generic.IGenericDAO;
import br.com.ldnovaes.exception.NaoEncontradoBancoDados;
import br.com.ldnovaes.model.Cliente;

public interface IClienteDAO extends IGenericDAO<Cliente>{
	
	Cliente buscarPorNome(String nome);

	List<Cliente> listarPorNome(String entradaUsuario)
			throws NoSuchFieldException, SecurityException, NaoEncontradoBancoDados;
}
