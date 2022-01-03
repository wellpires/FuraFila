package br.com.furafila.mvc.estabelecimentoLogin.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estabelecimentoLogin.business.EstabelecimentoLoginBusiness;
import br.com.furafila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import br.com.furafila.mvc.estabelecimentoLogin.service.EstabelecimentoLoginService;
import br.com.furafila.mvc.login.business.LoginBusiness;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;
import br.com.furafila.utils.Navegacao;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@ViewScoped
public class EstabelecimentoLoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private EstabelecimentoLoginService estabelecimentoLoginService = new EstabelecimentoLoginService();

    private EstabelecimentoLoginBusiness estabelecimentoLoginBusiness = new EstabelecimentoLoginBusiness();
    private LoginBusiness loginBusiness = new LoginBusiness();
    
    private List<EstabelecimentoLogin> lstEstabelecimentoLogin = new ArrayList<>();
    
    private EstabelecimentoLogin estabelecimentoLogin = new EstabelecimentoLogin();
    
    private Boolean flgBtnExcluir = true;
    
    public void init() {

        try {
            
            EstabelecimentoLogin el = (EstabelecimentoLogin) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO_LOGIN);
            setLstEstabelecimentoLogin(getEstabelecimentoLoginService().listarUsuarios(el));
            
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public String gravar(){
        
        try {
            
            getEstabelecimentoLogin().setEstabelecimento((Estabelecimento) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO));
            
            getEstabelecimentoLogin().getLogin().getPermissao().setIdPermissao(FuraFilaConstants.CODIGO_PERFIL_2);
            getEstabelecimentoLogin().getLogin().setStatus(Boolean.TRUE);
            getEstabelecimentoLogin().getLogin().setDisponivelEntrega(Boolean.FALSE);
            int idLogin = getLoginBusiness().gravar(getEstabelecimentoLogin().getLogin());
            getEstabelecimentoLogin().getLogin().setIdLogin(idLogin);
            
            getEstabelecimentoLoginBusiness().gravar(getEstabelecimentoLogin());
            
            setEstabelecimentoLogin(new EstabelecimentoLogin());
            
            FuraFilaUtils.executarJavascript("PF('wgdAddUsuario').hide()");
            
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
        
        return Navegacao.irUsuarios();
        
    }
    
    public String excluir(){
        
        try {
            
            getEstabelecimentoLoginBusiness().excluir(estabelecimentoLogin);
            
            getLoginBusiness().excluir((getEstabelecimentoLogin().getLogin()));
            
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
        
        return Navegacao.irUsuarios();
        
    }
    
    public String alterar(){
        
        try {
            getLoginBusiness().alterar(getEstabelecimentoLogin().getLogin());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
        
        return Navegacao.irUsuarios();
        
    }
    
    public void habilitarBotaoExcluir(SelectEvent event){
        setFlgBtnExcluir(false);
    }
    
    public void desabilitarBotaoExcluir(UnselectEvent event){
        setFlgBtnExcluir(true);
    }
    
    public EstabelecimentoLoginService getEstabelecimentoLoginService() {
        return estabelecimentoLoginService;
    }

    public void setEstabelecimentoLoginService(EstabelecimentoLoginService estabelecimentoLoginService) {
        this.estabelecimentoLoginService = estabelecimentoLoginService;
    }

    public List<EstabelecimentoLogin> getLstEstabelecimentoLogin() {
        return lstEstabelecimentoLogin;
    }

    public void setLstEstabelecimentoLogin(List<EstabelecimentoLogin> lstEstabelecimentoLogin) {
        this.lstEstabelecimentoLogin = lstEstabelecimentoLogin;
    }

    public LoginBusiness getLoginBusiness() {
        return loginBusiness;
    }

    public void setLoginBusiness(LoginBusiness loginBusiness) {
        this.loginBusiness = loginBusiness;
    }

    public EstabelecimentoLogin getEstabelecimentoLogin() {
        return estabelecimentoLogin;
    }

    public void setEstabelecimentoLogin(EstabelecimentoLogin estabelecimentoLogin) {
        if(estabelecimentoLogin == null){
            estabelecimentoLogin = new EstabelecimentoLogin();
        }
        this.estabelecimentoLogin = estabelecimentoLogin;
    }

    public EstabelecimentoLoginBusiness getEstabelecimentoLoginBusiness() {
        return estabelecimentoLoginBusiness;
    }

    public void setEstabelecimentoLoginBusiness(EstabelecimentoLoginBusiness estabelecimentoLoginBusiness) {
        this.estabelecimentoLoginBusiness = estabelecimentoLoginBusiness;
    }

    public Boolean getFlgBtnExcluir() {
        return flgBtnExcluir;
    }

    public void setFlgBtnExcluir(Boolean flgBtnExcluir) {
        this.flgBtnExcluir = flgBtnExcluir;
    }

}
