package br.com.furafila.mvc.estabelecimentoLogin.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estabelecimento.service.EstabelecimentoService;
import br.com.furafila.mvc.estabelecimento.service.impl.EstabelecimentoServiceImpl;
import br.com.furafila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import br.com.furafila.mvc.estabelecimentoLogin.service.EstabelecimentoLoginService;
import br.com.furafila.mvc.login.business.LoginBusiness;
import br.com.furafila.mvc.login.service.LoginService;
import br.com.furafila.mvc.login.service.impl.LoginServiceImpl;
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

	private static final Logger logger = LogManager.getLogger(EstabelecimentoLoginBean.class);

	private EstabelecimentoLoginService estabelecimentoLoginService = new EstabelecimentoLoginService();
	private EstabelecimentoService estabelecimentoService = new EstabelecimentoServiceImpl();
	private LoginService loginService = new LoginServiceImpl();

	private LoginBusiness loginBusiness = new LoginBusiness();

	private List<EstabelecimentoLogin> lstEstabelecimentoLogin = new ArrayList<>();

	private EstabelecimentoLogin estabelecimentoLogin = new EstabelecimentoLogin();

	private Boolean flgBtnExcluir = true;

	public void init() {

		try {

			EstabelecimentoLogin el = (EstabelecimentoLogin) FuraFilaUtils
					.pegarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO_LOGIN);
			this.lstEstabelecimentoLogin = this.estabelecimentoLoginService.listarUsuarios(el);

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

	}

	public String gravar() {

		try {

			this.estabelecimentoLogin.setEstabelecimento(
					(Estabelecimento) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO));

			this.estabelecimentoLogin.getLogin().getPermissao().setIdPermissao(FuraFilaConstants.CODIGO_PERFIL_2);
			this.estabelecimentoLogin.getLogin().setStatus(Boolean.TRUE);
			this.estabelecimentoLogin.getLogin().setDisponivelEntrega(Boolean.FALSE);

			int idLogin = this.loginService.gravarLogin(this.estabelecimentoLogin.getLogin());

			this.estabelecimentoLogin.getLogin().setIdLogin(idLogin);
			this.estabelecimentoService.adicionarUsuarioEstabelecimento(this.estabelecimentoLogin);

			this.estabelecimentoLogin = new EstabelecimentoLogin();

			FuraFilaUtils.executarJavascript("PF('wgdAddUsuario').hide()");

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		return Navegacao.irUsuarios();

	}

	public String excluir() {

		try {

			this.estabelecimentoLoginService.deletarEstabelecimentoUsuario(estabelecimentoLogin);

			this.loginService.deletar(getEstabelecimentoLogin().getLogin());

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		return Navegacao.irUsuarios();

	}

	public String alterar() {

		try {
			loginService.alterar(this.estabelecimentoLogin.getLogin());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		return Navegacao.irUsuarios();

	}

	public void habilitarBotaoExcluir(SelectEvent event) {
		setFlgBtnExcluir(false);
	}

	public void desabilitarBotaoExcluir(UnselectEvent event) {
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
		if (estabelecimentoLogin == null) {
			estabelecimentoLogin = new EstabelecimentoLogin();
		}
		this.estabelecimentoLogin = estabelecimentoLogin;
	}

	public Boolean getFlgBtnExcluir() {
		return flgBtnExcluir;
	}

	public void setFlgBtnExcluir(Boolean flgBtnExcluir) {
		this.flgBtnExcluir = flgBtnExcluir;
	}

}
