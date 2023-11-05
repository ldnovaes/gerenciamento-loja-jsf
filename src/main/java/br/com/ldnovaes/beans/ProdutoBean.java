package br.com.ldnovaes.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.ldnovaes.models.Produto;
import lombok.Getter;
import lombok.Setter;

@Named(value = "produtoBean")
@ViewScoped
@Getter
@Setter
public class ProdutoBean extends GenericBean<Produto> implements Serializable{

	private static final long serialVersionUID = 1L;
	
    public ProdutoBean() {
		super(Produto.class);
	}
    
    @PostConstruct
	public void init() {
		this.setModelSelecionado(new Produto());
		this.setModels(this.getService().buscarTodos());
	}
	

	public void abrirNovoModel() {
		this.setModelSelecionado(new Produto());
	}


	
}
