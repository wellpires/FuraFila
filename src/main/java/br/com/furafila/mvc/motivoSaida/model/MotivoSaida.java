
package br.com.furafila.mvc.motivoSaida.model;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class MotivoSaida {
    private Integer idMotivo = 0;
    private String motivoSaida = "";

    public Integer getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Integer id_motivo) {
        this.idMotivo = id_motivo;
    }

    public String getMotivoSaida() {
        return motivoSaida;
    }

    public void setMotivoSaida(String motivoSaida) {
        this.motivoSaida = motivoSaida;
    }
    
    public MotivoSaida Clonar(){
        MotivoSaida ms = new MotivoSaida();
        
        ms.setIdMotivo(getIdMotivo());
        ms.setMotivoSaida(getMotivoSaida());
        
    
        return ms;
    }

}
