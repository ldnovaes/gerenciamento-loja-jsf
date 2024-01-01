package br.com.ldnovaes.dao;

import javax.enterprise.context.RequestScoped;

import br.com.ldnovaes.dao.generic.GenericDAO;
import br.com.ldnovaes.model.Venda;

@RequestScoped
public class VendaDAO extends GenericDAO<Venda> implements IVendaDAO{

	public VendaDAO() {
		super(Venda.class);
	}

}
