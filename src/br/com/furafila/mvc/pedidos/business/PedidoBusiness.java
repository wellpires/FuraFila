package br.com.furafila.mvc.pedidos.business;

import br.com.furafila.mvc.pedidos.model.Pedidos;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class PedidoBusiness {
    
    public void gravarPedido(Pedidos pedido) throws Exception{
        
        String strQuery = "INSERT INTO PEDIDOS ("
                + "id_produto_FK,"
                + "qtd,"
                + "id_comanda_FK"
                + ") VALUES ("
                + pedido.getProduto().getId_produto()
                + "," + pedido.getQtd()
                + "," + pedido.getComanda().getId_comanda()
                + ")";
        pedido.setId_pedido(BancoDados.inserirRetornaID(strQuery));
        
    }
    
}
