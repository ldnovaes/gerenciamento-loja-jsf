package br.com.ldnovaes.services;

import java.util.Map;

import br.com.ldnovaes.models.Produto;
import br.com.ldnovaes.services.generic.IGenericService;

public interface IProdutoService extends IGenericService<Produto>{
	Double formatarPreco(String precoString);

	Map<Long, Produto> getProdutosEmMap();
}
