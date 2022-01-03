package br.com.furafila.mvc.tipoProduto.business;

import java.util.List;

import br.com.furafila.mvc.principal.connectionFactory.BancoDados;
import br.com.furafila.mvc.tipoProduto.model.TipoProduto;
import br.com.furafila.utils.FuraFilaConstants;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class TipoProdutoBusiness {

    public void gravar(TipoProduto tipoProduto) throws Exception {

        String strQuery = "INSERT INTO"
                + " TIPO_PRODUTO "
                + "("
                + "tipo_produto_desc"
                + ") VALUES ("
                + "'" + tipoProduto.getTipoProdutoDesc() + "'"
                + ")";
        
        BancoDados.executaComando(strQuery);
    }

    public void alterar(TipoProduto tipoProduto) throws Exception {

        String strQuery = "UPDATE "
                + "TIPO_PRODUTO "
                + "SET "
                + "tipo_produto_desc = '" + tipoProduto.getTipoProdutoDesc() + "' "
                + "WHERE id_tipo_produto =" + tipoProduto.getIdTipoProduto();

        BancoDados.executaComando(strQuery);

    }

    public void alterarStatusTipoProduto(TipoProduto tipoProduto) throws Exception {

        String strQuery = "UPDATE "
                + "TIPO_PRODUTO "
                + "SET "
                + "status = " + tipoProduto.getStatusSQL()
                + " WHERE id_tipo_produto = " + tipoProduto.getIdTipoProduto();

        BancoDados.executaComando(strQuery);

    }

    public List<String> listarTipoProduto(TipoProduto tipoProduto) throws Exception{
        
        String strQuery = "SELECT TP.tipo_produto_desc FROM TIPO_PRODUTO TP WHERE TP.tipo_produto_desc LIKE '" + tipoProduto.getTipoProdutoDesc() + "'";
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
    
    public List<List<String>> listarTipoProduto(boolean isAdministrador) throws Exception {

        String strQuery = "SELECT "
                + "TP.id_tipo_produto AS [CODIGO],"
                + "TP.tipo_produto_desc AS [TIPO PRODUTO],"
                + "TP.status AS [STATUS]"
                + " FROM TIPO_PRODUTO TP";

        strQuery = isAdministrador ? strQuery + "" : strQuery + " WHERE TP.status = 1";

        return BancoDados.retorna_N_Registros(strQuery);

    }

    public List<List<String>> listarTipoProdutoAtivos() throws Exception {

        String strQuery = "SELECT TP.tipo_produto_desc AS [TIPO PRODUTO]"
                + " FROM TIPO_PRODUTO TP"
                + " WHERE TP.status = " + FuraFilaConstants.COD_ATIVO;

        return BancoDados.retorna_N_Registros(strQuery);

    }

}
