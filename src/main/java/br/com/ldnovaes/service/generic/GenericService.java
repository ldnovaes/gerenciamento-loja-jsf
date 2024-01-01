package br.com.ldnovaes.service.generic;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.ldnovaes.dao.IVendaDAO;
import br.com.ldnovaes.dao.generic.IGenericDAO;
import br.com.ldnovaes.model.Persistente;

/**
* <p>Service Genérico</p>
* <p>
* Essa classe é abstrata e genérica e implementa os métodos de <strong>{@link IGenericService}</strong>.
* não havendo necessidade de recriar os mesmos métodos e repetí-los para cada service caso essa classe seja extendida. 
* </p>
* 
* <p>Dessa forma, cada service terá responsabilidade exclusiva por seu modelo/registro, deixando de se preocupar com responsabilidades que são comuns entre os services</p>
* 
* @see br.com.ldnovaes.service.generic.IGenericService
* @see br.com.ldnovaes.model.Persistente
* @author ldnovaes
*/
public abstract class GenericService<T extends Persistente> implements IGenericService<T>{
	
	protected IGenericDAO<T> dao;
	
	/**
	 * <p>Construtor da classe.</p>
	 * <p>Esse construtor obtém o DAO correspondente injetado pelo contêiner web.</p>
	 * <p>O DAO é encontrado pelo contêiner web através do tipo da classe, ou tipo da Interface passada via construtor da implementação.</p>
	 *
	 * <p><strong>Exemplo de criação de um novo service utilizando essa abstração</strong>:</p>
	 * 
	 * <pre>
	 *  public class VendaService extends GenericService&lt;Venda&gt; implements IVenda {
	 *      
	 *      
	 *		public VendaService(IVendaDAO dao) {
	 *			super(dao);
	 *		}
	 *  }
	 * </pre>
	 * 
	 * <p>Acima, podemos notar que para facilitar para o contêiner, foi passado um <strong>{@link IVendaDAO}</strong>, forçando-o a entender qual a implementação buscada.</p>
	 * 
	 * @param dao é o DAO passado via injeção pelo contêiner web.
	 */
	public GenericService(IGenericDAO<T> dao) { 
		this.dao = dao;
	}

	@Override
	public void persistir(T model) {
		this.dao.persistir(model);
		
	}
	
	@Override
	public void editar(T model) {
		this.dao.editar(model);
	}

	@Override
	public void deletar(T model) {
		this.dao.deletar(model);
	}

	@Override
	public List<T> buscarTodos() {
		return this.dao.buscarTodos();
	}
	
	@Override
	public List<T> buscarPaginada(int paginaAtual, int tamanhoPagina) {
		return this.dao.buscarPaginada(paginaAtual, tamanhoPagina);
	}
	
	@Override
	public int getQuantidadeTotalRegistros() {
		return this.dao.getQuantidadeTotalRegistro();
	}
	
	@SuppressWarnings("hiding")
	@Override
	public <T, K> Map<K, T> getItensEmMap(Function<T, K> keyExtractor) {
	    @SuppressWarnings("unchecked")
		List<T> itens = (List<T>) this.dao.buscarTodos();
	    Map<K, T> itensEmMap = itens.stream().collect(Collectors.toMap(keyExtractor, Function.identity()));
	    return itensEmMap;
	}

}
