package br.com.ldnovaes.lazy;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.ldnovaes.lazy.generic.GenericLazyModel;
import br.com.ldnovaes.model.Produto;

@Named
@RequestScoped
public class LazyProduto extends GenericLazyModel<Produto> {

	private static final long serialVersionUID = 1L;

}
