package br.com.furafila.mvc.produto.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.cliente.service.ImagemService;
import br.com.furafila.mvc.cliente.service.impl.ImagemServiceImpl;
import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.modelsGerais.ComprarProduto;
import br.com.furafila.mvc.pedidos.model.Pedidos;
import br.com.furafila.mvc.produto.builder.EditarProdutoDTOBuilder;
import br.com.furafila.mvc.produto.business.ProdutoBusiness;
import br.com.furafila.mvc.produto.dto.EditarProdutoDTO;
import br.com.furafila.mvc.produto.dto.EditarProdutoPrecoUnitarioDTO;
import br.com.furafila.mvc.produto.dto.NovaDimensaoDTO;
import br.com.furafila.mvc.produto.dto.NovoProdutoDTO;
import br.com.furafila.mvc.produto.model.Produto;
import br.com.furafila.mvc.produto.service.ProdutoApiService;
import br.com.furafila.mvc.produto.service.ProdutoService;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ProdutoServiceImpl implements ProdutoService {

	private ProdutoBusiness produtoBusiness = new ProdutoBusiness();
	private ImagemService imageService = new ImagemServiceImpl();

	private ProdutoApiService produtoApiService = new ProdutoApiServiceImpl();

	@Override
	public List<Produto> buscarCardapio(Estabelecimento estabelecimento) throws Exception {

		List<List<String>> lstDados = produtoBusiness.listarProdutoCardapio(estabelecimento);
		List<Produto> lstProdutos = new ArrayList<>();

		if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
			for (List<String> lstValores : lstDados) {

				int index = 0;
				Produto p = new Produto();
				p.setIdProduto(Integer.parseInt(lstValores.get(index++)));
				p.setProdutoDesc(lstValores.get(index++));
				p.setValorUnitario(Double.parseDouble(lstValores.get(index++)));
				p.getTipoProduto().setTipoProdutoDesc(lstValores.get(index++));

				lstProdutos.add(p);

			}
		}

		return lstProdutos;

	}

	@Override
	public List<Pedidos> listarProdutosComprar(ComprarProduto comprarProduto) throws Exception {

		List<List<String>> lstDados = produtoBusiness.listarProdutosComprar(comprarProduto);
		List<Pedidos> lstProdutoComprar = new ArrayList<>();

		if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
			for (List<String> lstValor : lstDados) {

				int index = 0;
				Pedidos p = new Pedidos();

				p.getProduto().setIdProduto(Integer.parseInt(lstValor.get(index++)));
				p.getProduto().setProdutoDesc(lstValor.get(index++));
				p.getProduto().setValorUnitario(Double.parseDouble(lstValor.get(index++)));
				p.getComanda().getEstabelecimento().setIdEstabelecimento(Integer.parseInt(lstValor.get(index++)));
				p.getComanda().getEstabelecimento().setRazaoSocial(lstValor.get(index++));
				p.getProduto().getDimensao().setAltura(Integer.parseInt(lstValor.get(index++)));
				p.getProduto().getDimensao().setLargura(Integer.parseInt(lstValor.get(index++)));
				p.getProduto().getDimensao().setProfundidade(Integer.parseInt(lstValor.get(index++)));
				p.getProduto().getImagem().setIdImagem(Integer.parseInt(lstValor.get(index++)));
				File imagem = imageService.buscarImagem(Long.valueOf(p.getProduto().getImagem().getIdImagem()));
				p.getProduto().getImagem().setImagem(imagem.getAbsolutePath());

				lstProdutoComprar.add(p);

			}
		}

		return lstProdutoComprar;

	}

	@Override
	public Long gravar(Produto produtos) {

		NovoProdutoDTO novoProdutoDTO = new NovoProdutoDTO();
		novoProdutoDTO.setName(produtos.getProdutoDesc());
		novoProdutoDTO.setProductTypeId(produtos.getTipoProduto().getIdTipoProduto().longValue());
		novoProdutoDTO.setImageId(produtos.getImagem().getIdImagem().longValue());
		novoProdutoDTO.setMinimumStockQuantity(produtos.getQtdMinima().longValue());

		NovaDimensaoDTO novaDimensaoDTO = new NovaDimensaoDTO();
		novaDimensaoDTO.setHeight(produtos.getDimensao().getAltura().longValue());
		novaDimensaoDTO.setWidth(produtos.getDimensao().getLargura().longValue());
		novaDimensaoDTO.setLength(produtos.getDimensao().getProfundidade().longValue());
		novoProdutoDTO.setNovaDimensaoDTO(novaDimensaoDTO);

		return produtoApiService.gravar(novoProdutoDTO);
	}

	@Override
	public void alterar(Produto produto) {

		EditarProdutoDTO editarProdutoDTO = new EditarProdutoDTOBuilder().name(produto.getProdutoDesc())
				.minimumStockQuantity(produto.getQtdMinima()).productTypeId(produto.getTipoProduto().getIdTipoProduto())
				.newDimension().height(produto.getDimensao().getAltura()).width(produto.getDimensao().getLargura())
				.length(produto.getDimensao().getProfundidade()).builder();
		produtoApiService.alterar(editarProdutoDTO, produto.getIdProduto().longValue());

	}

	@Override
	public void alterarStatus(Integer idProduto) {
		produtoApiService.alterarStatus(idProduto);
	}

	@Override
	public void alterarPreco(Produto produto) {

		EditarProdutoPrecoUnitarioDTO editarProdutoPrecoUnitarioDTO = new EditarProdutoPrecoUnitarioDTO();
		editarProdutoPrecoUnitarioDTO.setUnitPrice(produto.getValorUnitario());

		produtoApiService.alterarPreco(editarProdutoPrecoUnitarioDTO, produto.getIdProduto().longValue());

	}

}
