package br.com.furafila.mvc.comanda.service;

import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.comanda.business.ComandaBusiness;
import br.com.furafila.mvc.comanda.model.Comanda;
import br.com.furafila.mvc.pedidoLocker.model.PedidoLocker;
import br.com.furafila.mvc.pedidos.model.Pedidos;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Goncalves Pires
 */
public class ComandaService {

    private ComandaBusiness comandaBusiness = new ComandaBusiness();

    public List<PedidoLocker> listarComandasAprovadas(String complementoQuery) throws Exception {

        List<List<String>> lstDados = getComandaBusiness().listarComandasAprovadas(complementoQuery);
        List<PedidoLocker> lstComandasAprovadas = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            for (List<String> lstValores : lstDados) {

                int index = 0;
                PedidoLocker pl = new PedidoLocker();

                pl.getPedidos().getComanda().setIdComanda(lstValores.get(index++));
                pl.getPedidos().getComanda().setCodigoPedido(lstValores.get(index++));
                pl.getPedidos().getComanda().getStatus().setIdStatus(Integer.parseInt(lstValores.get(index++)));
                pl.getPedidos().getComanda().getStatus().setStatus(lstValores.get(index++));
                pl.getPedidos().getComanda().getEstabelecimento().setIdEstabelecimento(Integer.parseInt(lstValores.get(index++)));
                pl.getPedidos().getComanda().getEstabelecimento().setRazaoSocial(lstValores.get(index++));
                pl.getPedidos().getComanda().getEstabelecimento().setCnpj(Long.parseLong(lstValores.get(index++)));
                pl.getLocker().getConjuntoLocker().setConjuntoLockerDesc(lstValores.get(index++));
                pl.getLocker().setIdLocker(Integer.parseInt(lstValores.get(index++)));
                pl.getLocker().setLockerDesc(lstValores.get(index++));
                pl.getLocker().getConjuntoLocker().getLogradouro().getTipoLogradouro().setDescTipoLogradouro(lstValores.get(index++));
                pl.getLocker().getConjuntoLocker().getLogradouro().setNroCep(Integer.parseInt(lstValores.get(index++)));
                pl.getLocker().getConjuntoLocker().getLogradouro().setLogradouro(lstValores.get(index++));
                pl.getLocker().getConjuntoLocker().getLogradouro().setLatitude(Double.parseDouble(lstValores.get(index++)));
                pl.getLocker().getConjuntoLocker().getLogradouro().setLongitude(Double.parseDouble(lstValores.get(index++)));
                pl.getLocker().getConjuntoLocker().getLogradouro().getBairro().setDescBairro(lstValores.get(index++));
                pl.getLocker().getConjuntoLocker().getLogradouro().getBairro().getCidade().setDescCidade(lstValores.get(index++));
                pl.getLocker().getConjuntoLocker().getLogradouro().getBairro().getCidade().getUf().setSiglaUf(lstValores.get(index++));
                pl.getPedidos().getComanda().getCliente().setNome(lstValores.get(index++));
                pl.getPedidos().getComanda().getCliente().setEmail(lstValores.get(index++));

                lstComandasAprovadas.add(pl);

            }
        }

        return lstComandasAprovadas;
    }

    public List<Pedidos> listarProdutosPorComanda(Comanda comanda) throws Exception {

        List<List<String>> lstDados = getComandaBusiness().listarProdutosPorComanda(comanda);
        List<Pedidos> lstProdutos = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            for (List<String> lstValores : lstDados) {

                int index = 0;
                Pedidos p = new Pedidos();
                p.getProduto().setIdProduto(Integer.parseInt(lstValores.get(index++)));
                p.getProduto().setProdutoDesc(lstValores.get(index++));
                p.getProduto().setValorUnitario(Double.parseDouble(lstValores.get(index++)));
                p.setQtd(Integer.parseInt(lstValores.get(index++)));
                lstProdutos.add(p);

            }
        }

        return lstProdutos;
    }

    public PedidoLocker buscarEstabelecimentoLocker(Comanda comanda) throws Exception {

        List<String> lstDados = getComandaBusiness().buscarEstabelecimentoLocker(comanda);
        PedidoLocker pedidoLocker = new PedidoLocker();

        if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
            int index = 0;
            pedidoLocker.getPedidos().getComanda().getEstabelecimento().setIdEstabelecimento(Integer.parseInt(lstDados.get(index++)));
            pedidoLocker.getLocker().setIdLocker(Integer.parseInt(lstDados.get(index++)));

        }

        return pedidoLocker;
    }

    public ComandaBusiness getComandaBusiness() {
        return comandaBusiness;
    }

    public void setComandaBusiness(ComandaBusiness comandaBusiness) {
        this.comandaBusiness = comandaBusiness;
    }

}
