package br.com.ldnovaes.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ldnovaes.daos.IProdutoDAO;
import br.com.ldnovaes.models.Produto;
import br.com.ldnovaes.services.generic.GenericService;

@RequestScoped
public class ProdutoService extends GenericService<Produto> implements IProdutoService{
	
	
	private IProdutoDAO produtoDao;
	
	@Inject
	public ProdutoService(IProdutoDAO produtoDao) {
		super(produtoDao);
		this.produtoDao = produtoDao;
	}

	
}
