package br.com.furafila.mvc.produto.model;

import br.com.furafila.mvc.dimensao.model.Dimensao;
import br.com.furafila.mvc.imagem.model.Imagem;
import br.com.furafila.mvc.tipoProduto.model.TipoProduto;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Produto {

    private Integer idProduto;
    private String produtoDesc;
    private Double valorUnitario = 0.0;
    private Integer qtdMinima;
    private Integer qtdMaxima;
    private Boolean status;
    private TipoProduto tipoProduto = new TipoProduto();
    private Imagem imagem = new Imagem();
    private Dimensao dimensao = new Dimensao();

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer id_produto) {
        this.idProduto = id_produto;
    }

    public String getProdutoDesc() {
        return produtoDesc;
    }

    public void setProdutoDesc(String produto_desc) {
        this.produtoDesc = produto_desc;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public String getValor_unitarioExibicao(){
        return FuraFilaUtils.formatarMoeda(getValorUnitario());
    }
    
    public void setValorUnitario(Double valor_unitario) {
        this.valorUnitario = valor_unitario;
    }

    public Integer getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(Integer qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public Integer getQtdMaxima() {
        return qtdMaxima;
    }

    public void setQtdMaxima(Integer qtdMaxima) {
        this.qtdMaxima = qtdMaxima;
    }
    
    public Character getStatusSQL(){
        return status? FuraFilaConstants.COD_ATIVO :  FuraFilaConstants.COD_INATIVO;
    }
    
    public String getStatusExibicao(){
        return status ? FuraFilaConstants.ATIVO :  FuraFilaConstants.INATIVO;
    }
    
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public Dimensao getDimensao() {
        return dimensao;
    }

    public void setDimensao(Dimensao dimensao) {
        this.dimensao = dimensao;
    }

    public Produto clonar() {
        Produto produto = new Produto();

        produto.setIdProduto(getIdProduto());
        produto.setProdutoDesc(getProdutoDesc());
        produto.setValorUnitario(getValorUnitario());
        produto.setQtdMaxima(getQtdMaxima());
        produto.setQtdMinima(getQtdMinima());
        produto.setStatus(getStatus());
        produto.setTipoProduto(getTipoProduto());
        produto.setImagem(getImagem());
        produto.setDimensao(getDimensao());

        return produto;
    }

}
