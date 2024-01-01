package br.com.ldnovaes.exception;

/**
 * Exception para querys que tiveram erros ou não foram encontrados nenhum dado/registro.
 */
public class NaoEncontradoBancoDados extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NaoEncontradoBancoDados(String mensagem) {
        super(mensagem);
    }
	
}
