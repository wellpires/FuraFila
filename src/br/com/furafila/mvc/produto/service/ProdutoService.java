package br.com.furafila.mvc.produto.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.imagem.business.ImagemBusiness;
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
    private ImagemBusiness imagemBusiness = new ImagemBusiness();
    
    public List<Produto> buscarCardapio(Estabelecimento estabelecimento) throws Exception{
        
        List<List<String>> lstDados = getProdutoBusiness().listarProdutoCardapio(estabelecimento);
        List<Produto> lstProdutos = new ArrayList<>();
        
        if(!FuraFilaUtils.listaDuplaVaziaNula(lstDados)){
            for(List<String> lstValores : lstDados){
                
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
    
    public List<Pedidos> listarProdutosComprar(ComprarProduto comprarProduto) throws Exception{
        
        List<List<String>> lstDados = getProdutoBusiness().listarProdutosComprar(comprarProduto);
        List<Pedidos> lstProdutoComprar = new ArrayList<>();
        
        if(!FuraFilaUtils.listaDuplaVaziaNula(lstDados)){
            for(List<String> lstValor : lstDados){
                
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
                File imagem = imagemBusiness.buscarImagemPorId( p.getProduto().getImagem().getIdImagem());
                p.getProduto().getImagem().setImagem(imagem.getAbsolutePath());
                
                
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
