package br.com.ldnovaes.service;

import java.util.Map;

import br.com.ldnovaes.model.Produto;
import br.com.ldnovaes.service.generic.IGenericService;

public interface IProdutoService extends IGenericService<Produto>{
	Double formatarPreco(String precoString);

	Map<Long, Produto> getProdutosEmMap();
}
