package br.com.ldnovaes.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.ldnovaes.controller.generic.GenericBean;
import br.com.ldnovaes.model.Cliente;
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
		this.modelSelecionado = new Cliente();
	}
	

	public void abrirNovoModel() {
		this.setModelSelecionado(modelSelecionado);
	}
	
    
}
