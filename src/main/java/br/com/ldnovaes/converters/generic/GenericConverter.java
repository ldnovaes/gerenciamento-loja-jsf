package br.com.ldnovaes.converters.generic;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

import br.com.ldnovaes.model.Persistente;
import br.com.ldnovaes.service.generic.IGenericService;

public class GenericConverter<T extends Persistente> implements Converter<T> {

	@Inject
	private IGenericService<T> service;

	@Override
	public T getAsObject(FacesContext context, UIComponent component, String value) {
	    if (value == null || value.trim().isEmpty()) {
	        return null;
	    }

	    try {
	        Long id = Long.valueOf(value.trim());
	        return this.service.getItensEmMap(T::getId).get(id);
	    } catch (NumberFormatException e) {
	        String errorMessage = String.format("Erro de conversão: '%s' não é um ID válido.", value);
	        throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", errorMessage));
	    }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, T value) {
		if (value != null) {
			return String.valueOf(value.getId());
		} 
		return null;
	}
}