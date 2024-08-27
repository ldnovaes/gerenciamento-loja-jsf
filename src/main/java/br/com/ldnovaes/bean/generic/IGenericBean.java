package br.com.ldnovaes.bean.generic;

import org.primefaces.event.data.PageEvent;

import br.com.ldnovaes.model.Persistente;



/**
* <p>Uma interface para criação de <strong>beans</strong> genéricos.</p>
* <p>Usuários desta interface são classes genéricas de beans gerenciáveis que necessitam de executar certas ações que são comum para todos <strong>beans</strong>.</p>
* <p>Os recursos supracitados que são em comum para todos os beans são:</p>
* <ul>
* 	<li>Abrir uma nova instância do modelo</li>
* 	<li>Adição de Registro</li>
* 	<li>Edição de Registro</li>
* 	<li>Deleção de Registro</li>
*</ul>
*/
public interface IGenericBean<T extends Persistente> {
	
	
	/**
	 * <p>Abre um novo registro/modelo.</p>
	 * <p>Este método criará uma nova instância de <strong>{@link Persisente}</strong> quando o usuário tentar adicionar um novo registro.</p>
	 */
	public void abrirNovoModel();
	
	/**
	 * <p>Salva um registro, sendo ele existente ou não.</p>
	 * <p>Primeiro, é necessário verificar se há um ID já inserido no modelo, para então descobrir se é uma <strong>ADIÇÃO</strong> de um novo registro ou uma <strong>EDIÇÃO</strong> de um registro já cadastrado anteriormente</p>
	 * 
	 * <p><strong>Exemplo de uma boa implementação:</strong></p>
	 * 
	 * <pre>
	 *	public void salvarModel() {
	 *		if (this.modelSelecionado.getId() == null) {
	 *			this.service.persistir(modelSelecionado);
	 *			this.models.add(this.modelSelecionado);
	 *		} else {
	 *			this.service.editar(modelSelecionado);
	 *		}
	 *	}
	 * </pre>
	 */
	public void salvarModel();
	
	/**
	 * <p>Deleta um registro do banco de dados.</p>
	 * <p>Esse método deleta o registro selecionado.</p>
	 */
	public void deletarModel();
	
	/**
	 * <p>Obtém a mensagem dinâmica para o botão de exclusão.</p>
	 * <p>Esse método edita uma mensagem dinâmica para o botão de deleção no <strong>xhtml</strong> com base no <strong>modelClassName</strong> passado via construtor.</p>
	 * 
	 * @return mensagem dinâmica para a renderização do botão.
	 */
	public String getBotaoMensagemDeletar();
	
	/**
	 * <p>Verifica se existe registros selecionados.</p>
	 * <p>Esse método verifica se há mais de um registro selecionado.</p>
	 * 
	 * @return true para caso haja mais de um modelo selecionado e false para caso não haja nenhum.
	 */
	public boolean temModelsSelecionados();
	
	/**
	 * <p>Esse método apaga vários registros selecionados de uma única vez.</p> 
	 * <p>Caso o usuário tenha escolhido a exclusão de 2 ou mais registros de uma única vez, é esse método que deve ser chamado.</p>
	 */
	public void deletarModelsSelecionados();
	
	
	/**
	 * <p>Ouvinte para paginação.</p>
	 * <p>Esse ouvinte obtém a página atual do usuário enquanto ele realiza a paginação no <strong>DataTable</strong>.</p>
	 * 
	 * @param event é passado através do listener, é o objeto da paginação
	 */
	public void onDataTableUpdate(PageEvent event);
}
