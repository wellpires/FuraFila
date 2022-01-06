package br.com.furafila.mvc.tipoProduto.model;

import br.com.furafila.utils.FuraFilaConstants;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class TipoProduto {

    private Integer idTipoProduto = 0;
    private String tipoProdutoDesc = "";
    private Boolean status;

    public Integer getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(Integer id_tipo_produto) {
        this.idTipoProduto = id_tipo_produto;
    }

    public String getTipoProdutoDesc() {
        return tipoProdutoDesc;
    }

    public void setTipoProdutoDesc(String tipo_produto_desc) {
        this.tipoProdutoDesc = tipo_produto_desc;
    }
    
    public Character getStatusSQL() {
        return status ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO;
    }
    
    public String getStatusExibicao() {
        return status ? FuraFilaConstants.ATIVO : FuraFilaConstants.INATIVO;
    }
    
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public TipoProduto clonar() {
        
        TipoProduto tp = new TipoProduto();

        tp.setIdTipoProduto(getIdTipoProduto());
        tp.setTipoProdutoDesc(getTipoProdutoDesc());
        tp.setStatus(getStatus());

        return tp;
    }

}
