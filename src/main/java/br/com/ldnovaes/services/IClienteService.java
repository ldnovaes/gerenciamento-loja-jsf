package br.com.ldnovaes.services;

import br.com.ldnovaes.models.Cliente;
import br.com.ldnovaes.services.generic.IGenericService;

public interface IClienteService extends IGenericService<Cliente>{
	Cliente buscarPorNome(String nome);
}
