package br.com.ldnovaes.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.ldnovaes.models.Produto;
import br.com.ldnovaes.services.IProdutoService;
import lombok.Getter;
import lombok.Setter;

@Named(value = "produtoBean")
@ViewScoped
@Getter
@Setter
public class ProdutoBean extends GenericBean<Produto> implements Serializable{

	private static final long serialVersionUID = 1L;
	private String precoString;
	
	@Inject
	private IProdutoService produtoService;
	
    public ProdutoBean() {
		super(Produto.class);
		this.setService(produtoService);
	}
    
    @PostConstruct
	public void init() {
		this.setModelSelecionado(new Produto());
		this.setModels(this.produtoService.buscarTodos());
	}
	

	public void abrirNovoModel() {
		this.setModelSelecionado(new Produto());
		this.precoString = "";
	}

	@Override
	public void salvarModel() {
		
		this.getModelSelecionado().setPreco(
				this.produtoService.formatarPreco(this.precoString));
		
		super.salvarModel();
		PrimeFaces.current().executeScript("formataPrecoEmTabela()");

	}
	
}
