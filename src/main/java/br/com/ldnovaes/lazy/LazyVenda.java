package br.com.ldnovaes.lazy;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.ldnovaes.lazy.generic.GenericLazyModel;
import br.com.ldnovaes.model.Venda;

@Named
@RequestScoped
public class LazyVenda extends GenericLazyModel<Venda> {

	private static final long serialVersionUID = 1L;

}
