package br.com.ldnovaes.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ldnovaes.dao.IVendaDAO;
import br.com.ldnovaes.model.Venda;
import br.com.ldnovaes.service.generic.GenericService;

@RequestScoped
public class VendaService extends GenericService<Venda> implements IVendaService{
	private IVendaDAO vendaDAO;
	
	@Inject
	public VendaService(IVendaDAO vendaDAO) {
		super(vendaDAO);
		this.vendaDAO = vendaDAO;
	}
}
