package br.com.furafila.mvc.estabelecimento.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import br.com.furafila.mvc.cliente.service.ImagemService;
import br.com.furafila.mvc.cliente.service.impl.ImagemServiceImpl;
import br.com.furafila.mvc.estabelecimento.business.EstabelecimentoBusiness;
import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estabelecimento.service.EstabelecimentoService;
import br.com.furafila.mvc.estabelecimento.service.impl.EstabelecimentoServiceImpl;
import br.com.furafila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import br.com.furafila.mvc.estoque.business.EstoqueBusiness;
import br.com.furafila.mvc.estoque.model.Estoque;
import br.com.furafila.mvc.estoque.service.EstoqueService;
import br.com.furafila.mvc.estoque.service.impl.EstoqueServiceImpl;
import br.com.furafila.mvc.estoqueProdutos.model.EstoqueProdutos;
import br.com.furafila.mvc.imagem.business.ImagemBusiness;
import br.com.furafila.mvc.imagem.model.Imagem;
import br.com.furafila.mvc.login.business.LoginBusiness;
import br.com.furafila.mvc.login.service.LoginService;
import br.com.furafila.mvc.login.service.impl.LoginServiceImpl;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;
import br.com.furafila.utils.Navegacao;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@SessionScoped
public class EstabelecimentoBean implements Serializable {

	private static final Logger logger = LogManager.getLogger(EstabelecimentoBean.class);

	private static final long serialVersionUID = 1L;

	private List<Estabelecimento> lstEstabelecimentos = new ArrayList<>();

	private Estabelecimento estabelecimento = new Estabelecimento();
	private EstabelecimentoLogin estabelecimentoLogin = new EstabelecimentoLogin();
	private Estoque estoque = new Estoque();

	private EstabelecimentoBusiness estabelecimentoBusiness = new EstabelecimentoBusiness();
	private LoginBusiness loginBusiness = new LoginBusiness();
	private ImagemBusiness imagemBusiness = new ImagemBusiness();
	private EstoqueBusiness estoqueBusiness = new EstoqueBusiness();

	private EstabelecimentoService estabelecimentoService = new EstabelecimentoServiceImpl();
	private EstoqueService estoqueService = new EstoqueServiceImpl();
	private ImagemService imagemService = new ImagemServiceImpl();
	private LoginService loginService = new LoginServiceImpl();

	public void listarEstabelecimentos() {
		try {
			setEstabelecimento(new Estabelecimento());
			setLstEstabelecimentos(getEstabelecimentoService().listarEstabelecimentos());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}
	}

	public void gerarRelatorio(ActionEvent ae) {

		try {
			FuraFilaUtils.gerarRelatorios("estabelecimentoVendas",
					getEstabelecimentoService().listarEstabelecimentoMaisVendem(), "ESTABELECIMENTOS_VENDAS");
		} catch (JRException ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

	}

	public String gravar() {

		try {

			this.estabelecimentoLogin.getLogin().getPermissao().setIdPermissao(FuraFilaConstants.CODIGO_PERFIL_2);
			this.estabelecimentoLogin.getLogin().setStatus(Boolean.TRUE);
			this.estabelecimentoLogin.getLogin().setDisponivelEntrega(Boolean.FALSE);
			int loginId = loginService.gravarLogin(getEstabelecimentoLogin().getLogin());

			Imagem imagem = new Imagem();
			imagem.setImagem(FuraFilaConstants.SEM_IMAGEM_ESTABELECIMENTO);
			Long idImagem = imagemService.gravar(imagem);
			imagem.setIdImagem(idImagem.intValue());

			this.estabelecimento.setLoginId(Long.valueOf(loginId));
			this.estabelecimento.setImagem(imagem);
			this.estabelecimentoService.gravar(getEstabelecimento());

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
			return "";
		}

		zerarLogin();
		zerarEstabelecimento();
		return Navegacao.irIndex();

	}

	public void alterar(ActionEvent ae) {

		try {

			this.imagemService.alterar(this.estabelecimento.getImagem());

			this.estabelecimentoService.alterar(this.estabelecimento);

			getEstabelecimentoLoginSessao().getEstabelecimento().setImagem(getEstabelecimento().getImagem());
			getEstabelecimentoLoginSessao().getEstabelecimento().setRazaoSocial(getEstabelecimento().getRazaoSocial());

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

	}

	public String ativarEmpresa() {

		try {
			alterarStatus(Boolean.TRUE);

			getEstoque().setEstabelecimento(getEstabelecimento());

			if (!getEstoqueService().estoqueExiste(getEstoque())) {
				getEstoque().setEstabelecimento(getEstabelecimento());
				estoqueService.criarEstoque(getEstoque());
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		return Navegacao.irEstabelecimentosCadastrados();

	}

	public String desativarEmpresa() {

		try {
			alterarStatus(Boolean.FALSE);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		return Navegacao.irEstabelecimentosCadastrados();

	}

	public void alterarStatus(Boolean status) throws Exception {

		getEstabelecimento().setStatus(status);

		this.estabelecimentoService.alterarStatus(estabelecimento);

	}

	public void iniciarTabelaEstabelecimentos(ActionEvent ae) {

		try {
			setLstEstabelecimentos(getEstabelecimentoService().listarEstabelecimentos());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

	}

	public void listarInfoEstabelecimento(ActionEvent ae) {

		try {
			Estabelecimento estabelecimentoSessao = pegarEstabelecimentoSessao();

			Estabelecimento estabelecimento = this.estabelecimentoService
					.buscarInformacaoEstabelecimento(estabelecimentoSessao.getIdEstabelecimento());

			estabelecimento.getImagem().setImagem(estabelecimentoSessao.getImagem().getImagem());
			this.estabelecimento = estabelecimento;

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}
	}

	public void carregar(FileUploadEvent event) {

		try {

			EstoqueProdutos ep = new EstoqueProdutos();
			ep.getEstoque().setEstabelecimento(getEstabelecimento());

			String ext[] = event.getFile().getFileName().split("\\.");
			File tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),
					".".concat(ext[ext.length - 1]));
			FileUtils.copyInputStreamToFile(event.getFile().getInputstream(), tempFile);

			this.estabelecimento.getImagem().setImagem(tempFile.getAbsolutePath());

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

	}

	private Estabelecimento pegarEstabelecimentoSessao() {
		return (Estabelecimento) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO);
	}

	public EstabelecimentoLogin getEstabelecimentoLoginSessao() {
		return (EstabelecimentoLogin) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO_LOGIN);
	}

	public void zerarEstabelecimento() {
		getEstabelecimento().zerarObjeto();
	}

	public void zerarLogin() {
		getEstabelecimentoLogin().getLogin().setUsuario("");
	}

	public EstabelecimentoBusiness getEstabelecimentoBusiness() {
		return estabelecimentoBusiness;
	}

	public void setEstabelecimentoBusiness(EstabelecimentoBusiness estabelecimentoBusiness) {
		this.estabelecimentoBusiness = estabelecimentoBusiness;
	}

	public LoginBusiness getLoginBusiness() {
		return loginBusiness;
	}

	public void setLoginBusiness(LoginBusiness loginBusiness) {
		this.loginBusiness = loginBusiness;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		if (estabelecimento != null && !estabelecimento.objetoVazio()) {
			this.estabelecimento = estabelecimento;
		}
	}

	public List<Estabelecimento> getLstEstabelecimentos() {
		return lstEstabelecimentos;
	}

	public void setLstEstabelecimentos(List<Estabelecimento> lstEstabelecimentos) {
		this.lstEstabelecimentos = lstEstabelecimentos;
	}

	public EstabelecimentoService getEstabelecimentoService() {
		return estabelecimentoService;
	}

	public void setEstabelecimentoService(EstabelecimentoServiceImpl estabelecimentoService) {
		this.estabelecimentoService = estabelecimentoService;
	}

	public EstabelecimentoLogin getEstabelecimentoLogin() {
		return estabelecimentoLogin;
	}

	public void setEstabelecimentoLogin(EstabelecimentoLogin estabelecimentoLogin) {
		this.estabelecimentoLogin = estabelecimentoLogin;
	}

	public ImagemBusiness getImagemBusiness() {
		return imagemBusiness;
	}

	public void setImagemBusiness(ImagemBusiness imagemBusiness) {
		this.imagemBusiness = imagemBusiness;
	}

	public EstoqueBusiness getEstoqueBusiness() {
		return estoqueBusiness;
	}

	public void setEstoqueBusiness(EstoqueBusiness estoqueBusiness) {
		this.estoqueBusiness = estoqueBusiness;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public EstoqueService getEstoqueService() {
		return estoqueService;
	}

	public void setEstoqueService(EstoqueServiceImpl estoqueService) {
		this.estoqueService = estoqueService;
	}

}
