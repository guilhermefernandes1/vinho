package com.algaworks.wine.model;

public enum TipoVinho {
	TINTO("Tinto"),
	BRANCO("Branco"),
	ROSE("Rosè"),
	SECO("Seco");
	
	private String descricao;
	
	TipoVinho(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
