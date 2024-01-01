package br.com.ldnovaes.daos;

import javax.enterprise.context.RequestScoped;

import br.com.ldnovaes.daos.generic.GenericDAO;
import br.com.ldnovaes.models.Cliente;

@RequestScoped
public class ClienteDAO extends GenericDAO<Cliente> implements IClienteDAO {

	public ClienteDAO() {
		super(Cliente.class);
	}
	
	@Override
	public Cliente buscarPorNome(String nome) {
		return this.entityManager.createQuery("SELECT c FROM Cliente c WHERE c.nome = :nome", Cliente.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
}
