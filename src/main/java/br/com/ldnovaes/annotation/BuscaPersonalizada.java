package br.com.ldnovaes.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.persistence.EntityManager;

/**
 * <p>Anotação para guardar em RUNTIME a query para ser utilizado para filtrar um campo atraves de uma consulta <strong>ao banco de dados</strong></p>
 * <p>Importante destacar que esta anotação apenas guarda o a query, cuja implementação fica por conta do desenvolvedor responsável.</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BuscaPersonalizada {
	
	/**
	 * <p>Guarda oa query a ser utilizada.</p>
	 * 
	 * @return a query para ser utilizada por um <strong>{@link EntityManager}</strong>
	 */
	String query();
}
