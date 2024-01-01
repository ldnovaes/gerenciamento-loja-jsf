package br.com.ldnovaes.lazy.generic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.Normalizer;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.ComparatorUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;

import br.com.ldnovaes.annotation.Conversor;
import br.com.ldnovaes.model.Persistente;
import br.com.ldnovaes.service.generic.IGenericService;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Classe abstrata e genérica a ser implementado por modelos para utilização de lazy load eficazes gerenciados pelo próprio PrimeFaces</p>
 * <p>A implementação desta classe é fácil. Em seu modelo mais simples, onde não há nenhum <strong>{@link Conversor}</strong> anotado em algum campo do modelo a ser utilizado é:</p>
 *
 * <pre>
 * 	&#64;Named
 *	&#64;RequestScoped
 *	public class LazyItemLicitacao extends GenericLazyModel&lt;ItemLicitacao&gt; {
 *		private static final long serialVersionUID = 1L;
 *	}
 * </pre>
 * 
 * <p>Para casos onde há um <strong>{@link Conversor}</strong> anotado, é preciso criar um filtro descente com um retorno <strong>boolean</strong> para bom funcionamento da filtragem.</p>
 * 
 * <strong>Um exemplo disto é: </strong>
 * 
 * <pre>
 * 	&#64;Named
 *	&#64;RequestScoped
 *	public class LazyItemLicitacao extends GenericLazyModel&lt;ItemLicitacao&gt; {
 *		
 *		private static final long serialVersionUID = 1L;
 *		
 *		public boolean filtroNumeroItem(Object filtroBancoDeDados, Object filtroInserido) {
 *			
 *			if(filtroBancoDeDados.equals(filtroInserido) {
 *				return true;
 *			}
 *			return false;
 *		}
 *	}
 * </pre>
 * 
 * <p>Após a criação da função filtro com retorno boolean, é importantíssimo anotar o nome desse filtro no <strong>field</strong> correspondente em sua classe modelo que extende <strong>{@link Persistente}</strong></p>
 * <p>A anotação é a <strong>{@link Conversor}</strong></p>
 * <strong>Um exemoplo disto é:</strong>
 * 
 * <pre>
 * 		public class ItemLicitacao implements Persistente {
 * 				&#64;Column(name = "numero_item")
 *				&#64;Conversor(nome = "filtroNumeroItem") 
 *				private Integer numeroItem;
 * 		}
 * </pre>
 * 
 * <p>Isso garante o funcionamento do filtro. O detalhe aqui é que você precisa caprichar na construção do seu filtro, para abrangir diversas entradas do usuário, visando sempre entregar o melhor resultado possível.</p>
 * <p><strong>IMPORTANTE</strong></p>
 * <p>É importante que todas as implementações desta classe genérica tenham anotados <strong>{@link RequestScoped}</strong> e <strong>{@link Named}</strong>.</p>
 * 
 * @param <T> O tipo de <strong>modelo</strong> que implementa de <strong>{@link Persistente}</strong> para ser utilizado
 */
@Getter
@Setter
public abstract class GenericLazyModel<T extends Persistente> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	protected IGenericService<T> service;
	
	protected List<T> models;
	
	@PostConstruct
	public void init() {
		this.models = this.service.buscarTodos();
	}

	@Override
	public T getRowData(String rowKey) {
		for (T model : this.models) {
			if (model.getId() == Integer.parseInt(rowKey)) {
				return model;
			}
		}
		return null;
	}

	@Override
	public String getRowKey(T licitacao) {
		return String.valueOf(licitacao.getId());
	}

	@Override
	public int count(Map<String, FilterMeta> filtrarPor) {
		return (int) this.models
				.stream()
				.filter(o -> filtrar(FacesContext.getCurrentInstance(), filtrarPor.values(), o))
				.count();
	}

	@Override
	public List<T> load(int inicio, int tamanhoPagina, Map<String, SortMeta> ordenarPor,
			Map<String, FilterMeta> filtrarPor) {

        List<T> modelsFiltrados = this.models.stream()
                .skip(inicio)
                .filter(o -> filtrar(FacesContext.getCurrentInstance(), filtrarPor.values(), o))
                .limit(tamanhoPagina)
                .collect(Collectors.toList());

		if (!ordenarPor.isEmpty()) {
			List<Comparator<T>> comparators = ordenarPor.values().stream()
					.map(o -> new GenericComparator<T>(o.getField(), o.getOrder()))
					.collect(Collectors.toList());
			Comparator<T> cp = ComparatorUtils.chainedComparator(comparators);
			modelsFiltrados.sort(cp);
		}
						
		return modelsFiltrados;
	}
	
	/**
	 * <p>Método que filtra os dados inseridos pelos usuários</p>
	 * <p>Esse método foi construído para funcionar de forma genérica abrangindo todos os tipos de relacionamentos e evitando que a filtragem não tenha um bom resultado.</p>
	 * <p>Ele busca pelos Getters dos fields do modelo a ser buscado e verifica se há uma anotação de <strong>{@link Conversor}</strong>.</p>
	 * <p>Se houver ele executa o método correspondente e retorna o resultado.</p>
	 * <p>Se não houver, ele realiza a verificação com matching.isMatching() e retorna o resultado.</p>
	 * 
	 * @param context      o contexto atual do JSF
	 * @param filtrarPor   uma coleção com os filtros aplicados pelo usuário
	 * @param o            o objeto (modelo) que está sendo filtrado. 
	 * @return             true para correspondências no filtro e false para não correspondências
	 */
	private boolean filtrar(FacesContext context, Collection<FilterMeta> filtrarPor, Object o) {
		
		boolean matching = true;

		for (FilterMeta filtro : filtrarPor) {
			FilterConstraint constraint = filtro.getConstraint();
			Object valorInserido = filtro.getFilterValue();
			String nomeField = filtro.getField();

			Object[] resultado = this.getValorDeField(o, nomeField);
			
			if (resultado == null) {
				break;
			}
			
			Field fieldAtual = (Field) resultado[0];
			Object valorColunaBancoDeDados = resultado[1];

			try { // tenta obter o método conversor anotado no field do model e em caso de falha
					// tenta a verificação normal, sem utilização de um conversor
				String nomeMetodoConversor = fieldAtual.getAnnotation(Conversor.class).nome();
				
				Method metodoConversor = this.getClass().getDeclaredMethod(nomeMetodoConversor, Object.class, Object.class);
				
				metodoConversor.setAccessible(true);
				matching = (boolean) metodoConversor.invoke(this, valorColunaBancoDeDados, valorInserido);
				metodoConversor.setAccessible(false);
			} catch (Exception e) {
				System.out.println(e);
				matching = constraint.isMatching(context, valorColunaBancoDeDados, valorInserido, LocaleUtils.getCurrentLocale());
			}

			if (!matching) {
				break;
			}
		}

		return matching;
	}
	
	/**
	 * <p>Obtém o campo relacionado e o valor da coluna do banco de dados correspondente ao campo presente no modelo extendido de <strong>{@link Persistente}</strong> a ser filtrado</p>
	 * <p>Com base no nome do campo, obtem o getter desse campo e então invoca o método em busca do valor correspondente, obtendo também o field relacionado ao getter</p> 
	 * 
	 * @param obj        o modelo a ser filtrado
	 * @param nomeField  o nome do campo ao qual o usuário quer filtrar
	 * @return o field e o seu valor, referente a coluna ao qual o usuário quer filtrar
	 */
	private Object[] getValorDeField(Object obj, String nomeField) {

		try {
			String[] nomeFields = nomeField.split("\\.");
			Object valorField = obj;
			Field field = null;

			for (String name : nomeFields) {
				field = valorField.getClass().getDeclaredField(name);
				valorField = valorField.getClass().getMethod("get" + capitalize(name)).invoke(valorField);
			}
			return new Object[] {field, valorField};
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * <p>Transforma a primeira letra em maiúscula</p>
	 * <p>Utilizada pelo método <strong>{@link GenericLazyModel#filtrar(FacesContext, Collection, Object)}<strong> pra obter o getter referente ao campo</p>
	 * @param str a string ao qual é preciso transforma em maiúscula
	 * @see GenericLazyModel#filtrar(FacesContext, Collection, Object)
	 * @return a string com sua primeira letra em maiúscula
	 */
	private String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	/**
	 * <p>Filtra um registro/modelo ignorando os acentos.</p>
	 * <p>A filtragem de registros/modelos é realizada ignorando qualquer acento, também ignorando maiúsculas e minúsculas.</p>
	 * 
 	 * @param valorBancoDeDados  refere-se ao valor guardado na coluna marcada com a anotação <strong>{@link Conversor}</strong> presente no modelo a ser filtrado
	 * @param valorInserido      a entrada do usuário inserida no campo de filtro 
	 * @return <strong>true</strong> se a coluna vinda do banco contém os dados inseridos pelo usuário, caso contrário, <strong>false</strong>
	 */
	protected boolean filtrarIgnorandoAcentos(Object valorBancoDeDados, Object valorInserido) {
		
		if (valorInserido == null || valorInserido.toString().isEmpty()) {
			return true;
		}
		
		if (valorBancoDeDados == null) {
			return false;
		}
		
		String modalidadeInseridaPorUsuario = Normalizer.normalize(valorInserido.toString(), Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase(); 
		String modalidade = Normalizer.normalize(valorBancoDeDados.toString(), Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase(); 
		return modalidade.contains(modalidadeInseridaPorUsuario);
	}


}
