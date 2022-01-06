package br.com.furafila.mvc.conjuntoLocker.model;

import br.com.furafila.mvc.logradouro.model.Logradouro;
import br.com.furafila.utils.FuraFilaConstants;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ConjuntoLocker {

    private Integer idConjuntoLocker;
    private String conjuntoLockerDesc;
    private Integer nroLocalizacao;
    private Boolean status;
    private Logradouro logradouro = new Logradouro();
    private Integer qtdLockers = 0;

    public Integer getIdConjuntoLocker() {
        return idConjuntoLocker;
    }

    public void setIdConjuntoLocker(Integer id_conjunto_locker) {
        this.idConjuntoLocker = id_conjunto_locker;
    }

    public Integer getNroLocalizacao() {
        return nroLocalizacao;
    }

    public void setNroLocalizacao(Integer nro_localizacao) {
        this.nroLocalizacao = nro_localizacao;
    }

    public Character getStatusSQL() {
        return getStatus() ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO;
    }

    public String getStatusExibicao() {
        return getStatus() ? FuraFilaConstants.ATIVO : FuraFilaConstants.INATIVO;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public String getConjuntoLockerDesc() {
        return conjuntoLockerDesc;
    }

    public void setConjuntoLockerDesc(String conjunto_locker_desc) {
        this.conjuntoLockerDesc = conjunto_locker_desc;
    }

    public Integer getQtdLockers() {
        return qtdLockers;
    }

    public void setQtdLockers(Integer qtdLockers) {
        this.qtdLockers = qtdLockers;
    }

}
