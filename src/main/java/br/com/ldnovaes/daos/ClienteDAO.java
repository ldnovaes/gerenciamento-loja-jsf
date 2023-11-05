package br.com.ldnovaes.daos;

import javax.enterprise.context.RequestScoped;

import br.com.ldnovaes.daos.generic.GenericDAO;
import br.com.ldnovaes.models.Cliente;

@RequestScoped
public class ClienteDAO extends GenericDAO<Cliente> implements IClienteDAO {

	public ClienteDAO() {
		super(Cliente.class);
	}
	
}
