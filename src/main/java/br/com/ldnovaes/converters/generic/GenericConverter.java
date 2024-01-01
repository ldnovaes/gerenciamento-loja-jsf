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
		if (value != null && value.trim().length() > 0) {
			try {
				return this.service.getItensEmMap(T::getId).get(Long.parseLong(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão",
						"Não é uma licitação válido."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, T value) {
		if (value != null) {
			return String.valueOf(value.getId());
		} else {
			return null;
		}
	}
}