package br.com.furafila.mvc.tipoProduto.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.furafila.mvc.tipoProduto.model.TipoProduto;
import br.com.furafila.mvc.tipoProduto.service.TipoProdutoService;
import br.com.furafila.mvc.tipoProduto.service.impl.TipoProdutoServiceImpl;
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

	private static final long serialVersionUID = 4823910999015389026L;

	private static final Logger logger = LogManager.getLogger(TipoProdutoBean.class);

	private TipoProduto tipoProduto = null;

	private List<TipoProduto> lstTipoProduto = null;

	private TipoProdutoService tipoProdutoService = null;

	private Boolean flgBotoes = null;

	public String inicializarTipoProduto() {

		try {
			this.tipoProdutoService = new TipoProdutoServiceImpl();
			this.tipoProduto = new TipoProduto();
			this.flgBotoes = true;
			this.lstTipoProduto = this.tipoProdutoService.listarTipoProduto(true);
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
			tipoProdutoService.gravar(getTipoProduto());

			FuraFilaUtils.growlInfo(FuraFilaConstants.INFO_GROWL_TITULO,
					FuraFilaConstants.INFO_TIPO_PRODUTO_CADASTRADO);

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		FuraFilaUtils.executarJavascript("PF('wgdNovoTipoProduto').hide()");
		setTipoProduto(new TipoProduto());

	}

	public String alterar() {

		try {
			this.tipoProdutoService.alterar(getTipoProduto());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

		return Navegacao.irTipoProduto();

	}

	public String alterarStatus() {

		try {
			getTipoProduto().setStatus(!getTipoProduto().getStatus());

			this.tipoProdutoService.alterarStatus(getTipoProduto());

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

		try {
			return this.tipoProdutoService.pegarTipoProduto(getTipoProduto());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
			return true;
		}

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

	public Boolean getFlgBotoes() {
		return flgBotoes;
	}

	public void setFlgBotoes(Boolean flgBotoes) {
		this.flgBotoes = flgBotoes;
	}

}
