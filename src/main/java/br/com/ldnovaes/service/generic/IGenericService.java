package br.com.ldnovaes.service.generic;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.ldnovaes.model.Persistente;

/**
 * <p>Interface para classe abstrata <strong>{@link GenericService}</strong>.<p>
 * <p>A implementação desta interface é uma classe genérica que poderá ser extendida para todos os <strong>services</strong> que forem futuramente criados.</p> 
 * 
 * @see br.com.ldnovaes.model.Persistente
 * @author ldnovaes
 */
public interface IGenericService<T extends Persistente> {
	
	/**
	 * <p>Chama o DAO correspondente para salvar um novo modelo/registro no banco de dados.</p>
	 * <p>Esse método é responsável por chamar o DAO para persistir um novo registro no banco de dados.</p>
	 * 
	 * @see br.com.ldnovaes.dao.generic.IGenericDAO#persistir(Persistente)
	 * @param model O modelo a ser salvo.
	 */
	public void persistir(T model);
	
	/**
	 * <p>Chama o DAO correspondente para deletar um modelo/registro do banco de dados.</p>
	 * <p>Esse método é responsável por chamar o DAO para deletar um registro do banco de dados.</p>
	 * 
	 * @see br.com.ldnovaes.dao.generic.IGenericDAO#deletar(Persistente)
	 * @param model O modelo a ser salvo.
	 */
	public void deletar(T model);
	
	/**
	 * <p>Chama o DAO correspondente para salvar uma edição de modelo/registro no banco de dados.</p>
	 * <p>Esse método é responsável por chamar o DAO para persistir um registro editado no banco de dados.</p>
	 * 
	 * @see br.com.ldnovaes.dao.generic.IGenericDAO#editar(Persistente)
	 * @param model O modelo a ser salvo.
	 */
	public void editar(T model);
	
	/**
	 * <p>Chama o DAO correspondente para obter todos os registros de um modelo.</p>
	 * <p>Esse método é responsável por chamar o DAO para obter do banco de dados todos os registros do modelo passado para a implementação desta interface.</p>
	 * 
	 * @see br.com.ldnovaes.dao.generic.IGenericDAO#buscarTodos()
	 * @return lista com todos os registros do modelo passado.
	 */
	public List<T> buscarTodos();
	
	/**
	 * <p>Chama o DAO para obter os registros de um registro/modelo <strong>paginados</strong> em intervalos passados pelo usuário.</p>
	 * <p>Esse método é responsável por charmar o DAO por obter do banco de dados os registros por demanda, criando uma paginação dos dados para evitar uma sobrecarga.</p>
	 * 
	 * @see br.com.ldnovaes.dao.generic.IGenericDAO#buscarPaginada(int, int)
	 * @return lista com os registros paginados do modelo passado.
	 */
	public List<T> buscarPaginada(int paginaAtual, int tamanhoPagina);	
	
	/**
	 * <p>Obter objetos passados em <strong>{@link T}</strong> com chaves hash por ID.</p>
	 * <p>Para cada key ID há um objeto do tipo passado em <strong>{@link T}</strong> relacionado ao seu próprio ID</p>
	 * 
	 * @return mapa com a <strong>key</strong> guardando o Id e <strong>value</strong> guardando o objeto passado em <strong>{@link T}</strong>.
	 */
	public <T, K> Map<K, T> getItensEmMap(Function<T, K> keyExtractor);
	
	
	/**
	 * <p>Chama o DAO correspondente para obter a quantidade total de registros inseridos</p>
	 * 
	 * @see br.com.ldnovaes.dao.generic.IGenericDAO#getQuantidadeTotalRegistro()
	 * @return a quantidade total de registros do modelo passado em inteiro
	 */
	public int getQuantidadeTotalRegistros();

}
