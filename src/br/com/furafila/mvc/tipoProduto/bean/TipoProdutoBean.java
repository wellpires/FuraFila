package br.com.furafila.mvc.tipoProduto.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.furafila.mvc.tipoProduto.business.TipoProdutoBusiness;
import br.com.furafila.mvc.tipoProduto.model.TipoProduto;
import br.com.furafila.mvc.tipoProduto.service.ITipoProdutoService;
import br.com.furafila.mvc.tipoProduto.service.TipoProdutoService;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;
import br.com.furafila.utils.Navegacao;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@SessionScoped
public class TipoProdutoBean implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(TipoProdutoBean.class);
	
	private static final long serialVersionUID = 1L;

	private TipoProduto tipoProduto = null;

	private List<TipoProduto> lstTipoProduto = null;

	private TipoProdutoBusiness tipoProdutoBusiness = null;

	private ITipoProdutoService iTipoProdutoService = null;

	private Boolean flgBotoes = null;

	public String inicializarTipoProduto() {

		try {
			this.tipoProdutoBusiness = new TipoProdutoBusiness();
			this.iTipoProdutoService = new TipoProdutoService();
			this.tipoProduto = new TipoProduto();
			this.flgBotoes = true;
			setLstTipoProduto(this.iTipoProdutoService.listarTipoProduto(true));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		return Navegacao.irTipoProduto();
	}

	public String inicializarNovoTipoProduto() {

		try {
			this.tipoProduto = new TipoProduto();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		return Navegacao.irNovoTipoProduto();
	}

	public void gravar(ActionEvent ae) {

		try {
			if (tipoProdutoExiste()) {
				FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO,
						FuraFilaConstants.AVISO_TIPO_PRODUTO_EXISTE);
				return;
			}
			getTipoProdutoBusiness().gravar(getTipoProduto());

			FuraFilaUtils.growlInfo(FuraFilaConstants.INFO_GROWL_TITULO,
					FuraFilaConstants.INFO_TIPO_PRODUTO_CADASTRADO);

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		FuraFilaUtils.executarJavascript("PF('wgdNovoTipoProduto').hide()");
		setTipoProduto(new TipoProduto());

	}

	public String editar() {

		try {
			getTipoProdutoBusiness().alterar(getTipoProduto());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		return Navegacao.irTipoProduto();

	}

	public String alterarStatus() {

		try {
			getTipoProduto().setStatus(!getTipoProduto().getStatus());

			getTipoProdutoBusiness().alterarStatusTipoProduto(getTipoProduto());

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		return Navegacao.irTipoProduto();
	}

	public String nomeBotaoAtivarDesativar() {
		if (getLstTipoProduto().size() > 0 && null != getTipoProduto().getStatus()) {
			return getTipoProduto().getStatus() ? FuraFilaConstants.DESATIVAR : FuraFilaConstants.ATIVAR;
		} else {
			return FuraFilaConstants.ATIVAR;
		}
	}

	public void habilitarBotoes(SelectEvent event) {
		setFlgBotoes(false);
	}

	public void desabilitarBotoes(UnselectEvent event) {
		setFlgBotoes(true);
	}

	private boolean tipoProdutoExiste() {

		boolean tipoProdutoExiste = false;

		try {
			tipoProdutoExiste = this.iTipoProdutoService.pegarTipoProduto(getTipoProduto()) > 0;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		return tipoProdutoExiste;

	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		if (tipoProduto != null) {
			this.tipoProduto = tipoProduto;
		}
	}

	public List<TipoProduto> getLstTipoProduto() {
		return lstTipoProduto;
	}

	public void setLstTipoProduto(List<TipoProduto> lstTipoProduto) {
		this.lstTipoProduto = lstTipoProduto;
	}

	public TipoProdutoBusiness getTipoProdutoBusiness() {
		return tipoProdutoBusiness;
	}

	public void setTipoProdutoBusiness(TipoProdutoBusiness tipoProdutoBusiness) {
		this.tipoProdutoBusiness = tipoProdutoBusiness;
	}

	public Boolean getFlgBotoes() {
		return flgBotoes;
	}

	public void setFlgBotoes(Boolean flgBotoes) {
		this.flgBotoes = flgBotoes;
	}

}
