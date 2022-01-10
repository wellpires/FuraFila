package br.com.furafila.mvc.produto.bean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.furafila.mvc.cep.dto.LocationDTO;
import br.com.furafila.mvc.cep.model.Distancia;
import br.com.furafila.mvc.cep.service.CepService;
import br.com.furafila.mvc.cliente.model.Cliente;
import br.com.furafila.mvc.comanda.business.ComandaBusiness;
import br.com.furafila.mvc.comanda.model.Comanda;
import br.com.furafila.mvc.conjuntoLocker.model.ConjuntoLocker;
import br.com.furafila.mvc.conjuntoLocker.service.ConjuntoLockerService;
import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estabelecimento.service.EstabelecimentoService;
import br.com.furafila.mvc.locker.business.LockerBusiness;
import br.com.furafila.mvc.locker.model.Locker;
import br.com.furafila.mvc.locker.service.LockerService;
import br.com.furafila.mvc.logradouro.model.Logradouro;
import br.com.furafila.mvc.modelsGerais.ComprarProduto;
import br.com.furafila.mvc.pedidoLocker.business.PedidoLockerBusiness;
import br.com.furafila.mvc.pedidoLocker.model.PedidoLocker;
import br.com.furafila.mvc.pedidos.business.PedidoBusiness;
import br.com.furafila.mvc.pedidos.model.Pedidos;
import br.com.furafila.mvc.produto.model.Produto;
import br.com.furafila.mvc.produto.service.ProdutoService;
import br.com.furafila.utils.EnviarEmails;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;
import br.com.furafila.utils.Navegacao;
import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.common.domain.ShippingType;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.State;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@SessionScoped
public class ComprarProdutosBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ComprarProdutosBean.class);

	private ProdutoService produtoService = new ProdutoService();
	private ConjuntoLockerService conjuntoLockerService = new ConjuntoLockerService();
	private LockerService lockerService = new LockerService();
	private EstabelecimentoService estabelecimentoService = new EstabelecimentoService();

	private ComandaBusiness comandaBusiness = new ComandaBusiness();
	private PedidoBusiness pedidoBusiness = new PedidoBusiness();
	private PedidoLockerBusiness pedidoLockerBusiness = new PedidoLockerBusiness();
	private LockerBusiness lockerBusiness = new LockerBusiness();

	private ComprarProduto comprarProduto = new ComprarProduto();
	private Comanda comanda = new Comanda();
	private Pedidos pedidos = new Pedidos();
	private Produto produtos = new Produto();
	private Logradouro logradouro = new Logradouro();
	private PedidoLocker pedidoLocker = new PedidoLocker();
	private ConjuntoLocker conjuntoLocker = new ConjuntoLocker();

	private List<Pedidos> lstProdutoComprar = new ArrayList<>();
	private List<PedidoLocker> lstCarrinho = new ArrayList<>();
	private List<ConjuntoLocker> lstConjuntoLockers = new ArrayList<>();
	private List<Estabelecimento> lstEstabelecimentos = new ArrayList<>();
	private List<Estabelecimento> lstEstabelecimentosFiltro = new ArrayList<>();
	private List<SelectItem> lstOrdens = new ArrayList<>();

	private String centerGeoMap = "0,0";
	private MapModel geoModel = new DefaultMapModel();
	private Double latitudeHidden = 0D;
	private Double longitudeHidden = 0D;
	private String response;

	private Integer tabIndex = 0;

	public void popularProdutosComprar() {
		try {
			setLstProdutoComprar(getProdutoService().listarProdutosComprar(getComprarProduto()));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}
	}

	public void popularFiltro() {

		try {
			setLstEstabelecimentosFiltro(getEstabelecimentoService().listarEstabelecimentos());

			getLstOrdens().clear();
			SelectItemGroup sigPreco = new SelectItemGroup("Preço");
			sigPreco.setSelectItems(new SelectItem[] { new SelectItem(" P.valor_unitario DESC", "Maior Preço"),
					new SelectItem(" P.valor_unitario ASC", "Menor preço") });
			getLstOrdens().add(sigPreco);

			SelectItemGroup sigProdutos = new SelectItemGroup("Produto");
			sigProdutos.setSelectItems(new SelectItem[] { new SelectItem(" P.produto_desc ASC", "A-Z"),
					new SelectItem(" P.produto_desc DESC", "Z-A") });
			getLstOrdens().add(sigProdutos);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

	}

	public void popularLockers() {
		try {
			setTabIndex(0);
			setLstConjuntoLockers(getConjuntoLockerService().listarConjuntoLocker());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}
	}

	public void organizarLockers() {

		try {
			popularLockers();
			HashMap<Integer, Object> hmDistancias = new HashMap<>();
			Distancia d = new Distancia();
			d.getOrigem().setLatitude(getLatitudeHidden());
			d.getOrigem().setLongitude(getLongitudeHidden());

			for (ConjuntoLocker l : getLstConjuntoLockers()) {

				d.setDestino(l.getLogradouro());
				FuraFilaUtils.calcularDistancia(d);

				hmDistancias.put(d.getDistancia(), l);

				LatLng center = new LatLng(l.getLogradouro().getLatitude(), l.getLogradouro().getLongitude());
				getGeoModel().addOverlay(new Marker(center, l.getConjuntoLockerDesc()));

			}

			Map<Integer, Object> hmDistanciasOrdenadas = new TreeMap<>(hmDistancias);
			getLstConjuntoLockers().clear();

			for (Map.Entry<Integer, Object> distancia : hmDistanciasOrdenadas.entrySet()) {
				getLstConjuntoLockers().add((ConjuntoLocker) distancia.getValue());
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}
	}

	public void adicionarCarrinho(Pedidos p) {

		PedidoLocker pd = new PedidoLocker();
		pd.setPedidos(p.clonar());

		int posicaoLista = existeProduto(pd.getPedidos().getProduto().getIdProduto());

		if (posicaoLista > -1) {
			getLstCarrinho().get(posicaoLista).getPedidos().somarQtdeProdutos(pd.getPedidos().getQtd());
		} else {
			getLstCarrinho().add(pd);
		}

		listarEstabelecimento();

		p.setQtd(0);
		FuraFilaUtils.growlInfo(FuraFilaConstants.INFO_GROWL_TITULO, FuraFilaConstants.INFO_PRODUTO_AD_CARRINHO);

	}

	public void deletarCarrinho(PedidoLocker p) {

		getLstCarrinho().remove(p);

		FuraFilaUtils.growlInfo(FuraFilaConstants.INFO_GROWL_TITULO, FuraFilaConstants.INFO_PRODUTO_DEL_CARRINHO);
	}

	public String getTotalFormatado() {

		Double total = 0.0;
		for (PedidoLocker pd : getLstCarrinho()) {
			total += pd.getPedidos().getSubTotal();
		}

		return FuraFilaUtils.formatarMoeda(total);

	}

	private int existeProduto(int codigo) {

		int posicaoLista = -1;

		for (int i = 0; i < getLstCarrinho().size(); i++) {
			if (getLstCarrinho().get(i).getPedidos().getProduto().getIdProduto() == codigo) {
				posicaoLista = i;
			}
		}

		return posicaoLista;

	}

	public void pesquisarGeolocalizacao(ActionEvent ae) {

		try {
			CepService cep = new CepService();

			cep.pesquisarCep(getLogradouro());

			LocationDTO locationDTO = cep.pegarGeolocalizacao(getLogradouro());

			LatLng center = new LatLng(locationDTO.getLatitude(), locationDTO.getLongitude());
			setCenterGeoMap(center.getLat() + "," + center.getLng());
			getGeoModel().addOverlay(new Marker(center, "", null, FuraFilaConstants.IMAGEM_LOCKER));
			setLatitudeHidden(locationDTO.getLatitude());
			setLongitudeHidden(locationDTO.getLongitude());

			organizarLockers();

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

	}

	private void listarEstabelecimento() {

		for (int i = 0; i < getLstCarrinho().size(); i++) {

			int estabelecimentoExiste = 0;
			if (getLstEstabelecimentos().isEmpty()) {
				getLstEstabelecimentos().add(getLstCarrinho().get(i).getPedidos().getComanda().getEstabelecimento());
				return;
			}

			Estabelecimento e = getLstCarrinho().get(i).getPedidos().getComanda().getEstabelecimento();

			for (Estabelecimento est : getLstEstabelecimentos()) {
				if (e.getRazaoSocial().equalsIgnoreCase(est.getRazaoSocial())) {
					estabelecimentoExiste++;
				}
			}

			if (estabelecimentoExiste == 0) {
				getLstEstabelecimentos().add(getLstCarrinho().get(i).getPedidos().getComanda().getEstabelecimento());
			}

		}

	}

	public void finalizar(ActionEvent ae) {
		try {
			EnviarEmails.envarEmailEmAnalise(getPedidoLocker(), getTotalFormatado(), getLstCarrinho());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		avancar(null);
	}

	public void avancar(ActionEvent ae) {
		this.setTabIndex((Integer) (this.getTabIndex() + 1));
	}

	public void retroceder(ActionEvent ae) {
		if (getTabIndex() > 0) {
			this.setTabIndex((Integer) (this.getTabIndex() - 1));
		}
	}

	public void efetuarPagamento(ActionEvent ae) throws IOException {

		String codigoCompra = "";

		CheckoutRegistrationBuilder checkoutBuilder = new CheckoutRegistrationBuilder();

		for (PedidoLocker p : getLstCarrinho()) {
			new CheckoutRegistrationBuilder().addItem(new PaymentItemBuilder()
					.withId(p.getPedidos().getProduto().getIdProduto().toString())
					.withDescription(p.getPedidos().getProduto().getProdutoDesc()).withQuantity(p.getPedidos().getQtd())
					.withAmount(
							new BigDecimal(p.getPedidos().getProduto().getValorUnitario()).setScale(2, RoundingMode.UP))
					.build());
			getPedidoLocker().getPedidos().getComanda().setIdComanda(p.getPedidos().getComanda().getIdComanda());
		}

		Cliente c = pegarDadosSessaoCliente();

		checkoutBuilder
				.withSender(
						new SenderBuilder().withName(c.getNome()).withEmail(c.getEmail())
								.withPhone(new PhoneBuilder().withAreaCode(c.getTelRes().toString().substring(0, 2))
										.withNumber(c.getTelRes().toString().substring(2)))
								.withCPF(c.getCpfFormatado()));
		checkoutBuilder.withCurrency(Currency.BRL);
		checkoutBuilder.withShipping(new ShippingBuilder().withType(ShippingType.Type.USER_CHOISE)
				.withAddress(new AddressBuilder().withCountry("Brazil").withState(State.SP)
						.withCity(c.getLogradouro().getBairro().getCidade().getDescCidade())
						.withDistrict(c.getLogradouro().getBairro().getDescBairro())
						.withPostalCode(c.getLogradouro().getNroCepFormatado())
						.withStreet(c.getLogradouro().getLogradouroFormatado()).withNumber(c.getNroCasa().toString())
						.withComplement(c.getComplemento())));

//			boolean apenasCodigoPagamento = true;
//			codigoCompra = pagamento.register(PagSeguroConfig.getAccountCredentials(), apenasCodigoPagamento);
//			pagamento.getNotificationURL();
		codigoCompra = String.valueOf(System.currentTimeMillis());
		getPedidoLocker().getPedidos().getComanda().setCodigoPedido(codigoCompra);

		getPedidoLocker().getPedidos().getComanda().getStatus().setIdStatus(FuraFilaConstants.COD_EM_ANALISE);
		getPedidoLocker().getPedidos().getComanda().setCliente(pegarDadosSessaoCliente().clonar());
		getPedidoLocker().getPedidos().getComanda().setIdComanda(FuraFilaUtils.gerarCodigoVenda().toString());

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=" + codigoCompra);
		gravarPedidoComanda();

	}

	private void gravarPedidoComanda() {

		try {
			getComandaBusiness().gravarComanda(getPedidoLocker().getPedidos().getComanda());

			for (PedidoLocker pl : getLstCarrinho()) {
				pl.getPedidos().getComanda().setIdComanda(getPedidoLocker().getPedidos().getComanda().getIdComanda());
				getPedidoBusiness().gravarPedido(pl.getPedidos());
				getPedidoLockerBusiness().gravar(pl);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}

	}

	public String finalizarPedido() {

		for (PedidoLocker pd : getLstCarrinho()) {
			pd.getPedidos().getComanda().setCliente(pegarDadosSessaoCliente().clonar());
			pd.getPedidos().getComanda().getStatus().setIdStatus(FuraFilaConstants.COD_EM_ANALISE);
		}

		return Navegacao.irPaginaFinalizarCompra();
	}

	public void irResumoPedido(ActionEvent ae) {

		try {
			Integer volumeTotal = 0;

			for (PedidoLocker pl : getLstCarrinho()) {
				volumeTotal += pl.getPedidos().getProduto().getDimensao().calcularVolume();
			}

			List<Locker> lstLocker = getLockerService().buscarLockerPorVolume(volumeTotal, getConjuntoLocker());

			// TRAZER O MENOR VOLUME DOS QUE FORAM BUSCADOS DO BANCO DE DADOS
			Integer menorValor = Integer.MAX_VALUE;
			Locker locker = new Locker();
			for (Locker l : lstLocker) {
				if (l.getDimensao().calcularVolume() < menorValor) {
					menorValor = l.getDimensao().calcularVolume();
					locker = l;
				}
			}

			locker.getStatus().setIdStatus(FuraFilaConstants.COD_LOCKER_EM_USO);

			for (PedidoLocker pd : getLstCarrinho()) {
				pd.getLocker().setIdLocker(locker.getIdLocker());
				pd.getLocker().setLockerDesc(locker.getLockerDesc());
				pd.getLocker().setDimensao(locker.getDimensao());
				pd.getLocker().setConjuntoLocker(getConjuntoLocker());
				pd.getLocker().setStatus(locker.getStatus());
				getPedidoLocker().getPedidos().getComanda()
						.setEstabelecimento(pd.getPedidos().getComanda().getEstabelecimento());
				getPedidoLocker().getLocker().setConjuntoLocker(getConjuntoLocker());
			}

			// ALTERAR STATUS DO LOCKER
			getLockerBusiness().alterarStatus(locker);

			avancar(null);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
		}
	}

	private Cliente pegarDadosSessaoCliente() {
		return (Cliente) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_CLIENTE);
	}

	public String irPesquisa() {
		zerarBean();
		return Navegacao.irPaginaPesquisa();
	}

	public String irPaginaCliente() {
		zerarBean();
		return Navegacao.irPaginaPrincipalCliente();
	}

	private void zerarBean() {
		getLstCarrinho().clear();
		getLstConjuntoLockers().clear();
		this.setTabIndex(0);
		setPedidoLocker(new PedidoLocker());
		setConjuntoLocker(new ConjuntoLocker());
	}

	public void limparFiltros(ActionEvent ae) {
		setComprarProduto(new ComprarProduto());
		popularProdutosComprar();
	}

	public Boolean verificarEstabelecimentoDuplicado(Pedidos p) {
		Boolean desativar = false;
		for (PedidoLocker pl : getLstCarrinho()) {
			if (!p.getComanda().getEstabelecimento().getRazaoSocial()
					.equalsIgnoreCase(pl.getPedidos().getComanda().getEstabelecimento().getRazaoSocial())) {
				desativar = true;
				break;
			}
		}
		return desativar;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public ComprarProduto getComprarProduto() {
		return comprarProduto;
	}

	public void setComprarProduto(ComprarProduto comprarProduto) {
		this.comprarProduto = comprarProduto;
	}

	public List<Pedidos> getLstProdutoComprar() {
		return lstProdutoComprar;
	}

	public void setLstProdutoComprar(List<Pedidos> lstProdutoComprar) {
		this.lstProdutoComprar = lstProdutoComprar;
	}

	public Pedidos getPedidos() {
		return pedidos;
	}

	public void setPedidos(Pedidos pedidos) {
		this.pedidos = pedidos;
	}

	public Produto getProdutos() {
		return produtos;
	}

	public void setProdutos(Produto produtos) {
		this.produtos = produtos;
	}

	public List<PedidoLocker> getLstCarrinho() {
		return lstCarrinho;
	}

	public void setLstCarrinho(List<PedidoLocker> lstCarrinho) {
		this.lstCarrinho = lstCarrinho;
	}

	public String getCenterGeoMap() {
		return centerGeoMap;
	}

	public void setCenterGeoMap(String centerGeoMap) {
		this.centerGeoMap = centerGeoMap;
	}

	public MapModel getGeoModel() {
		return geoModel;
	}

	public void setGeoModel(MapModel geoModel) {
		this.geoModel = geoModel;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Double getLatitudeHidden() {
		return latitudeHidden;
	}

	public void setLatitudeHidden(Double latitudeHidden) {
		this.latitudeHidden = latitudeHidden;
	}

	public Double getLongitudeHidden() {
		return longitudeHidden;
	}

	public void setLongitudeHidden(Double longitudeHidden) {
		this.longitudeHidden = longitudeHidden;
	}

	public List<ConjuntoLocker> getLstConjuntoLockers() {
		return lstConjuntoLockers;
	}

	public void setLstConjuntoLockers(List<ConjuntoLocker> lstConjuntoLockers) {
		this.lstConjuntoLockers = lstConjuntoLockers;
	}

	public List<Estabelecimento> getLstEstabelecimentos() {
		return lstEstabelecimentos;
	}

	public void setLstEstabelecimentos(List<Estabelecimento> lstEstabelecimentos) {
		this.lstEstabelecimentos = lstEstabelecimentos;
	}

	public ConjuntoLockerService getConjuntoLockerService() {
		return conjuntoLockerService;
	}

	public void setConjuntoLockerService(ConjuntoLockerService conjuntoLockerService) {
		this.conjuntoLockerService = conjuntoLockerService;
	}

	public Integer getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(Integer tabIndex) {
		this.tabIndex = tabIndex;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public LockerService getLockerService() {
		return lockerService;
	}

	public void setLockerService(LockerService lockerService) {
		this.lockerService = lockerService;
	}

	public PedidoLocker getPedidoLocker() {
		return pedidoLocker;
	}

	public void setPedidoLocker(PedidoLocker pedidoLocker) {
		this.pedidoLocker = pedidoLocker;
	}

	public ConjuntoLocker getConjuntoLocker() {
		return conjuntoLocker;
	}

	public void setConjuntoLocker(ConjuntoLocker conjuntoLocker) {
		if (conjuntoLocker != null) {
			this.conjuntoLocker = conjuntoLocker;
		}
	}

	public ComandaBusiness getComandaBusiness() {
		return comandaBusiness;
	}

	public void setComandaBusiness(ComandaBusiness comandaBusiness) {
		this.comandaBusiness = comandaBusiness;
	}

	public PedidoBusiness getPedidoBusiness() {
		return pedidoBusiness;
	}

	public void setPedidoBusiness(PedidoBusiness pedidoBusiness) {
		this.pedidoBusiness = pedidoBusiness;
	}

	public PedidoLockerBusiness getPedidoLockerBusiness() {
		return pedidoLockerBusiness;
	}

	public void setPedidoLockerBusiness(PedidoLockerBusiness pedidoLockerBusiness) {
		this.pedidoLockerBusiness = pedidoLockerBusiness;
	}

	public LockerBusiness getLockerBusiness() {
		return lockerBusiness;
	}

	public void setLockerBusiness(LockerBusiness lockerBusiness) {
		this.lockerBusiness = lockerBusiness;
	}

	public List<SelectItem> getLstOrdens() {
		return lstOrdens;
	}

	public void setLstOrdens(List<SelectItem> lstOrdens) {
		this.lstOrdens = lstOrdens;
	}

	public EstabelecimentoService getEstabelecimentoService() {
		return estabelecimentoService;
	}

	public void setEstabelecimentoService(EstabelecimentoService estabelecimentoService) {
		this.estabelecimentoService = estabelecimentoService;
	}

	public List<Estabelecimento> getLstEstabelecimentosFiltro() {
		return lstEstabelecimentosFiltro;
	}

	public void setLstEstabelecimentosFiltro(List<Estabelecimento> lstEstabelecimentosFiltro) {
		this.lstEstabelecimentosFiltro = lstEstabelecimentosFiltro;
	}

}