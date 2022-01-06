package br.com.furafila.mvc.estabelecimentoLogin.model;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.login.model.Login;

/**
 *
 * @author Wellington Gonçalves Pires
 */

public class EstabelecimentoLogin {
    
    private Integer idEstabelecimentoLogin;
    private Estabelecimento estabelecimento = new Estabelecimento();
    private Login login = new Login();

    public Integer getIdEstabelecimentoLogin() {
        return idEstabelecimentoLogin;
    }

    public void setIdEstabelecimentoLogin(Integer id_estabelecimento_login) {
        this.idEstabelecimentoLogin = id_estabelecimento_login;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public EstabelecimentoLogin clonar() {
        
        EstabelecimentoLogin el = new EstabelecimentoLogin();
        
        el.setIdEstabelecimentoLogin(getIdEstabelecimentoLogin());
        el.setEstabelecimento(getEstabelecimento());
        el.setLogin(getLogin());
        
        return el;
        
    }
    
}
