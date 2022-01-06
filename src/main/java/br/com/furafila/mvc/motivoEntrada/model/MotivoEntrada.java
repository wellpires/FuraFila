
package br.com.furafila.mvc.motivoEntrada.model;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class MotivoEntrada {
    private Integer idMotivoEntrada = 0;
    private String motivoEntrada ="";

    public Integer getIdMotivoEntrada() {
        return idMotivoEntrada;
    }

    public void setIdMotivoEntrada(Integer id_motivo_entrada) {
        this.idMotivoEntrada = id_motivo_entrada;
    }

    public String getMotivoEntrada() {
        return motivoEntrada;
    }

    public void setMotivoEntrada(String motivoEntrada) {
        this.motivoEntrada = motivoEntrada;
    }
    
    public MotivoEntrada Clonar(){
        MotivoEntrada me = new MotivoEntrada();
        
        me.setIdMotivoEntrada(getIdMotivoEntrada());
        me.setMotivoEntrada(me.getMotivoEntrada());
    
        return me;
    }
}
