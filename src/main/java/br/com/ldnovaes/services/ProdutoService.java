package br.com.ldnovaes.services;

import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.ldnovaes.daos.IProdutoDAO;
import br.com.ldnovaes.models.Produto;
import br.com.ldnovaes.models.Venda;
import br.com.ldnovaes.services.generic.GenericService;

@RequestScoped
public class ProdutoService extends GenericService<Produto> implements IProdutoService{
	
	
	private IProdutoDAO produtoDao;
	private Map<Long, Produto> produtosEmMap;
	
	@Inject
	public ProdutoService(IProdutoDAO produtoDao) {
		super(produtoDao);
		this.produtoDao = produtoDao;
	}

	@Override
	public Double formatarPreco(String precoString) {
		String precoFormatado = precoString
				.strip()
				.replace(".", "")
				.replace(",", ".");
		Double preco = Double.valueOf(precoFormatado);
		return preco;
	}

	@Override
	public Map<Long, Produto> getProdutosEmMap() {
		if (this.produtosEmMap == null) {
            this.produtosEmMap = this.produtoDao.buscarTodos().stream().collect(Collectors.toMap(Produto::getId, produto -> produto));
        }
        return this.produtosEmMap;
	}
	
	@Override
	public void deletar(Produto model) {
		if (model.getVendas().size() > 0) {
			for (Venda venda : model.getVendas()) {
				venda.getProdutos().remove(model);
			}
		}
		super.deletar(model);
	}

	
}
