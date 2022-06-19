package br.com.furafila.mvc.estoqueProdutos.function;

import java.util.function.Function;

import br.com.furafila.mvc.estoqueProdutos.dto.ProdutosEstabelecimentoDTO;
import br.com.furafila.mvc.estoqueProdutos.model.EstoqueProdutos;

public class ProdutosEstabelecimentoDTO2EstoqueProdutosFunction implements Function<ProdutosEstabelecimentoDTO, EstoqueProdutos> {

	@Override
	public EstoqueProdutos apply(ProdutosEstabelecimentoDTO produtosEstabelecimentoDTO) {
		
		EstoqueProdutos estoqueProdutos = new EstoqueProdutos();
		estoqueProdutos.getProduto().setIdProduto(produtosEstabelecimentoDTO.getProductId().intValue());
		estoqueProdutos.getProduto().setProdutoDesc(produtosEstabelecimentoDTO.getProductName());
		estoqueProdutos.getProduto().setQtdMinima(produtosEstabelecimentoDTO.getStockMinimumQuantity().intValue());
		estoqueProdutos.getProduto().setValorUnitario(produtosEstabelecimentoDTO.getUnitPrice());
		estoqueProdutos.getProduto().setStatus(produtosEstabelecimentoDTO.getStatus());
		estoqueProdutos.getProduto().getTipoProduto().setIdTipoProduto(produtosEstabelecimentoDTO.getEstabelecimentoProdutoTipoDTO().getId().intValue());
		estoqueProdutos.getProduto().getTipoProduto().setTipoProdutoDesc(produtosEstabelecimentoDTO.getEstabelecimentoProdutoTipoDTO().getName());
		estoqueProdutos.getProduto().getDimensao().setIdDimensao(produtosEstabelecimentoDTO.getEstabelecimentoProdutoDimesaoDTO().getId().intValue());
		estoqueProdutos.getProduto().getDimensao().setAltura(produtosEstabelecimentoDTO.getEstabelecimentoProdutoDimesaoDTO().getHeight());
		estoqueProdutos.getProduto().getDimensao().setLargura(produtosEstabelecimentoDTO.getEstabelecimentoProdutoDimesaoDTO().getWidth());
		estoqueProdutos.getProduto().getDimensao().setProfundidade(produtosEstabelecimentoDTO.getEstabelecimentoProdutoDimesaoDTO().getLength().intValue());
		estoqueProdutos.getProduto().getImagem().setIdImagem(produtosEstabelecimentoDTO.getImageId().intValue());
		estoqueProdutos.setQtdEstoque(produtosEstabelecimentoDTO.getStockQuantity().intValue());
		
		return estoqueProdutos;
	}



}
