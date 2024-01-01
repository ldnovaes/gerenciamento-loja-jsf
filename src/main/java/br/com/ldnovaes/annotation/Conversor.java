package br.com.ldnovaes.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.ldnovaes.lazy.generic.GenericLazyModel;

/**
 * <p>Anotação para guardar em RUNTIME o nome do método utilizado para filtrar um campo atraves de <strong>{@link GenericLazyModel#load(int, int, java.util.Map, java.util.Map)}</strong></p>
 * <p>Importante destacar que esta anotação apenas guarda o nome do método, cuja implementação fica por conta do desenvolvedor responsável.</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Conversor {
	
	/**
	 * <p>Guarda o nome do método a ser utilizado para filtragem.</p>
	 * 
	 * @return nome do método
	 */
	String nome();
}
