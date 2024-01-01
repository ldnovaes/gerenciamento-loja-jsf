package br.com.ldnovaes.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.ldnovaes.models.Cliente;
import lombok.Getter;
import lombok.Setter;

@Named(value = "clienteBean")
@ViewScoped
@Getter
@Setter
public class ClienteBean extends GenericBean<Cliente> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ClienteBean() {
		super(Cliente.class);
	}
	
	@PostConstruct
	public void init() {
		this.setModelSelecionado(new Cliente());
		this.setModels(this.getService().buscarTodos());
	}
	

	public void abrirNovoModel() {
		this.setModelSelecionado(new Cliente());
	}
	
    
}
