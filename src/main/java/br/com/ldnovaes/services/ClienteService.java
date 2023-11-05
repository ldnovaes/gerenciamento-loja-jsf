package br.com.ldnovaes.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ldnovaes.daos.IClienteDAO;
import br.com.ldnovaes.models.Cliente;
import br.com.ldnovaes.services.generic.GenericService;

@RequestScoped
public class ClienteService extends GenericService<Cliente> implements IClienteService{
	
	private IClienteDAO clienteDAO;
	
	@Inject
	public ClienteService(IClienteDAO clienteDAO) {
		super(clienteDAO);
		this.clienteDAO = clienteDAO;
	}


}
