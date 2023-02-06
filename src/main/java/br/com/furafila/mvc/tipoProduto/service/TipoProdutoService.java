package br.com.furafila.mvc.tipoProduto.service;

import java.util.List;

import br.com.furafila.mvc.tipoProduto.model.TipoProduto;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public interface TipoProdutoService {
    
    public List<TipoProduto> listarTipoProduto(boolean isAdministador) throws Exception;
    public boolean pegarTipoProduto(TipoProduto tipoProduto) throws Exception;
	public void gravar(TipoProduto tipoProduto);
	public void alterar(TipoProduto tipoProduto);
	public void alterarStatus(TipoProduto tipoProduto);
    
}
