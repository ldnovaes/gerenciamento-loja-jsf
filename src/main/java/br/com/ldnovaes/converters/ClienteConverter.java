package br.com.ldnovaes.converters;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.ldnovaes.converters.generic.GenericConverter;
import br.com.ldnovaes.model.Cliente;

@Named
@RequestScoped
public class ClienteConverter extends GenericConverter<Cliente> {

}
