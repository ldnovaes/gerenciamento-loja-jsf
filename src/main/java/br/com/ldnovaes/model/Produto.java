package br.com.ldnovaes.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PRODUTO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Produto implements Persistente{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	@Column(name = "codigo_produto")
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private String codigo;

	@Column(name = "nome_produto")
	private String nome;
	
	@Column(name = "preco_unitario")
	private Double preco;
	
	@ManyToMany(mappedBy = "produtos")
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
