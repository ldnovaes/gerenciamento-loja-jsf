package br.com.ldnovaes.beans;

import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import br.com.ldnovaes.models.IModel;
import br.com.ldnovaes.services.generic.IGenericService;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class GenericBean<T extends IModel> {
	
	private List<T> models;

    private T modelSelecionado;

    private List<T> modelsSelecionados;
    
    private String modelClassName;

	@Inject
	private IGenericService<T> service;
	
	public GenericBean(Class<T> modelClass) {
        this.modelClassName = modelClass.getSimpleName();
    }

	public void salvarModel() {
		if (this.modelSelecionado.getCodigo() == null) {
			this.modelSelecionado.setCodigo(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
			this.service.persistir(modelSelecionado);
			this.models.add(this.modelSelecionado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.modelClassName + " Adicionado"));
		} else {
			this.service.editar(modelSelecionado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.modelClassName + " Atualizado"));
		}

		PrimeFaces.current().executeScript("PF('manageModelDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-models");
	}

	public void deletarModel() {
		this.service.deletar(modelSelecionado);
		this.models.remove(this.modelSelecionado);
		this.modelSelecionado = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.modelClassName + " Excluído"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-models");
	}

	public String getBotaoMensagemDeletar() {
		if (hasModelsSelecionados()) {
			int quantidadeSelecionados = this.modelsSelecionados.size();
			return quantidadeSelecionados > 1 ? quantidadeSelecionados + " " + this.modelClassName.toLowerCase() + "s selecionados" : "1 " + this.modelClassName + " selecionado";
		}

		return "Excluir";
	}

	public boolean hasModelsSelecionados() {
		return this.modelsSelecionados != null && !this.modelsSelecionados.isEmpty();
	}

	public void deleteModelsSelecionados() {
		this.models.removeAll(this.modelsSelecionados);
		this.modelsSelecionados = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.modelClassName + " Removidos"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-models");
		PrimeFaces.current().executeScript("PF('dtModels').clearFilters()");
	}


}
