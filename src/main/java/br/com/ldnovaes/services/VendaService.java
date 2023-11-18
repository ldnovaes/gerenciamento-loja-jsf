package br.com.ldnovaes.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ldnovaes.daos.IVendaDAO;
import br.com.ldnovaes.models.Venda;
import br.com.ldnovaes.services.generic.GenericService;

@RequestScoped
public class VendaService extends GenericService<Venda> implements IVendaService{
	private IVendaDAO vendaDAO;
	
	@Inject
	public VendaService(IVendaDAO vendaDAO) {
		super(vendaDAO);
		this.vendaDAO = vendaDAO;
	}
}
