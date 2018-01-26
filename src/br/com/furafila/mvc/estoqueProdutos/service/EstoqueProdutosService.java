package br.com.furafila.mvc.estoqueProdutos.service;

import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estoqueProdutos.business.EstoqueProdutosBusiness;
import br.com.furafila.mvc.estoqueProdutos.model.EstoqueProdutos;
import br.com.furafila.relatorios.VOs.ProdutoVO;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueProdutosService {

    private EstoqueProdutosBusiness estoqueProdutosBusiness = new EstoqueProdutosBusiness();
    
    public List<EstoqueProdutos> listarProdutosPorCodigoEstabelecimento(Estabelecimento estabelecimento) throws Exception {

        List<List<String>> lstDados = getEstoqueProdutosBusiness().listarProdutosPorCodigoEstabelecimento(estabelecimento);
        List<EstoqueProdutos> lstProdutos = new ArrayList<>();
        

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            
            for (List<String> lstValores : lstDados) {
                
                int index = 0;
                
                EstoqueProdutos e = new EstoqueProdutos();
                e.getProduto().setIdProduto(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().setProdutoDesc(lstValores.get(index++));
                e.getProduto().setQtdMinima(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().setValorUnitario(Double.parseDouble(lstValores.get(index++)));
                e.getProduto().setStatus(lstValores.get(index++).charAt(0) == FuraFilaConstants.COD_ATIVO);
                e.getProduto().getTipoProduto().setIdTipoProduto(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().getTipoProduto().setTipoProdutoDesc(lstValores.get(index++));
                e.getProduto().getDimensao().setIdDimensao(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().getDimensao().setAltura(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().getDimensao().setLargura(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().getDimensao().setProfundidade(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().getImagem().setIdImagem(Integer.parseInt(lstValores.get(index++)));
                e.setQtdEstoque(Integer.parseInt(lstValores.get(index++)));
                
                lstProdutos.add(e);
            }

        }

        return lstProdutos;
        
    }

    public List<ProdutoVO> valoresParaRelatorio(EstoqueProdutos ep) throws Exception{
        
        List<List<String>> lstDados = getEstoqueProdutosBusiness().dadosParaRelatorio(ep);
        List<ProdutoVO> lstRelatorio = new ArrayList<>();
        
        for(List<String> lstValores : lstDados){
            
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
    
    public EstoqueProdutosBusiness getEstoqueProdutosBusiness() {
        return estoqueProdutosBusiness;
    }

    public void setEstoqueProdutosBusiness(EstoqueProdutosBusiness estoqueProdutosBusiness) {
        this.estoqueProdutosBusiness = estoqueProdutosBusiness;
    }

}
