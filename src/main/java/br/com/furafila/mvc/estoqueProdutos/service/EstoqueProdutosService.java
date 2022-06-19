package br.com.furafila.mvc.estoqueProdutos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estoqueProdutos.business.EstoqueProdutosBusiness;
import br.com.furafila.mvc.estoqueProdutos.dto.ProdutosEstabelecimentoDTO;
import br.com.furafila.mvc.estoqueProdutos.function.ProdutosEstabelecimentoDTO2EstoqueProdutosFunction;
import br.com.furafila.mvc.estoqueProdutos.model.EstoqueProdutos;
import br.com.furafila.mvc.produto.service.ProdutoApiService;
import br.com.furafila.mvc.produto.service.impl.ProdutoApiServiceImpl;
import br.com.furafila.relatorios.VOs.ProdutoVO;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueProdutosService {

	private EstoqueProdutosBusiness estoqueProdutosBusiness = new EstoqueProdutosBusiness();
	private ProdutoApiService produtoApiService = new ProdutoApiServiceImpl();

	public List<EstoqueProdutos> listarProdutosEstabelecimento(Estabelecimento estabelecimento) {

		List<ProdutosEstabelecimentoDTO> produtoEstabelecimentoDTOs = produtoApiService
				.listarProdutosEstabelecimento(estabelecimento.getIdEstabelecimento());

		return produtoEstabelecimentoDTOs.stream().map(new ProdutosEstabelecimentoDTO2EstoqueProdutosFunction())
				.collect(Collectors.toList());
	}

	public List<ProdutoVO> valoresParaRelatorio(EstoqueProdutos ep) throws Exception {

		List<List<String>> lstDados = this.estoqueProdutosBusiness.dadosParaRelatorio(ep);
		List<ProdutoVO> lstRelatorio = new ArrayList<>();

		for (List<String> lstValores : lstDados) {

			ProdutoVO p = new ProdutoVO();

			int index = 0;
			p.setRazao_social(lstValores.get(index++));
			p.setProduto_desc(lstValores.get(index++));
			p.setValor_unitario(Double.parseDouble(lstValores.get(index++)));
			p.setQtdEstoque(Integer.parseInt(lstValores.get(index++)));
			p.setTipo_produto_desc(lstValores.get(index++));

			lstRelatorio.add(p);

		}

		return lstRelatorio;

	}

}
