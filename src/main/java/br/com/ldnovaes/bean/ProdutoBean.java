package br.com.ldnovaes.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.ldnovaes.bean.generic.GenericBean;
import br.com.ldnovaes.model.Produto;
import br.com.ldnovaes.service.IProdutoService;
import lombok.Getter;
import lombok.Setter;

@Named(value = "produtoBean")
@ViewScoped
@Getter
@Setter
public class ProdutoBean extends GenericBean<Produto> implements Serializable{

	private static final long serialVersionUID = 1L;
	private String precoString;
	
    public ProdutoBean() {
		super(Produto.class);
	}
    
    @PostConstruct
	public void init() {
		this.modelSelecionado = new Produto();
	}
	

	public void abrirNovoModel() {
		this.modelSelecionado = new Produto();
		this.precoString = "";
	}

	@Override
	public void salvarModel() {
		this.modelSelecionado.setPreco(((IProdutoService) this.service).formatarPreco(this.precoString));
		super.salvarModel();
		PrimeFaces.current().executeScript("formataPrecoEmTabela()");
	}
	
}
