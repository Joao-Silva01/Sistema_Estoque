package br.com.springboot.model;

public enum Category {
	
	CELULARES("Celulares"),
	ELETRODOMESTICO("Eletrodomésticos"),
	INFORMATICA("Informática"),
	MOVEIS("Móveis");
	
	private String descricao;
	
	Category(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
