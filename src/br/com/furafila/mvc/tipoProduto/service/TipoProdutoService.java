package br.com.furafila.mvc.tipoProduto.service;

import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.tipoProduto.business.TipoProdutoBusiness;
import br.com.furafila.mvc.tipoProduto.model.TipoProduto;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class TipoProdutoService implements ITipoProdutoService{

    private TipoProdutoBusiness tipoProdutoBusiness = new TipoProdutoBusiness();

    @Override
    public List<TipoProduto> listarTipoProduto(boolean isAdministador) throws Exception {

        TipoProduto tipoProduto;
        List<TipoProduto> lstTipoProdutos = new ArrayList<>();
        List<List<String>> lstDados = getTipoProdutoBusiness().listarTipoProduto(isAdministador);

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {

            for (List<String> valor : lstDados) {

                tipoProduto = new TipoProduto();
                int indice = 0;

                tipoProduto.setId_tipo_produto(Integer.parseInt(valor.get(indice++)));
                tipoProduto.setTipo_produto_desc(valor.get(indice++));
                tipoProduto.setStatus(valor.get(indice++).charAt(0) == FuraFilaConstants.COD_ATIVO);

                lstTipoProdutos.add(tipoProduto);

            }
        }

        return lstTipoProdutos;

    }

    @Override
    public int pegarTipoProduto(TipoProduto tipoProduto) throws Exception {

        List<String> lstDados = getTipoProdutoBusiness().listarTipoProduto(tipoProduto);

        return lstDados.size();

    }

    public TipoProdutoBusiness getTipoProdutoBusiness() {
        return tipoProdutoBusiness;
    }

    public void setTipoProdutoBusiness(TipoProdutoBusiness tipoProdutoBusiness) {
        this.tipoProdutoBusiness = tipoProdutoBusiness;
    }

}
