package br.com.furafila.mvc.estoque.service;

import java.util.List;

import br.com.furafila.mvc.estoque.business.EstoqueBusiness;
import br.com.furafila.mvc.estoque.model.Estoque;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueService {

    private EstoqueBusiness estoqueBusiness = new EstoqueBusiness();

    public boolean estoqueExiste(Estoque estoque) throws Exception {

        List<String> lstDados = getEstoqueBusiness().verificarEstoqueExiste(estoque);

        Boolean estoqueExiste = false;

        if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
            estoqueExiste = Integer.parseInt(lstDados.get(0)) > 0;
        }

        return estoqueExiste;

    }



    public void buscarCodigoEstoque(Estoque estoque) throws Exception {

        List<String> lstDados = getEstoqueBusiness().buscarCodigoEstoque(estoque);

        if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
            estoque.setId_estoque(Integer.parseInt(lstDados.get(0)));
        }

    }

    public EstoqueBusiness getEstoqueBusiness() {
        return estoqueBusiness;
    }

    public void setEstoqueBusiness(EstoqueBusiness estoqueBusiness) {
        this.estoqueBusiness = estoqueBusiness;
    }

}
