package br.com.furafila.mvc.pedidos.model;

import br.com.furafila.mvc.comanda.model.Comanda;
import br.com.furafila.mvc.produto.model.Produto;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Pedidos {

    private Integer idPedido = 0;
    private Integer qtd = 0;
    private Produto produto = new Produto();
    private Comanda comanda = new Comanda();

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer id_pedido) {
        this.idPedido = id_pedido;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void getSomarQtd() {
        setQtd(this.qtd++);
    }

    public void getSubtQtd() {
        setQtd(this.qtd--);
    }

    public void manipularQtd(char operacao) {
        if (operacao == '+') {
            setQtd(+1);
        } else if (operacao == '-') {
            if (getQtd() > 0) {
                setQtd(-1);
            }
        }
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Double getSubTotal() {
        if (getQtd() != null && getProduto() != null) {
            return getQtd() * getProduto().getValorUnitario();
        } else {
            return 0.0;
        }
    }

    public String getSubTotalExibicao() {

        if (getQtd() != null && getProduto() != null) {
            return FuraFilaUtils.formatarMoeda(getQtd() * getProduto().getValorUnitario());
        } else {
            return FuraFilaUtils.formatarMoeda(0.0);
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Pedidos clonar() {
        Pedidos pedidos = new Pedidos();

        pedidos.setIdPedido(getIdPedido());
        pedidos.setQtd(getQtd());
        pedidos.setProduto(getProduto().clonar());
        pedidos.setComanda(getComanda().clonar());

        return pedidos;
    }

    public void somarQtdeProdutos(int qtdProduto) {
        setQtd(getQtd() + qtdProduto);
    }


}
