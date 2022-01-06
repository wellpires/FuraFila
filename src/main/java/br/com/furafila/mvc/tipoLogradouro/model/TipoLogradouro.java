package br.com.furafila.mvc.tipoLogradouro.model;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class TipoLogradouro {
    
    private Integer idTipoLogradouro = 0;
    private String descTipoLogradouro = "";

    public Integer getIdTipoLogradouro() {
        return idTipoLogradouro;
    }

    public void setIdTipoLogradouro(Integer id_tipo_logradouro) {
        this.idTipoLogradouro = id_tipo_logradouro;
    }

    public String getDescTipoLogradouro() {
        return descTipoLogradouro;
    }

    public void setDescTipoLogradouro(String desc_tipo_logradouro) {
        this.descTipoLogradouro = desc_tipo_logradouro;
    }
    
    public boolean objetoVazio(){
        return 0 == getIdTipoLogradouro() && "".equals(getDescTipoLogradouro());
    }
    
    public void zerarObjeto(){
        setIdTipoLogradouro(0);
        setDescTipoLogradouro("");
    }
    
}
