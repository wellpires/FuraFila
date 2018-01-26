package br.com.furafila.mvc.conjuntoLocker.service;

import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.cliente.model.Cliente;
import br.com.furafila.mvc.conjuntoLocker.business.ConjuntoLockerBusiness;
import br.com.furafila.mvc.conjuntoLocker.model.ConjuntoLocker;
import br.com.furafila.mvc.locker.model.Locker;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ConjuntoLockerService {

    private ConjuntoLockerBusiness conjuntoLockerBusiness = new ConjuntoLockerBusiness();

    public List<ConjuntoLocker> listarConjuntoLocker() throws Exception {

        List<List<String>> lstDados = getConjuntoLockerBusiness().listarConjuntoLockers();
        List<ConjuntoLocker> lstConjuntoLockers = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            for (List<String> lstValores : lstDados) {

                int index = 0;
                ConjuntoLocker cl = new ConjuntoLocker();

                cl.setIdConjuntoLocker(Integer.parseInt(lstValores.get(index++)));
                cl.setConjuntoLockerDesc(lstValores.get(index++));
                cl.setNroLocalizacao(Integer.parseInt(lstValores.get(index++)));
                cl.getLogradouro().setNroCep(Integer.parseInt(lstValores.get(index++)));
                cl.getLogradouro().setLatitude(Double.parseDouble(lstValores.get(index++)));
                cl.getLogradouro().setLongitude(Double.parseDouble(lstValores.get(index++)));
                cl.getLogradouro().getTipoLogradouro().setDescTipoLogradouro(lstValores.get(index++));
                cl.getLogradouro().setLogradouro(lstValores.get(index++));
                cl.getLogradouro().getBairro().setDescBairro(lstValores.get(index++));
                cl.getLogradouro().getBairro().getCidade().setDescCidade(lstValores.get(index++));
                cl.getLogradouro().getBairro().getCidade().getUf().setDescUf(lstValores.get(index++));
                cl.setStatus(lstValores.get(index++).charAt(0) == FuraFilaConstants.COD_ATIVO);
                cl.setQtdLockers(Integer.parseInt(lstValores.get(index++)));

                lstConjuntoLockers.add(cl);

            }
        }

        return lstConjuntoLockers;

    }

    public List<Locker> listarLockerPorConjunto(ConjuntoLocker conjuntoLocker) throws Exception {

        List<List<String>> lstDados = getConjuntoLockerBusiness().listarLockersPorConjunto(conjuntoLocker);
        List<Locker> lstLockers = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            for (List<String> lstValores : lstDados) {
                int index = 0;
                Locker l = new Locker();

                l.setIdLocker(Integer.parseInt(lstValores.get(index++)));
                l.setLockerDesc(lstValores.get(index++));
                l.setBolStatus(lstValores.get(index++).charAt(0) == FuraFilaConstants.COD_ATIVO);
                l.getStatus().setIdStatus(Integer.parseInt(lstValores.get(index++)));
                l.getStatus().setStatus(lstValores.get(index++));
                l.getDimensao().setIdDimensao(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setDimensaoDesc(lstValores.get(index++));
                l.getDimensao().setAltura(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setLargura(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setProfundidade(Integer.parseInt(lstValores.get(index++)));

                lstLockers.add(l);
            }
        }

        return lstLockers;

    }

    public List<Locker> listarLockersProximos(Cliente cliente) throws Exception {

        List<List<String>> lstDados = getConjuntoLockerBusiness().listarLockersProximos(cliente);
        List<Locker> lstLockersProximos = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {

            for (List<String> lstValores : lstDados) {
                int index = 0;
                Locker locker = new Locker();

                locker.getConjuntoLocker().setIdConjuntoLocker(Integer.parseInt(lstValores.get(index++)));
                locker.getConjuntoLocker().setConjuntoLockerDesc(lstValores.get(index++));
                locker.setIdLocker(Integer.parseInt(lstValores.get(index++)));
                locker.setLockerDesc(lstValores.get(index++));
                locker.getDimensao().setDimensaoDesc(lstValores.get(index++));
                locker.getConjuntoLocker().getLogradouro().setNroCep(Integer.parseInt(lstValores.get(index++)));
                
                lstLockersProximos.add(locker);
            }
        }
        
        return lstLockersProximos;
        
    }

    public ConjuntoLockerBusiness getConjuntoLockerBusiness() {
        return conjuntoLockerBusiness;
    }

    public void setConjuntoLockerBusiness(ConjuntoLockerBusiness conjuntoLockerBusiness) {
        this.conjuntoLockerBusiness = conjuntoLockerBusiness;
    }

}
