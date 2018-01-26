package br.com.furafila.mvc.pedidoLocker.model;

import br.com.furafila.mvc.locker.model.Locker;
import br.com.furafila.mvc.pedidos.model.Pedidos;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class PedidoLocker {
    
    private Integer idPedidoLocker;
    private Locker locker = new Locker();
    private Pedidos pedidos = new Pedidos();

    public Integer getIdPedidoLocker() {
        return idPedidoLocker;
    }

    public void setIdPedidoLocker(Integer id_pedido_locker) {
        this.idPedidoLocker = id_pedido_locker;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }
    
}
