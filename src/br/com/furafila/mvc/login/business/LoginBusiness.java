package br.com.furafila.mvc.login.business;

import java.util.List;

import br.com.furafila.mvc.login.model.Login;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;
import br.com.furafila.utils.FuraFilaConstants;

/**
 *
 * @author Wellington Gonçalves Pires
 */

public class LoginBusiness {
    
    public int gravar(Login login) throws Exception{
        
        String strQuery = "INSERT INTO "
                + "LOGIN "
                + "("
                + "usuario,"
                + "senha,"
                + "status,"
                + "disponivel_entrega,"
                + "id_permissao_FK"
                + ") "
                + "VALUES"
                + " ("
                + "'" + login.getUsuario() + "',"
                + "'" + login.getSenhaCriptografada()+ "',"
                + login.getStatusSQL() + ","
                + login.getDisponivel_entregaSQL() + ","
                + "'" + login.getPermissao().getIdPermissao() + "')";
        
        BancoDados.executaComando(strQuery);
        
        List<String> registro = BancoDados.retornaRegistro(String.format("select l.id_login from login l where l.usuario ='%s'", login.getUsuario()));
        
        return Integer.parseInt(registro.get(0));
        
    }
    
    public void alterar(Login login) throws Exception{
        
        String strQuery = "UPDATE "
                + "LOGIN "
                + "SET "
                + "usuario = '" + login.getUsuario() + "', "
                + "senha = '" + login.getSenhaCriptografada()+ "' "
                + "WHERE id_login = " + login.getIdLogin();
        
        BancoDados.executaComando(strQuery);
    }
    
    public void excluir(Login login) throws Exception{
        
        String strQuery = "DELETE FROM LOGIN WHERE id_login = " + login.getIdLogin();
        
        BancoDados.executaComando(strQuery);
        
    }
    
    public void alterarStatus(Login login) throws Exception{
        
        String strQuery = "UPDATE LOGIN SET status = " + login.getStatusSQL() + " WHERE id_login = " + login.getIdLogin();
        BancoDados.executaComando(strQuery);
        
    }    
    
    public void alterarDisponibilidade(Login login) throws Exception{
        
        String strQuery = "UPDATE LOGIN SET disponivel_entrega = " + login.getDisponivel_entregaSQL() + " WHERE id_login = " + login.getIdLogin();
        BancoDados.executaComando(strQuery);
        
    }
    
    public List<String> buscarUsuario(Login login) throws Exception{
        
        String strQuery = "SELECT"
                + " L.id_login AS [ID],"
                + " L.usuario AS [USUARIO],"
                + " L.senha AS [SENHA],"
                + " (SELECT P.id_permissao FROM PERMISSAO P WHERE P.id_permissao = L.id_permissao_FK) AS [CD PERMISSAO],"
                + " (SELECT P.sigla_permissao FROM PERMISSAO P WHERE P.id_permissao = L.id_permissao_FK) AS [SIGLA],"
                + " (SELECT P.permissao FROM PERMISSAO P WHERE P.id_permissao = L.id_permissao_FK) AS [PERMISSAO]"
                + " FROM LOGIN L WHERE L.usuario = '" + login.getUsuario() + "' AND L.senha = '" + login.getSenhaCriptografada()+ "'";
        
        return BancoDados.retornaRegistro(strQuery);
        
    }

    public List<String> obterUsuario(Login login, boolean isAlteracao) throws Exception{
        
        String complementoQuery = isAlteracao ? " AND id_login <> " + login.getIdLogin() : "";
        
        String strQuery = "SELECT L.usuario AS [USUARIO] FROM LOGIN L WHERE L.usuario LIKE '" + login.getUsuario() + "'" + complementoQuery;
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
        
    public List<List<String>> listarEntregadores() throws Exception{
        
        String strQuery = "SELECT "
                + "L.id_login AS [CD],"
                + " L.usuario AS [USUARIO],"
                + " L.status AS [STATUS],"
                + " L.disponivel_entrega AS [DISPONIVEL] "
                + "FROM LOGIN L"
                + " WHERE L.id_permissao_FK = (SELECT P.id_permissao FROM PERMISSAO P WHERE P.sigla_permissao = '" + FuraFilaConstants.SIGLA_PERFIL_4 + "')";
        
        return BancoDados.retorna_N_Registros(strQuery);
        
    }

}
