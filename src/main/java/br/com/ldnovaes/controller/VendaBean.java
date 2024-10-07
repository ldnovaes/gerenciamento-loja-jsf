package br.com.ldnovaes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;

import br.com.ldnovaes.controller.generic.GenericBean;
import br.com.ldnovaes.exception.NaoEncontradoBancoDados;
import br.com.ldnovaes.model.Cliente;
import br.com.ldnovaes.model.Produto;
import br.com.ldnovaes.model.Venda;
import br.com.ldnovaes.service.IClienteService;
import br.com.ldnovaes.service.IProdutoService;
import lombok.Getter;
import lombok.Setter;

@Named(value = "vendaBean")
@ViewScoped
@Getter
@Setter
public class VendaBean extends GenericBean<Venda> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	
	@Inject
	private IClienteService clienteService;
	
	@Inject
	private IProdutoService produtoService;
	
	@Inject
	private LazyDataModel<Cliente> clientes;

	public VendaBean() {
		super(Venda.class);
	}
	
	@PostConstruct
	public void init() {
		this.modelSelecionado = new Venda();
	}
	

	public void abrirNovoModel() {
		this.modelSelecionado = new Venda();
	}
	
	public List<Cliente> completarCliente(String query) {
        String queryLowerCase = query.toLowerCase();

        List<Cliente> clientes;
        
        try {
			 clientes = this.clienteService.listarPorNome(query)
						.stream()
						.filter(t -> t.getNome().toLowerCase().contains(queryLowerCase))
						.collect(Collectors.toList());;
			} catch (NoSuchFieldException | SecurityException | NaoEncontradoBancoDados e) {
				 clientes = new ArrayList<>();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.toString()));
				PrimeFaces.current().ajax().update("form:messages", "form:dt-models");
			}
	        
	        return clientes;
    }
	

	public List<Produto> completarProduto(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Produto> produtos = this.produtoService.buscarTodos();
        return produtos.stream().filter(t -> t.getNome().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }


}
