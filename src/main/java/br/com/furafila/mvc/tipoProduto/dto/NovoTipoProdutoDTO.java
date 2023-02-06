package br.com.furafila.mvc.tipoProduto.dto;

public class NovoTipoProdutoDTO {

	private String name;

	public NovoTipoProdutoDTO() {
	}

	public NovoTipoProdutoDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
