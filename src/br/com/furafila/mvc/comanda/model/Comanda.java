package br.com.furafila.mvc.comanda.model;

import java.io.Serializable;
import java.util.Date;

import br.com.furafila.mvc.cliente.model.Cliente;
import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.formaPagamento.model.FormaPagamento;
import br.com.furafila.mvc.status.model.Status;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Comanda implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id_comanda = "";
    private String codigoPedido = "";
    private Integer qtdTotal = 0;
    private Date dataVenda;
    private Date dataConfirmacao;
    private FormaPagamento formaPagamento = new FormaPagamento();
    private Estabelecimento estabelecimento = new Estabelecimento();
    private Cliente cliente = new Cliente();
    private Status status = new Status();

    public String getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(String id_comanda) {
        this.id_comanda = id_comanda;
    }

    public Integer getQtdTotal() {
        return qtdTotal;
    }

    public void setQtdTotal(Integer qtdTotal) {
        this.qtdTotal = qtdTotal;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Date getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(Date dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Comanda clonar() {
        Comanda comanda = new Comanda();

        comanda.setCliente(getCliente());
        comanda.setDataConfirmacao(getDataConfirmacao());
        comanda.setDataVenda(getDataVenda());
        comanda.setEstabelecimento(getEstabelecimento());
        comanda.setFormaPagamento(getFormaPagamento());
        comanda.setId_comanda(getId_comanda());
        comanda.setQtdTotal(getQtdTotal());
        comanda.setStatus(getStatus());

        return comanda;

    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Boolean getCodigoPedidoVazio() {
        return FuraFilaUtils.valorVazioNulo(codigoPedido);
    }
}
