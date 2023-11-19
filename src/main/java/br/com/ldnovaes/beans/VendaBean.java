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
import br.com.ldnovaes.models.Venda;
import br.com.ldnovaes.services.IClienteService;
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
	private Cliente cliente;
	private String nomeCliente;
	

	public VendaBean() {
		super(Venda.class);
	}
	
	@PostConstruct
	public void init() {
		this.cliente = new Cliente();
		this.setModelSelecionado(new Venda());
		this.setModels(this.getService().buscarTodos());
	}
	
	@Override
	public void salvarModel() {
		cliente = this.clienteService.buscarPorNome(nomeCliente);
		this.getModelSelecionado().setCliente(cliente);
		super.salvarModel();
	}
	

	public void abrirNovoModel() {
		this.cliente = new Cliente();
		this.setModelSelecionado(new Venda());
	}
	
	public List<String> completarNomecliente(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> clientesNomes = new ArrayList<>();
        List<Cliente> clientes = this.clienteService.buscarTodos();
        for (Cliente cliente: clientes) {
        	clientesNomes.add(cliente.getNome());
        }

        return clientesNomes.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }


}
