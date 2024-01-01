package br.com.ldnovaes.converters;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.ldnovaes.converters.generic.GenericConverter;
import br.com.ldnovaes.model.Venda;

@Named
@RequestScoped
public class VendaConverter extends GenericConverter<Venda> {

}
