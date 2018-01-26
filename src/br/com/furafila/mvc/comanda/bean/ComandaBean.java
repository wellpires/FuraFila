package br.com.furafila.mvc.comanda.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.furafila.mvc.comanda.business.ComandaBusiness;
import br.com.furafila.mvc.comanda.service.ComandaService;
import br.com.furafila.mvc.estoqueSaida.business.EstoqueSaidaBusiness;
import br.com.furafila.mvc.estoqueSaida.model.EstoqueSaida;
import br.com.furafila.mvc.locker.business.LockerBusiness;
import br.com.furafila.mvc.pedidoLocker.model.PedidoLocker;
import br.com.furafila.mvc.pedidos.model.Pedidos;
import br.com.furafila.utils.EnviarEmails;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@SessionScoped
public class ComandaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PedidoLocker> lstComandasAprovadas = new ArrayList<>();

    private ComandaBusiness comandaBusiness = new ComandaBusiness();
    private LockerBusiness lockerBusiness = new LockerBusiness();
    private EstoqueSaidaBusiness estoqueSaidaBusiness = new EstoqueSaidaBusiness();

    private ComandaService comandaService = new ComandaService();

    private PedidoLocker pedidoLocker = new PedidoLocker();

    public void popularComandasAprovadas() {

        try {
            setLstComandasAprovadas(getComandaService().listarComandasAprovadas(" WHERE C.id_status_FK <> " + FuraFilaConstants.COD_PRODUTO_ENTREGUE + " AND C.id_status_FK <> " + FuraFilaConstants.COD_ENCAMINHADO_LOCKER));
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void adicionarCabecalho() {
        FacesContext.getCurrentInstance().getExternalContext().addResponseHeader("Access-Control-Allow-Origin", "https://sandbox.pagseguro.uol.com.br");
    }

    public void atualizarComandas(ActionEvent ae) {

        try {

            //1 - VERIFICAR O STATUS JUNTO COM O PAGSEGURO
            //2 - ATUALIZAR A FORMA DE PAGAMENTO
            //3 - ORGANIZAR A LISTA DE ACORDO COM A LOCALIZAÇÃO
//            List<PedidoLocker> lstTodasComandas = getComandaService().listarComandasAprovadas(false);
            //VERIFICAR O STATUS JUNTO AO PAGSEGURO
//            buscarDadosPedido();
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void confirmarPedido(ActionEvent ae) {
        try {
            List<Pedidos> lstProdutos = getComandaService().listarProdutosPorComanda(getPedidoLocker().getPedidos().getComanda());
            
            PedidoLocker pl = getComandaService().buscarEstabelecimentoLocker(getPedidoLocker().getPedidos().getComanda());
            
            pl.getPedidos().getComanda().getStatus().setIdStatus(FuraFilaConstants.COD_PRODUTO_ENTREGUE);
            pl.getLocker().getStatus().setIdStatus(FuraFilaConstants.COD_LOCKER_LIVRE);
            getLockerBusiness().alterarStatus(pl.getLocker());
            
            for (Pedidos p : lstProdutos) {
                EstoqueSaida es = new EstoqueSaida();
                es.setProduto(p.getProduto());
                es.setQtdSaida(p.getQtd());
                es.getMotivoSaida().setMotivoSaida(FuraFilaConstants.MOTIVO_SAIDA_VENDA);
                getEstoqueSaidaBusiness().gravar(es, pl.getPedidos().getComanda().getEstabelecimento());
            }
            
            FuraFilaUtils.executarJavascript("alert('Pedido nº" + getPedidoLocker().getPedidos().getComanda().getIdComanda() + " entregue!')");
            
            getComandaBusiness().alterarStatusComanda(getPedidoLocker().getPedidos().getComanda());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void alterarStatusPedido(ActionEvent ae) {
        try {

            List<Pedidos> lstProdutos = getComandaService().listarProdutosPorComanda(getPedidoLocker().getPedidos().getComanda());

            if (FuraFilaConstants.COD_EM_ANALISE == getPedidoLocker().getPedidos().getComanda().getStatus().getIdStatus()) {
                getPedidoLocker().getPedidos().getComanda().getStatus().setIdStatus(FuraFilaConstants.COD_EM_SEPARACAO);

                Double total = 0.0;

                for (Pedidos p : lstProdutos) {
                    total += p.getSubTotal();
                }

                EnviarEmails.enviarEmailEmSeparacao(getPedidoLocker(), FuraFilaUtils.formatarMoeda(total), lstProdutos);
            } else if (FuraFilaConstants.COD_EM_SEPARACAO == getPedidoLocker().getPedidos().getComanda().getStatus().getIdStatus()) {
                getPedidoLocker().getPedidos().getComanda().getStatus().setIdStatus(FuraFilaConstants.COD_ENCAMINHADO_LOCKER);
                getComandaBusiness().atualizarDataVenda(getPedidoLocker().getPedidos().getComanda());

                EnviarEmails.enviarEmailEncaminhadoLocker(getPedidoLocker());

            } else if (FuraFilaConstants.COD_ENCAMINHADO_LOCKER == getPedidoLocker().getPedidos().getComanda().getStatus().getIdStatus()) {
                getPedidoLocker().getPedidos().getComanda().getStatus().setIdStatus(FuraFilaConstants.COD_PRODUTO_ENTREGUE);
                getPedidoLocker().getLocker().getStatus().setIdStatus(FuraFilaConstants.COD_LOCKER_LIVRE);
                getLockerBusiness().alterarStatus(getPedidoLocker().getLocker());

                for (Pedidos p : lstProdutos) {
                    EstoqueSaida es = new EstoqueSaida();
                    es.setProduto(p.getProduto());
                    es.setQtdSaida(p.getQtd());
                    es.getMotivoSaida().setMotivoSaida(FuraFilaConstants.MOTIVO_SAIDA_VENDA);
                    getEstoqueSaidaBusiness().gravar(es, getPedidoLocker().getPedidos().getComanda().getEstabelecimento());
                }

                FuraFilaUtils.executarJavascript("alert('Pedido nº" + getPedidoLocker().getPedidos().getComanda().getIdComanda() + " entregue!')");

            }

            getComandaBusiness().alterarStatusComanda(getPedidoLocker().getPedidos().getComanda());
            popularComandasAprovadas();
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public List<PedidoLocker> getLstComandasAprovadas() {
        return lstComandasAprovadas;
    }

    public void setLstComandasAprovadas(List<PedidoLocker> lstComandasAprovadas) {
        this.lstComandasAprovadas = lstComandasAprovadas;
    }

    public ComandaService getComandaService() {
        return comandaService;
    }

    public void setComandaService(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    public PedidoLocker getPedidoLocker() {
        return pedidoLocker;
    }

    public void setPedidoLocker(PedidoLocker pedidoLocker) {
        this.pedidoLocker = pedidoLocker;
    }

    public ComandaBusiness getComandaBusiness() {
        return comandaBusiness;
    }

    public void setComandaBusiness(ComandaBusiness comandaBusiness) {
        this.comandaBusiness = comandaBusiness;
    }

    public LockerBusiness getLockerBusiness() {
        return lockerBusiness;
    }

    public void setLockerBusiness(LockerBusiness lockerBusiness) {
        this.lockerBusiness = lockerBusiness;
    }

    public EstoqueSaidaBusiness getEstoqueSaidaBusiness() {
        return estoqueSaidaBusiness;
    }

    public void setEstoqueSaidaBusiness(EstoqueSaidaBusiness estoqueSaidaBusiness) {
        this.estoqueSaidaBusiness = estoqueSaidaBusiness;
    }

}
