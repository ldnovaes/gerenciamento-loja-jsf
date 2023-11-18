package br.com.ldnovaes.daos;

import javax.enterprise.context.RequestScoped;

import br.com.ldnovaes.daos.generic.GenericDAO;
import br.com.ldnovaes.models.Venda;

@RequestScoped
public class VendaDAO extends GenericDAO<Venda> implements IVendaDAO{

	public VendaDAO() {
		super(Venda.class);
	}

}
