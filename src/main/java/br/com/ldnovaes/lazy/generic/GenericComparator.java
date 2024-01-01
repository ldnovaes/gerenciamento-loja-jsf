package br.com.ldnovaes.lazy.generic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.primefaces.model.SortOrder;

import br.com.ldnovaes.model.Persistente;

/**
 * <p>Comparador de valores a ser utilizado pela ordenação presente em <strong>{@link GenericLazyModel#load(int, int, java.util.Map, java.util.Map)}</strong></p>
 * 
 * @param <T> o tipo de model implementado de <strong>{@link Persistente}</strong>
 */
public class GenericComparator<T> implements Comparator<T> {

    private String campoOrdenacao;

    private SortOrder ordem;
    
    /**
     * <p>Construtor que recebe o campo a ser ordenado e comparado e a ordem que serão ajustados para exibição.</p>
     * 
     * @param campoOrdenacao o nome do campo presente no modelo que implementa de <strong>{@link Persistente}</strong> a ser ordenado
     * @param ordem          a ordem em que os dados serão organizados. Pode ser crescente ou decrescente
     */
    public GenericComparator(String campoOrdenacao, SortOrder ordem) {
        this.campoOrdenacao = campoOrdenacao;
        this.ordem = ordem;
    }
    
    /**
     * <p>Método que compara dois objetos</p>
     * 
     * @param model1 primeiro modelo a ser comparado
     * @param model2 segundo modelo a ser comparado
     * @return inteiro que indica se o valor é maior, menor ou igual ao 2º elemento comparado
     */
    public int compare(T model1, T model2) {
        try {
        	String[] fieldNames = campoOrdenacao.split("\\.");

        	Method metodo = null;
        	List<T> models = new ArrayList<T>(Arrays.asList(model1, model2));
        	List<Object> valores = new ArrayList<>();
        	Object valor = null;
        	
	        
        	for(T model : models) {
	        	for (String name : fieldNames) {
	        		
	        		if(valor == null) {
	        			metodo = model1.getClass().getMethod("get" + Character.toUpperCase(name.charAt(0)) + name.substring(1));
			            valor = metodo.invoke(model);
	        		} else {
	        			metodo = valor.getClass().getMethod("get" + Character.toUpperCase(name.charAt(0)) + name.substring(1));
			            valor = metodo.invoke(valor);
	        		}
		        }
	        	valores.add(valor);
	        	valor = null;
        	}
        	
        	Object value1 = valores.get(0);
        	Object value2 = valores.get(1);
	        int value = ((Comparable) value1).compareTo(value2);
            return SortOrder.ASCENDING.equals(ordem) ? value : -1 * value;
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
