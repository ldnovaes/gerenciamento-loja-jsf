package br.com.ldnovaes.dao;

import javax.enterprise.context.RequestScoped;

import br.com.ldnovaes.dao.generic.GenericDAO;
import br.com.ldnovaes.model.Produto;

@RequestScoped
public class ProdutoDAO extends GenericDAO<Produto> implements IProdutoDAO{

	public ProdutoDAO() {
		super(Produto.class);
	}

}
