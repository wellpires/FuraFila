package br.com.furafila.mvc.login.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.furafila.mvc.cliente.model.Cliente;
import br.com.furafila.mvc.cliente.service.ClienteService;
import br.com.furafila.mvc.estabelecimento.service.EstabelecimentoService;
import br.com.furafila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import br.com.furafila.mvc.estabelecimentoLogin.service.EstabelecimentoLoginService;
import br.com.furafila.mvc.login.model.Login;
import br.com.furafila.mvc.login.service.LoginService;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    private Login login = new Login();
    private LoginService loginService = new LoginService();
    private ClienteService clienteService = new ClienteService();
    private EstabelecimentoService estabelecimentoService = new EstabelecimentoService();
    private EstabelecimentoLoginService estabelecimentoLoginService = new EstabelecimentoLoginService();

    public String logarSe() {

        try {

            Login l = getLoginService().logarSe(getLogin());

            if (getLogin().getUsuario().equals(l.getUsuario()) && getLogin().getSenhaCriptografada().equals(l.getSenha())) {
                setLogin(l);
                getLogin().setSenha("");
                Authentication request = new UsernamePasswordAuthenticationToken(getLogin().getPermissao().getSigla_permissao(), FuraFilaConstants.SENHA_PADRAO);
                Authentication result = getAuthenticationManager().authenticate(request);
                SecurityContextHolder.getContext().setAuthentication(result);

                if (getLogin().getPermissao().getId_permissao() == FuraFilaConstants.CODIGO_PERFIL_2) {

                    // LOJISTA
                    EstabelecimentoLogin estabelecimentoLogin = new EstabelecimentoLogin();
                    estabelecimentoLogin.setLogin(getLogin());
                    getEstabelecimentoLoginService().buscarInformacoesIniciaisEstabelecimento(estabelecimentoLogin);

                    if (!estabelecimentoLogin.getEstabelecimento().getStatus()) {
                        FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.AVISO_CADASTRO_NAO_ATIVO);
                        return "";
                    }
                    
                    FuraFilaUtils.passarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO, estabelecimentoLogin.getEstabelecimento());
                    FuraFilaUtils.passarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO_LOGIN, estabelecimentoLogin);
                    
                    
                    
                } else if (getLogin().getPermissao().getId_permissao() == FuraFilaConstants.CODIGO_PERFIL_3) {

                    // CLIENTE
                    Cliente cliente = new Cliente();
                    cliente.setLogin(getLogin());
                    FuraFilaUtils.passarDadosSessao(FuraFilaConstants.SESSAO_CLIENTE, getClienteService().buscarDadosBasicosCliente(cliente));

                }
            } else {
                FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.AVISO_FALHA_LOGIN);
                return "";
            }

        } catch (Exception e) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, e.getMessage());
            return "";
        }

        return getLogin().getPermissao().getSigla_permissao();

    }

    public String logout() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        SecurityContextHolder.clearContext();
        return "logout";

    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public EstabelecimentoService getEstabelecimentoService() {
        return estabelecimentoService;
    }

    public void setEstabelecimentoService(EstabelecimentoService estabelecimentoService) {
        this.estabelecimentoService = estabelecimentoService;
    }

    public EstabelecimentoLoginService getEstabelecimentoLoginService() {
        return estabelecimentoLoginService;
    }

    public void setEstabelecimentoLoginService(EstabelecimentoLoginService estabelecimentoLoginService) {
        this.estabelecimentoLoginService = estabelecimentoLoginService;
    }

}
