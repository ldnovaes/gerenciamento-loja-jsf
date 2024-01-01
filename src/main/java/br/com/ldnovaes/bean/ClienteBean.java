package br.com.ldnovaes.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.ldnovaes.bean.generic.GenericBean;
import br.com.ldnovaes.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Named(value = "clienteBean")
@ViewScoped
@Getter
@Setter
public class ClienteBean extends GenericBean<Cliente> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String a;

	public ClienteBean() {
		super(Cliente.class);
	}
	
	@PostConstruct
	public void init() {
		this.modelSelecionado = new Cliente();
	}
	

	public void abrirNovoModel() {
		this.setModelSelecionado(new Cliente());
	}
	
    
}
