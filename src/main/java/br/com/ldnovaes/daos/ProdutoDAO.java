package br.com.ldnovaes.daos;

import javax.enterprise.context.RequestScoped;

import br.com.ldnovaes.daos.generic.GenericDAO;
import br.com.ldnovaes.models.Produto;

@RequestScoped
public class ProdutoDAO extends GenericDAO<Produto> implements IProdutoDAO{

	public ProdutoDAO() {
		super(Produto.class);
	}

}
