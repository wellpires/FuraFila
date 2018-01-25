package br.com.furafila.mvc.pedidoLocker.business;

import br.com.furafila.mvc.pedidoLocker.model.PedidoLocker;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class PedidoLockerBusiness {
    
    public void gravar(PedidoLocker pedidoLocker) throws Exception{
        
        String strQuery = "INSERT INTO PEDIDO_LOCKER ("
                + "id_locker_FK,"
                + "id_pedido_FK"
                + ") VALUES ("
                + pedidoLocker.getLocker().getId_locker()
                + "," + pedidoLocker.getPedidos().getId_pedido()
                + ")";
        
        BancoDados.executaComando(strQuery);
        
    }
    
}
