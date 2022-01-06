package br.com.furafila.mvc.tipoProduto.service;

import java.util.List;

import br.com.furafila.mvc.tipoProduto.model.TipoProduto;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public interface ITipoProdutoService {
    
    public List<TipoProduto> listarTipoProduto(boolean isAdministador) throws Exception;
    public int pegarTipoProduto(TipoProduto tipoProduto) throws Exception;
    
}
