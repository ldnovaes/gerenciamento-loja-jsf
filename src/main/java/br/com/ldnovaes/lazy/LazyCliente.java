package br.com.ldnovaes.lazy;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.ldnovaes.lazy.generic.GenericLazyModel;
import br.com.ldnovaes.model.Cliente;

@Named
@RequestScoped
public class LazyCliente extends GenericLazyModel<Cliente> {

	private static final long serialVersionUID = 1L;

	@Override
	protected boolean filtrarIgnorandoAcentos(Object valorBancoDeDados, Object valorInserido) {
		return super.filtrarIgnorandoAcentos(valorBancoDeDados, valorInserido);
	}

}
