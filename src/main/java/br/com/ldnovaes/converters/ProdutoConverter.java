package br.com.ldnovaes.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ldnovaes.models.Produto;
import br.com.ldnovaes.services.IProdutoService;

@Named
@RequestScoped
public class ProdutoConverter implements Converter<Produto>{

	@Inject
    private IProdutoService produtoService;

    @Override
    public Produto getAsObject(FacesContext context, UIComponent component, String value) {
    	
        if (value != null && value.trim().length() > 0) {
            try {
                return this.produtoService.getProdutosEmMap().get(Long.parseLong(value));
            }
            catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Produto value) {
        if (value != null) {
            return String.valueOf(value.getId());
        }
        else {
            return null;
        }
    }

}
