package br.com.furafila.mvc.tipoProduto.dto;

public class EditarTipoProdutoDTO {

	private String name;

	public EditarTipoProdutoDTO() {
	}

	public EditarTipoProdutoDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
