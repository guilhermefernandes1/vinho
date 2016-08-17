package com.algaworks.wine.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="vinho")
public class Vinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "O nome é obrigatório.")
	private String nome;
	
	@NotNull(message = "Tipo é obrigatório.")
	@Enumerated(EnumType.STRING)
	private TipoVinho tipo;
	
	@NotNull(message = "Safra é obrigatório.")
	@Min(1)
	private int safra;
	
	@NotNull(message = "Volume é obrigatório.")
	@Min(100)
	private int volume;
	
	@NotNull(message = "Valor é obrigatório.")
	private BigDecimal valor;
	
	private String foto;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoVinho getTipo() {
		return tipo;
	}
	public void setTipo(TipoVinho tipo) {
		this.tipo = tipo;
	}
	public int getSafra() {
		return safra;
	}
	public void setSafra(int safra) {
		this.safra = safra;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Long getCodigo() {
		return codigo;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
