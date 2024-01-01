package br.com.ldnovaes.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ldnovaes.dao.IClienteDAO;
import br.com.ldnovaes.exception.NaoEncontradoBancoDados;
import br.com.ldnovaes.model.Cliente;
import br.com.ldnovaes.service.generic.GenericService;

@RequestScoped
public class ClienteService extends GenericService<Cliente> implements IClienteService {
	
	@Inject
	public ClienteService(IClienteDAO dao) {
		super(dao);
	}
	
	@Override
	public Cliente buscarPorNome(String nome) {
		String nomeFormatado = nome.strip();
		return ((IClienteDAO) this.dao).buscarPorNome(nomeFormatado);
	}

	@Override
	public List<Cliente> listarPorNome(String query) throws NoSuchFieldException, SecurityException, NaoEncontradoBancoDados {
		return ((IClienteDAO) this.dao).listarPorNome(query);
	}


}
