package br.com.furafila.mvc.produto.service;

import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.modelsGerais.ComprarProduto;
import br.com.furafila.mvc.pedidos.model.Pedidos;
import br.com.furafila.mvc.produto.business.ProdutoBusiness;
import br.com.furafila.mvc.produto.model.Produto;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ProdutoService {
    
    private ProdutoBusiness produtoBusiness = new ProdutoBusiness();
    
    public List<Produto> buscarCardapio(Estabelecimento estabelecimento) throws Exception{
        
        List<List<String>> lstDados = getProdutoBusiness().listarProdutoCardapio(estabelecimento);
        List<Produto> lstProdutos = new ArrayList<>();
        
        if(!FuraFilaUtils.listaDuplaVaziaNula(lstDados)){
            for(List<String> lstValores : lstDados){
                
                int index = 0;
                Produto p = new Produto();
                p.setId_produto(Integer.parseInt(lstValores.get(index++)));
                p.setProduto_desc(lstValores.get(index++));
                p.setValor_unitario(Double.parseDouble(lstValores.get(index++)));
                p.getTipoProduto().setTipo_produto_desc(lstValores.get(index++));
                
                lstProdutos.add(p);
                
            }
        }
        
        return lstProdutos;
        
    }
    
    public List<Pedidos> listarProdutosComprar(ComprarProduto comprarProduto) throws Exception{
        
        List<List<String>> lstDados = getProdutoBusiness().listarProdutosComprar(comprarProduto);
        List<Pedidos> lstProdutoComprar = new ArrayList<>();
        
        if(!FuraFilaUtils.listaDuplaVaziaNula(lstDados)){
            for(List<String> lstValor : lstDados){
                
                int index = 0;
                Pedidos p = new Pedidos();
                
                p.getProduto().setId_produto(Integer.parseInt(lstValor.get(index++)));
                p.getProduto().setProduto_desc(lstValor.get(index++));
                p.getProduto().setValor_unitario(Double.parseDouble(lstValor.get(index++)));
                p.getComanda().getEstabelecimento().setId_estabelecimento(Integer.parseInt(lstValor.get(index++)));
                p.getComanda().getEstabelecimento().setRazao_social(lstValor.get(index++));
                p.getProduto().getDimensao().setAltura(Integer.parseInt(lstValor.get(index++)));
                p.getProduto().getDimensao().setLargura(Integer.parseInt(lstValor.get(index++)));
                p.getProduto().getDimensao().setProfundidade(Integer.parseInt(lstValor.get(index++)));
                
                lstProdutoComprar.add(p);
                
            }
        }
        
        return lstProdutoComprar;
        
    }
    
    
    public ProdutoBusiness getProdutoBusiness() {
        return produtoBusiness;
    }

    public void setProdutoBusiness(ProdutoBusiness produtoBusiness) {
        this.produtoBusiness = produtoBusiness;
    }

    
}
