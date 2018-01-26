
package br.com.furafila.mvc.estoqueEntrada.model;

import java.util.Date;

import br.com.furafila.mvc.motivoEntrada.model.MotivoEntrada;
import br.com.furafila.mvc.produto.model.Produto;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class EstoqueEntrada {
    private Integer idEstoqueEntrada = 0;
    private Integer qtdEntrada = 0;
    private Date dataEntrada;
    private Produto produto = new Produto();
    private MotivoEntrada motivoEntrada = new MotivoEntrada();

    public Integer getIdEstoqueEntrada() {
        return idEstoqueEntrada;
    }

    public void setIdEstoqueEntrada(Integer id_estoque_entrada) {
        this.idEstoqueEntrada = id_estoque_entrada;
    }

    public Integer getQtdEntrada() {
        return qtdEntrada;
    }

    public void setQtdEntrada(Integer qtdEntrada) {
        this.qtdEntrada = qtdEntrada;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public MotivoEntrada getMotivoEntrada() {
        return motivoEntrada;
    }

    public void setMotivoEntrada(MotivoEntrada motivoEntrada) {
        this.motivoEntrada = motivoEntrada;
    }
    public EstoqueEntrada clonar(){
        
        EstoqueEntrada ee = new EstoqueEntrada();
        
        ee.setDataEntrada(getDataEntrada());
        ee.setIdEstoqueEntrada(getIdEstoqueEntrada());
        ee.setMotivoEntrada(getMotivoEntrada());
        ee.setProduto(ee.getProduto());
        ee.setQtdEntrada(ee.getQtdEntrada());
        
        return ee;
    }
    
}
