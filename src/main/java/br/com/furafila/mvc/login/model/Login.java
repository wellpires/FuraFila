package br.com.furafila.mvc.login.model;

import br.com.furafila.mvc.permissao.model.Permissao;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Login {

    private Integer idLogin = 0;
    private String usuario = "";
    private String senha = "";
    private Boolean status;
    private Boolean disponivelEntrega;
    private Permissao permissao = new Permissao();

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer id_login) {
        this.idLogin = id_login;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenhaCriptografada() throws Exception {
        return FuraFilaUtils.criptografarTexto(getSenha());
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public String getStatusExibicao() {
        return status ? FuraFilaConstants.ATIVO : FuraFilaConstants.INATIVO;
    }
    
    public Character getStatusSQL() {
        return status ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO;
    }
    
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDisponivel_entregaExibicao() {
        return disponivelEntrega ? FuraFilaConstants.DISPONIVEL : FuraFilaConstants.INDISPONIVEL;
    }

    public Character getDisponivel_entregaSQL() {
        return disponivelEntrega ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO;
    }
    
    public Boolean getDisponivelEntrega() {
        return disponivelEntrega;
    }

    public void setDisponivelEntrega(Boolean disponivel_entrega) {
        this.disponivelEntrega = disponivel_entrega;
    }

    public Login clonar() throws Exception {

        Login login = new Login();

        login.setIdLogin(getIdLogin());
        login.setUsuario(getUsuario());
        login.setSenha(getSenha());
        login.setPermissao(getPermissao());
        login.setDisponivelEntrega(getDisponivelEntrega());
        login.setStatus(getStatus());

        return login;
    }

    public void zerarObjeto(){
        this.setIdLogin((Integer) 0);
        this.setUsuario("");
        this.setSenha("");
        this.setDisponivelEntrega(Boolean.FALSE);
        this.setStatus(Boolean.FALSE);
        getPermissao().zerarObjeto();
    }

}
