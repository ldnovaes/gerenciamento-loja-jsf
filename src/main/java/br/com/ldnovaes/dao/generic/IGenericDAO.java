package br.com.ldnovaes.dao.generic;

import java.util.List;

import br.com.ldnovaes.model.Persistente;

/**
 * <p>Interface para criação de <strong>DAOs</strong> genéricos.</p>
 * <p>A implementação desta interface deve ser uma classe genérica que poderá ser extendida para todos os DAOs que forem futuramente criados.</p> 
 * <p>Aplicando para esses casos de uso, podemos reaproveitar métodos, rotinas e funções que são compartilhadas entre os DAOs.</p>
 * <p>São exemplos do que fora supracitado:</p>
 * <ul>
 * 	<li>Persistência de um registro/modelo</li>
 * 	<li>Deleção de um registro/modelo</li>
 *  <li>Edição de um registro/modelo</li>
 *  <li>Busca de todos os registros/modelos</li>
 *  <li>Busca paginada de registros/modelos</li>
 *  <li>Quantidade de registros em uma tabela</li>
 * </ul>
 * 
 * @see br.com.ldnovaes.model.Persistente
 * @author ldnovaes
 */
public interface IGenericDAO<T extends Persistente> {
	
	/**
	 * <p>Salva um novo modelo/registro no banco de dados.</p>
	 * <p>Esse método é responsável por persistir um <strong>NOVO</strong> registro no banco de dados.</p>
	 * 
	 * @param model o modelo a ser salvo.
	 */
	public void persistir(T model);
	
	/**
	 * <p>Deleta uma modelo/registro do banco de dados.</p>
	 * <p>Esse método é responsável por deletar um registro do banco de dados.</p>
	 * 
	 * @param model o modelo a ser deletado.
	 */
	public void deletar(T model);
	
	/**
	 * <p>Salva uma edição de modelo/registro no banco de dados.</p>
	 * <p>Esse método é responsável por persistir um <strong>REGISTRO EXISTENTE</strong> no banco de dados.</p>
	 * 
	 * @param model o modelo a ser editado.
	 */
	public void editar(T model);
	
	/**
	 * <p>Obtém todos os registros de um registro/modelo.</p>
	 * <p>Esse método é responsável por obter do banco de dados todos os registros do registro/modelo passado para a implementação desta interface.</p>
	 * 
	 * @return lista com todos os registros do modelo passado.
	 */
	public List<T> buscarTodos();
	
	/**
	 * <p>Obtém os registros de um registro/modelo por <strong>paginação</strong> em intervalos passados pelo usuário.</p>
	 * <p>Esse método é responsável por obter do banco de dados os registros por demanda, criando uma paginação dos dados para evitar uma sobrecarga.</p>
	 * 
	 * @return lista com os registros paginados do modelo passado.
	 */
	public List<T> buscarPaginada(int paginaAtual, int tamanhoPagina);
	
	/**
	 * <p>Gera o JPQL para um <strong>SELECT ALL</strong>.</p>
	 * <p>Esse método cria um JPQL para obter todos os registros de um modelo passado para a implementação desta interface.</p>
	 * 
	 * @return SQL para utilização via JPQL.
	 */
	public String getSelectAllJQL();
	
	/**
	 * <p>Obtém a quantidade total de registros do modelo passado</p>
	 * <p>Esse método faz um <strong>SQL count()</strong> em um ID do modelo passado</p>
	 * 
	 * <p><strong>Um bom exemplo de uso é:</strong></p>
	 * 
	 * <pre>
	 *  &#64;Override
	 *	public int getQuantidadeTotalRegistro() {
	 *		TypedQuery*lt;Long&gt; query = entityManager.createQuery("SELECT COUNT(e) FROM SuaEntidade e", Long.class);
	 *    	return query.getSingleResult().intValue();
	 *	}
	 * </pre>
	 * 
	 * <p>Outras implementações são possíveis, como por exemplo utilizando StringBuilder ou mesmo criando um método auxiliar, entretanto, a criação da <strong>Query</strong> dentro do método não fere nenhum dos princípios SOLID.</p>
	 * 
	 * @return a quantidade total de registros do modelo passado em inteiro
	 */
	public int getQuantidadeTotalRegistro();

}
