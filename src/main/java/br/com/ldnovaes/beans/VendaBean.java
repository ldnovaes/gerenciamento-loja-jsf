package br.com.ldnovaes.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ldnovaes.models.Cliente;
import br.com.ldnovaes.models.Produto;
import br.com.ldnovaes.models.Venda;
import br.com.ldnovaes.services.IClienteService;
import br.com.ldnovaes.services.IProdutoService;
import lombok.Getter;
import lombok.Setter;

@Named(value = "vendaBean")
@ViewScoped
@Getter
@Setter
public class VendaBean extends GenericBean<Venda> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IClienteService clienteService;
	@Inject
	private IProdutoService produtoService;
	private Cliente cliente;
	private String nomeCliente;
	private List<String> produtosSelecionados;
	

	public VendaBean() {
		super(Venda.class);
	}
	
	@PostConstruct
	public void init() {
		this.cliente = new Cliente();
		this.produtosSelecionados = new ArrayList<>();
		this.setModelSelecionado(new Venda());
		this.setModels(this.getService().buscarTodos());
	}
	
	@Override
	public void salvarModel() {
		System.out.println(this.produtosSelecionados);
		cliente = this.clienteService.buscarPorNome(nomeCliente);
		this.produtosSelecionados = new ArrayList<>();
		this.getModelSelecionado().setCliente(cliente);
		super.salvarModel();
	}
	

	public void abrirNovoModel() {
		this.cliente = new Cliente();
		this.setModelSelecionado(new Venda());
	}
	
	public List<String> completarNomeCliente(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> clientesNomes = new ArrayList<>();
        List<Cliente> clientes = this.clienteService.buscarTodos();
        for (Cliente cliente: clientes) {
        	clientesNomes.add(cliente.getNome());
        }

        return clientesNomes.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }
	

	public List<String> completarNomeProduto(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> produtosNomes = new ArrayList<>();
        List<Produto> produtos = this.produtoService.buscarTodos();
        
        for (Produto produto: produtos) {
        	produtosNomes.add(produto.getNome());
        }

        return produtosNomes.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }


}
