package br.com.ldnovaes.model;

import java.util.List;

import br.com.ldnovaes.annotation.BuscaPersonalizada;
import br.com.ldnovaes.annotation.Conversor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_CLIENTE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente implements Persistente{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	@Column(name = "codigo_cliente")
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private String codigo;
	
	@Column(name = "nome_cliente")
	@Conversor(nome = "filtrarIgnorandoAcentos")
	@BuscaPersonalizada(query = "SELECT obj FROM Cliente obj WHERE obj.nome ILIKE :input")
	private String nome;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<Venda> vendas;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;		
	}

	@Override
	public String getCodigo() {
		return this.codigo;
	}

	@Override
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
