package br.com.furafila.mvc.locker.model;

import br.com.furafila.mvc.conjuntoLocker.model.ConjuntoLocker;
import br.com.furafila.mvc.dimensao.model.Dimensao;
import br.com.furafila.mvc.status.model.Status;
import br.com.furafila.utils.FuraFilaConstants;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Locker {

    private Integer idLocker;
    private String lockerDesc = "";
    private Boolean bolStatus;
    private ConjuntoLocker conjuntoLocker = new ConjuntoLocker();
    private Dimensao dimensao = new Dimensao();
    private Status status = new Status();

    public Integer getIdLocker() {
        return idLocker;
    }

    public void setIdLocker(Integer id_locker) {
        this.idLocker = id_locker;
    }

    public String getLockerDesc() {
        return lockerDesc;
    }

    public void setLockerDesc(String locker_desc) {
        if (!"".equals(locker_desc)) {
            this.lockerDesc = locker_desc;
        }
    }

    public ConjuntoLocker getConjuntoLocker() {
        return conjuntoLocker;
    }

    public void setConjuntoLocker(ConjuntoLocker conjuntoLocker) {
        this.conjuntoLocker = conjuntoLocker;
    }

    public Dimensao getDimensao() {
        return dimensao;
    }

    public void setDimensao(Dimensao dimensao) {
        this.dimensao = dimensao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getBolStatusExibicao(){
        return bolStatus ? FuraFilaConstants.ATIVO : FuraFilaConstants.INATIVO;
    }
    
    public Character getBolStatusSQL(){
        return bolStatus ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO;
    }
    
    public Boolean getBolStatus() {
        return bolStatus;
    }

    public void setBolStatus(Boolean bolStatus) {
        this.bolStatus = bolStatus;
    }
    
    public Locker clonar() {

        Locker locker = new Locker();
        locker.setIdLocker(getIdLocker());
        locker.setLockerDesc(getLockerDesc());
        locker.setDimensao(getDimensao());
        locker.setBolStatus(getBolStatus());

        return locker;
    }


}
