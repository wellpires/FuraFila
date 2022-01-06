
package br.com.furafila.mvc.estoqueSaida.model;

import java.util.Date;

import br.com.furafila.mvc.motivoSaida.model.MotivoSaida;
import br.com.furafila.mvc.produto.model.Produto;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class EstoqueSaida {  
    private Integer idEstoqueSaida = 0;
    private Integer qtdSaida =0;
    private Date dataSaida;
    private MotivoSaida motivoSaida = new MotivoSaida();
    private Produto produto = new Produto();

    public Integer getIdEstoqueSaida() {
        return idEstoqueSaida;
    }

    public void setIdEstoqueSaida(Integer id_estoque_saida) {
        this.idEstoqueSaida = id_estoque_saida;
    }

    public Integer getQtdSaida() {
        return qtdSaida;
    }

    public void setQtdSaida(Integer qtdSaida) {
        this.qtdSaida = qtdSaida;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public MotivoSaida getMotivoSaida() {
        return motivoSaida;
    }

    public void setMotivoSaida(MotivoSaida motivoSaida) {
        this.motivoSaida = motivoSaida;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public EstoqueSaida Clonar(){
        EstoqueSaida estoqueS= new EstoqueSaida();
        
        estoqueS.setIdEstoqueSaida(getIdEstoqueSaida());
        estoqueS.setMotivoSaida(getMotivoSaida());
        estoqueS.setProduto(getProduto());
        estoqueS.setDataSaida(getDataSaida());
        estoqueS.setQtdSaida(getQtdSaida());
            
    return estoqueS;
    }

}
