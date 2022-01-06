package br.com.furafila.mvc.locker.service;

import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.conjuntoLocker.model.ConjuntoLocker;
import br.com.furafila.mvc.locker.business.LockerBusiness;
import br.com.furafila.mvc.locker.model.Locker;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class LockerService {

    private LockerBusiness lockerBusiness = new LockerBusiness();

    public List<Locker> buscarLockerPorVolume(Integer volumeTotal,ConjuntoLocker conjuntoLocker) throws Exception {

        List<Locker> lstLocker = new ArrayList<>();
        List<List<String>> lstDados = getLockerBusiness().buscarLockersPorVolume(volumeTotal,conjuntoLocker);

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            for (List<String> lstValores : lstDados) {

                int index = 0;
                Locker l = new Locker();

                l.setIdLocker(Integer.parseInt(lstValores.get(index++)));
                l.setLockerDesc(lstValores.get(index++));
                l.getConjuntoLocker().setIdConjuntoLocker(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setIdDimensao(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setAltura(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setLargura(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setProfundidade(Integer.parseInt(lstValores.get(index++)));
                l.getStatus().setIdStatus(Integer.parseInt(lstValores.get(index++)));

                lstLocker.add(l);
            }
        }

        return lstLocker;

    }

    public LockerBusiness getLockerBusiness() {
        return lockerBusiness;
    }

    public void setLockerBusiness(LockerBusiness lockerBusiness) {
        this.lockerBusiness = lockerBusiness;
    }

}
