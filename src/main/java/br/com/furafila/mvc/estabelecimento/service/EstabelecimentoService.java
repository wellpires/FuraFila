package br.com.furafila.mvc.estabelecimento.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.com.furafila.mvc.estabelecimento.business.EstabelecimentoBusiness;
import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.pedidos.model.Pedidos;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstabelecimentoService {

	private EstabelecimentoBusiness estabelecimentoBusiness = new EstabelecimentoBusiness();

	public List<Estabelecimento> listarEstabelecimentos() throws Exception {

		List<Estabelecimento> lstEstabelecimentos = new ArrayList<>();
		List<List<String>> lstDados = getEstabelecimentoBusiness().listarEstabelecimento();

		if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {

			for (List<String> valores : lstDados) {

				Estabelecimento e = new Estabelecimento();
				Integer indice = 0;

				e.setIdEstabelecimento(Integer.parseInt(valores.get(indice++)));
				e.setRazaoSocial(valores.get(indice++));
				e.setCnpj(Long.parseLong(valores.get(indice++)));
				e.setInscricaoEstadual(Long.parseLong(valores.get(indice++)));
				e.setStatus(valores.get(indice++).equals(String.valueOf(FuraFilaConstants.COD_ATIVO)));
				e.setEmail(valores.get(indice++));
				String idImagem = valores.get(indice++);
				e.getImagem()
						.setIdImagem(Integer.parseInt(idImagem == null ? "0" : idImagem));
				e.getImagem().setImagem(FuraFilaUtils.semImagem(e));

				lstEstabelecimentos.add(e);

			}

		}

		return lstEstabelecimentos;

	}

	public void buscarInformacaoEstabelecimento(Estabelecimento estabelecimento) throws Exception {

		List<String> lstDados = getEstabelecimentoBusiness().listarInformacoesEstabelecimento(estabelecimento);

		if (!FuraFilaUtils.listaVaziaNula(lstDados)) {

			Integer indice = 0;

			estabelecimento.setIdEstabelecimento(Integer.parseInt(lstDados.get(indice++)));
			estabelecimento.setRazaoSocial(lstDados.get(indice++));
			estabelecimento.setEmail(lstDados.get(indice++));
			estabelecimento.setCnpj(Long.parseLong(lstDados.get(indice++)));
			estabelecimento.setInscricaoEstadual(Long.parseLong(lstDados.get(indice++)));

			String idImagem = lstDados.get(indice++);
			if (StringUtils.isBlank(idImagem)) {
				idImagem = NumberUtils.INTEGER_ZERO.toString();
			}

			estabelecimento.getImagem().setIdImagem(Integer.parseInt(idImagem));

		} else {
			estabelecimento = new Estabelecimento();
		}

	}

	public List<Pedidos> listarEstabelecimentoMaisVendem() throws Exception {

		List<List<String>> lstDados = getEstabelecimentoBusiness().listarEstabelecimentoMaisVendem();
		List<Pedidos> lstEstabelecimentos = new ArrayList<>();

		if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
			for (List<String> lstValores : lstDados) {

				int index = 0;
				Pedidos pedido = new Pedidos();

				pedido.getComanda().getEstabelecimento().setRazaoSocial(lstValores.get(index++));
				pedido.setQtd(Integer.parseInt(lstValores.get(index++)));

				lstEstabelecimentos.add(pedido);
			}
		}

		return lstEstabelecimentos;

	}

	public int verificarDuplicidadeRazaoSocial(Estabelecimento estabelecimento) throws Exception {
		return getEstabelecimentoBusiness().pegarRazaoSocial(estabelecimento).size();
	}

	public int verificarDuplicidadeEmail(Estabelecimento estabelecimento) throws Exception {
		return getEstabelecimentoBusiness().pegarEmail(estabelecimento).size();
	}

	public int verificarDuplicidadeCnpj(Estabelecimento estabelecimento) throws Exception {
		return getEstabelecimentoBusiness().pegarCnpj(estabelecimento).size();
	}

	public int verificarDuplicidadeInscricaoEstadual(Estabelecimento estabelecimento) throws Exception {
		return getEstabelecimentoBusiness().pegarInscricaoEstadual(estabelecimento).size();
	}

	public EstabelecimentoBusiness getEstabelecimentoBusiness() {
		return estabelecimentoBusiness;
	}

	public void setEstabelecimentoBusiness(EstabelecimentoBusiness estabelecimentoBusiness) {
		this.estabelecimentoBusiness = estabelecimentoBusiness;
	}

}
