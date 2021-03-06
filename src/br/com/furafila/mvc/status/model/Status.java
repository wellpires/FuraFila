package br.com.furafila.mvc.status.model;

import br.com.furafila.utils.FuraFilaConstants;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Status {
    private Integer idStatus= 0;
    private String status = "";

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer id_status) {
        this.idStatus = id_status;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusFormatado(){
        
        switch(getIdStatus()){
            
            case FuraFilaConstants.COD_LOCKER_EM_USO        : return FuraFilaConstants.LOCKER_EM_USO;
            case FuraFilaConstants.COD_LOCKER_LIVRE         : return FuraFilaConstants.LOCKER_LIVRE;
            case FuraFilaConstants.COD_EM_ANALISE           : return FuraFilaConstants.EM_ANALISE;
            case FuraFilaConstants.COD_EM_SEPARACAO         : return FuraFilaConstants.EM_SEPARACAO;
            case FuraFilaConstants.COD_ENCAMINHADO_LOCKER   : return FuraFilaConstants.ENCAMINHADO_LOCKER;
            case FuraFilaConstants.COD_APROVADO             : return FuraFilaConstants.APROVADO;
            case FuraFilaConstants.COD_REPROVADO            : return FuraFilaConstants.REPROVADO;
            case FuraFilaConstants.COD_PRODUTO_ENTREGUE     : return FuraFilaConstants.PRODUTO_ENTREGUE;
                default: return "";
            
        }
        
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Status clonar(){
        Status s = new Status();
        s.setIdStatus(getIdStatus());
        s.setStatus(getStatus());
        return s;
    }
    
}