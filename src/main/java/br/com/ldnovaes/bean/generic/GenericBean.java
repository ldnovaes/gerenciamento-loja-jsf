package br.com.ldnovaes.bean.generic;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;

import br.com.ldnovaes.model.Persistente;
import br.com.ldnovaes.service.generic.IGenericService;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Classe Genérica para os <strong>beans</strong> compartilharem recursos em comum.</p>
 * <p>Essa classe é a classe abstrata e genérica para que os <strong>beans</strong> reutilizem todos os recursos comuns entre si, sem a necessidade de vários beans com métodos e atributos repetidos.</p>
 * <p>Os recursos supracitados são:</p>
 * <ul>
 *     <li>Adição de Registro</li>
 *     <li>Edição de Registro</li>
 *     <li>Deleção de Registro</li>
 * </ul>
 * 
 * <p>Exemplos de criação de um novo <strong>bean</strong>:</p>
 * 
 * <pre>
 *     public class LicitacaoBean extends GenericBean&lt;Licitacao&gt; implements Serializable {
 *  
 *         private static final long serialVersionUID = 1L;
 *        
 *         public LicitacaoBean() {
 *             super(Licitacao.class);
 *         }
 *
 *         {@code @}PostConstruct
 *         public void init() {
 *             this.modelSelecionado = new Licitacao(); // inicia um novo modelo
 *             this.models = this.service.buscarTodos(); // inicia uma lista com todos os registros para apresentação na view
 *         }
 *     }
 * </pre>
 * 
 * @see br.com.ldnovaes.bean.generic.IGenericBean
 * @see br.com.ldnovaes.model.Persistente
 * @author ldnovaes
 */

@Getter
@Setter
public abstract class GenericBean<T extends Persistente> implements IGenericBean<T> {
	
	@Inject
	protected LazyDataModel<T> models;

	protected T modelSelecionado;

	protected List<T> modelsSelecionados;
    
	protected String modelClassName;

	@Inject
	protected IGenericService<T> service;
	
	/**
	 * <p>Construtor da classe para que as classes filhas possam informar à abstração qual o tipo de modelo utilizado.</p>
	 * 
	 * <p>Um exemplo de utilização desse construtor em um <strong>bean</strong> que extende <strong>GenericBean</strong> é o abaixo:</p>
	 * 
	 * <pre>
	 * public LicitacaoBean() {
	 *     super(Licitacao.class);
	 * }
	 * </pre>
	 * 
	 * @param modelClass tipo da classe model (T) para que seja utilizado por seus métodos genéricos.
	 */
	public GenericBean(Class<T> modelClass) {
        this.modelClassName = modelClass.getSimpleName();
    }

	@Override
	public void salvarModel() {
		if (this.modelSelecionado.getId() == null) {
			//this.modelSelecionado.setCodigo(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
			this.service.persistir(modelSelecionado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro:", this.modelClassName + " Adicionado"));
		} else {
			this.service.editar(modelSelecionado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.modelClassName + " Atualizado"));
		}

		PrimeFaces.current().executeScript("PF('manageModelDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-models");
	}

	@Override
	public void deletarModel() {
		this.service.deletar(modelSelecionado);
		this.modelSelecionado = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exclusão de Registro:", this.modelClassName + " Excluído"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-models");
	}

	@Override
	public String getBotaoMensagemDeletar() {
		if (hasModelsSelecionados()) {
			int quantidadeSelecionados = this.modelsSelecionados.size();
			return quantidadeSelecionados > 1 ? quantidadeSelecionados + " " + this.modelClassName.toLowerCase() + "s selecionados" : "1 " + this.modelClassName + " selecionado";
		}
		return "Excluir";
	}

	@Override
	public boolean hasModelsSelecionados() {
		return this.modelsSelecionados != null && !this.modelsSelecionados.isEmpty();
	}
	
	@Override
	public void deletarModelsSelecionados() {
		this.modelsSelecionados = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registros Selecionados Deletados", this.modelClassName + " Removidos"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-models");
		PrimeFaces.current().executeScript("PF('dtModels').clearFilters()");
	}
	
	@Override
	public void onDataTableUpdate(PageEvent event) {
		//int paginaAtual = event.getPage() + 1;
	}

}
