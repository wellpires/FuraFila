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
                + pedido.getProduto().getIdProduto()
                + "," + pedido.getQtd()
                + "," + pedido.getComanda().getIdComanda()
                + ")";
        pedido.setIdPedido(BancoDados.inserirRetornaID(strQuery));
        
    }
    
}
