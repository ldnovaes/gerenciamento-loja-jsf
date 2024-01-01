package br.com.ldnovaes.dao;

import java.lang.reflect.Field;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import br.com.ldnovaes.annotation.BuscaPersonalizada;
import br.com.ldnovaes.dao.generic.GenericDAO;
import br.com.ldnovaes.exception.NaoEncontradoBancoDados;
import br.com.ldnovaes.model.Cliente;

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
	
	@Override
	public List<Cliente> listarPorNome(String entradaUsuario)
			throws NoSuchFieldException, SecurityException, NaoEncontradoBancoDados {

		Field field = Cliente.class.getDeclaredField("nome");
		
		if (field.isAnnotationPresent(BuscaPersonalizada.class)) {
			BuscaPersonalizada annotation = field.getAnnotation(BuscaPersonalizada.class);
			String jpqlQuery = annotation.query().replace(":input", "'" + entradaUsuario + "%'");
			this.entityManager.getTransaction().begin();
			List<Cliente> lista = this.entityManager.createQuery(jpqlQuery, Cliente.class).getResultList();
			this.entityManager.getTransaction().commit();
			
			return lista;
			
		} else {
			throw new NaoEncontradoBancoDados("Não foi encontrado nenhum cliente com o nome referente a pesquisa!"); 
		}
	}
	
}
