package br.com.furafila.mvc.estabelecimentoLogin.business;

import java.util.List;

import br.com.furafila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstabelecimentoLoginBusiness {

    public void gravar(EstabelecimentoLogin estabelecimentoLogin) throws Exception {

        String strQuery = "INSERT INTO "
                + "ESTABELECIMENTO_LOGIN "
                + "("
                + "id_estabelecimento_FK,"
                + "id_login_FK"
                + ") VALUES ("
                + "'" + estabelecimentoLogin.getEstabelecimento().getIdEstabelecimento() + "',"
                + "'" + estabelecimentoLogin.getLogin().getIdLogin() + "')";

        BancoDados.executaComando(strQuery);

    }

    public void excluir(EstabelecimentoLogin estabelecimentoLogin) throws Exception {
        
        String strQuery = "DELETE FROM ESTABELECIMENTO_LOGIN WHERE id_login_FK = " + estabelecimentoLogin.getLogin().getIdLogin();
        
        BancoDados.executaComando(strQuery);
        
    }

    public List<String> buscarInformacoesIniciaisEstabelecimento(EstabelecimentoLogin estabelecimentoLogin) throws Exception {

    	String strQuery = String.format("select e.id_estabelecimento, e.razao_social, e.status, i.id_imagem, i.imagem from estabelecimento e "
    			+ "inner join estabelecimento_login el ON el.id_estabelecimento_fk = e.id_estabelecimento "
    			+ "inner join imagem i on i.id_imagem = e.id_imagem_fk where el.id_login_fk = %d;", estabelecimentoLogin.getLogin().getIdLogin());

        return BancoDados.retornaRegistro(strQuery);

    }

    public List<List<String>> listarUsuarios(EstabelecimentoLogin estabelecimentoLogin) throws Exception {

        String strQuery = "SELECT "
                +  "(SELECT L.id_login FROM LOGIN L WHERE L.id_login = EL.id_login_FK) AS [CD LOGIN],"
                + "(SELECT L.usuario FROM LOGIN L WHERE L.id_login = EL.id_login_FK) AS [LOGIN]"
                + " FROM ESTABELECIMENTO_LOGIN EL "
                + "WHERE EL.id_estabelecimento_FK = " + estabelecimentoLogin.getEstabelecimento().getIdEstabelecimento()
                + " AND EL.id_login_FK <> " +estabelecimentoLogin.getLogin().getIdLogin();

        return BancoDados.retorna_N_Registros(strQuery);
        
    }
    
    

}
