package br.com.ldnovaes.dao.generic;

import java.util.List;

import javax.inject.Inject;

import br.com.ldnovaes.model.Persistente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
* <p>DAO Genérico</p>
* <p>
* Essa classe é abstrata e genérica e implementa os métodos de <strong>{@link IGenericDAO}</strong>.
* não havendo necessidade de recriar os mesmos métodos e repetí-los para cada DAO caso essa classe seja extendida. 
* </p>
* 
* <p>Dessa forma, cada classe DAO terá responsabilidade exclusiva por seu modelo/registro, deixando de se preocupar com responsabilidades que são comuns entre os DAOs</p>
* 
* @see br.com.ldnovaes.dao.generic.IGenericDAO
* @see br.com.ldnovaes.model.Persistente
* @author ldnovaes
*/
public abstract class GenericDAO<T extends Persistente> implements IGenericDAO<T> {

	private Class<T> modelClass;

	@Inject
	protected EntityManager entityManager;
	
	/**
	 * <p>Construtor da classe.</p>
	 * <p>A classe filha deve passar o tipo da classe de registro utilizada pelo DAO.</p>
	 * 
	 * <p>Exemplo de uso para criação de um novo DAO com extensão de <strong>GenericDAO</strong>:</p>
	 * 
	 * <pre>
	 *  public class LicitacaoDAO extends GenericDAO&lt;Licitacao&gt; implements ILicitacaoDAO {
	 *      public LicitacaoDAO() {
	 *          super(Item.class);
	 *      }
	 *  }
	 * </pre>
	 * 
	 * @param modelClass O tipo da classe de registro utilizada pelo DAO.
	 */
	public GenericDAO(Class<T> modelClass) {
		this.modelClass = modelClass;
	}
	
	@Override
	public void persistir(T model) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(model);
		this.entityManager.getTransaction().commit();
	}

	@Override
	public void editar(T model) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(model);
		this.entityManager.getTransaction().commit();
	}
	
	@Override
	public void deletar(T model) {
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(model);
		this.entityManager.getTransaction().commit();
	}
	
	@Override
	public List<T> buscarTodos() {
		this.entityManager.getTransaction().begin();
		List<T> lista = this.entityManager.createQuery(getSelectAllJQL(), this.modelClass).getResultList();
		this.entityManager.getTransaction().commit();
		return lista;

	}
	
	@Override
	public List<T> buscarPaginada(int paginaAtual, int tamanhoPagina) {
		TypedQuery<T> query = this.entityManager.createQuery(this.getSelectAllJQL(), this.modelClass);
		
        query.setFirstResult(paginaAtual);
        query.setMaxResults(tamanhoPagina);

        List<T> resultados = query.getResultList();
        return resultados;
	}

	@Override
	public String getSelectAllJQL() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT obj FROM ");
		sb.append(this.modelClass.getSimpleName());
		sb.append(" obj");
		return sb.toString();
	}
	
	@Override
	public int getQuantidadeTotalRegistro() {
		 TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(e) FROM " + this.modelClass.getSimpleName() + " e", Long.class);
	     return query.getSingleResult().intValue();
	}

}
