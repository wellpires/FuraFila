
package br.com.furafila.mvc.estoqueProdutos.model;

import br.com.furafila.mvc.estoque.model.Estoque;
import br.com.furafila.mvc.produto.model.Produto;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class EstoqueProdutos {
    
    private Integer idEstoqueProdutos;
    private Integer qtdEstoque;
    private Produto produto = new Produto();
    private Estoque estoque = new Estoque();

    public Integer getIdEstoqueProdutos() {
        return idEstoqueProdutos;
    }

    public void setIdEstoqueProdutos(Integer id_estoque_produtos) {
        this.idEstoqueProdutos = id_estoque_produtos;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    
    public EstoqueProdutos Clonar(){
        EstoqueProdutos ep = new EstoqueProdutos();
        
        ep.setEstoque(getEstoque());
        ep.setIdEstoqueProdutos(getIdEstoqueProdutos());
        ep.setProduto(getProduto());
        ep.setQtdEstoque(getQtdEstoque());
        
        return ep;
    }
}
