package br.com.furafila.mvc.estabelecimento.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;

import br.com.furafila.mvc.estabelecimento.business.EstabelecimentoBusiness;
import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estabelecimento.service.EstabelecimentoService;
import br.com.furafila.mvc.estabelecimentoLogin.business.EstabelecimentoLoginBusiness;
import br.com.furafila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import br.com.furafila.mvc.estoque.business.EstoqueBusiness;
import br.com.furafila.mvc.estoque.model.Estoque;
import br.com.furafila.mvc.estoque.service.EstoqueService;
import br.com.furafila.mvc.estoqueProdutos.model.EstoqueProdutos;
import br.com.furafila.mvc.imagem.business.ImagemBusiness;
import br.com.furafila.mvc.login.business.LoginBusiness;
import br.com.furafila.utils.EnviarEmails;
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

    private static final long serialVersionUID = 1L;

    private List<Estabelecimento> lstEstabelecimentos = new ArrayList<>();

    private Estabelecimento estabelecimento = new Estabelecimento();
    private EstabelecimentoLogin estabelecimentoLogin = new EstabelecimentoLogin();
    private Estoque estoque = new Estoque();

    private EstabelecimentoBusiness estabelecimentoBusiness = new EstabelecimentoBusiness();
    private LoginBusiness loginBusiness = new LoginBusiness();
    private EstabelecimentoLoginBusiness estabelecimentoLoginBusiness = new EstabelecimentoLoginBusiness();
    private ImagemBusiness imagemBusiness = new ImagemBusiness();
    private EstoqueBusiness estoqueBusiness = new EstoqueBusiness();

    private EstabelecimentoService estabelecimentoService = new EstabelecimentoService();
    private EstoqueService estoqueService = new EstoqueService();

    public void listarEstabelecimentos() {
        try {
            setEstabelecimento(new Estabelecimento());
            setLstEstabelecimentos(getEstabelecimentoService().listarEstabelecimentos());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public void gerarRelatorio(ActionEvent ae){

        try {
            FuraFilaUtils.gerarRelatorios("estabelecimentoVendas", getEstabelecimentoService().listarEstabelecimentoMaisVendem(), "ESTABELECIMENTOS_VENDAS");
        } catch (JRException ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        } catch (IOException ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }
    
    public String gravar() {

        try {
            
//            if(!FuraFilaValidadores.validarEstabelecimento(getEstabelecimento())){
//                return "";
//            }
//            if(!FuraFilaValidadores.validarLogin(getEstabelecimentoLogin().getLogin())){
//                return "";
//            }
            
//            getImagemBusiness().gravar(getEstabelecimento().getImagem());

            getEstabelecimento().setStatus(Boolean.FALSE);
            getEstabelecimentoBusiness().gravar(getEstabelecimento());

            getEstabelecimentoLogin().getLogin().getPermissao().setIdPermissao(FuraFilaConstants.CODIGO_PERFIL_2);
            getEstabelecimentoLogin().getLogin().setStatus(Boolean.TRUE);
            getEstabelecimentoLogin().getLogin().setDisponivelEntrega(Boolean.FALSE);
            getLoginBusiness().gravar(getEstabelecimentoLogin().getLogin());

            getEstabelecimentoLogin().getEstabelecimento().setIdEstabelecimento(getEstabelecimento().getIdEstabelecimento());
            getEstabelecimentoLoginBusiness().gravar(getEstabelecimentoLogin());

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

        zerarLogin();
        zerarEstabelecimento();
        return Navegacao.irIndex();

    }

    public void alterar(ActionEvent ae) {

        try {

            getImagemBusiness().alterar(getEstabelecimento().getImagem());

            getEstabelecimentoBusiness().alterar(getEstabelecimento());
            
            getEstabelecimentoLoginSessao().getEstabelecimento().setImagem(getEstabelecimento().getImagem());
            getEstabelecimentoLoginSessao().getEstabelecimento().setRazaoSocial(getEstabelecimento().getRazaoSocial());
            
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public String ativarEmpresa() {

        try {
            alterarStatus(Boolean.TRUE);

            getEstoque().setEstabelecimento(getEstabelecimento());

            if (!getEstoqueService().estoqueExiste(getEstoque())) {
                getEstoque().setEstabelecimento(getEstabelecimento());
                getEstoqueBusiness().gravar(getEstoque());
            }

            EnviarEmails.enviarEmailBoasVindas(getEstabelecimento());

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

        return Navegacao.irEstabelecimentosCadastrados();

    }

    public String desativarEmpresa() {

        try {
            alterarStatus(Boolean.FALSE);
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

        return Navegacao.irEstabelecimentosCadastrados();

    }

    public void alterarStatus(Boolean status) throws Exception {

        getEstabelecimento().setStatus(status);

        getEstabelecimentoBusiness().alterarStatus(getEstabelecimento());

    }

    public void iniciarTabelaEstabelecimentos(ActionEvent ae) {

        try {
            setLstEstabelecimentos(getEstabelecimentoService().listarEstabelecimentos());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void listarInfoEstabelecimento() {

        try {
            setEstabelecimento(pegarEstabelecimentoSessao());

            getEstabelecimentoService().buscarInformacaoEstabelecimento(getEstabelecimento());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public void carregar(FileUploadEvent event) {

        try {

            EstoqueProdutos ep = new EstoqueProdutos();
            ep.getEstoque().setEstabelecimento(getEstabelecimento());

            String ext[] = event.getFile().getFileName().split("\\.");

            String caminho = FuraFilaUtils.montarCaminho(null, getEstabelecimentoLoginSessao(), false);
            String nomeImagem = FuraFilaUtils.montarNomeImagem(null, ep, false) + "." + ext[ext.length - 1];

            getEstabelecimento().getImagem().setImagem(FuraFilaUtils.copiarArquivo(caminho + nomeImagem, event.getFile().getInputstream()));

        } catch (Exception ex) {
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

    public void setEstabelecimentoService(EstabelecimentoService estabelecimentoService) {
        this.estabelecimentoService = estabelecimentoService;
    }

    public EstabelecimentoLogin getEstabelecimentoLogin() {
        return estabelecimentoLogin;
    }

    public void setEstabelecimentoLogin(EstabelecimentoLogin estabelecimentoLogin) {
        this.estabelecimentoLogin = estabelecimentoLogin;
    }

    public EstabelecimentoLoginBusiness getEstabelecimentoLoginBusiness() {
        return estabelecimentoLoginBusiness;
    }

    public void setEstabelecimentoLoginBusiness(EstabelecimentoLoginBusiness estabelecimentoLoginBusiness) {
        this.estabelecimentoLoginBusiness = estabelecimentoLoginBusiness;
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

    public void setEstoqueService(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

}
