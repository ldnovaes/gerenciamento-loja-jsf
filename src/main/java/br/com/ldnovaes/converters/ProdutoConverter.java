package br.com.ldnovaes.converters;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.ldnovaes.converters.generic.GenericConverter;
import br.com.ldnovaes.model.Produto;

@Named
@RequestScoped
public class ProdutoConverter extends GenericConverter<Produto>{

}
