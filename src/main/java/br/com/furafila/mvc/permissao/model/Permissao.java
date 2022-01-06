package br.com.furafila.mvc.permissao.model;

/**
 *
 * @author Wellington Gonçalves Pires
 */

public class Permissao {
    
    private Integer idPermissao = 0;
    private String permissao = ""; 
    private String siglaPermissao = "";

    public Integer getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Integer id_permissao) {
        this.idPermissao = id_permissao;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getSiglaPermissao() {
        return siglaPermissao;
    }

    public void setSiglaPermissao(String sigla_permissao) {
        this.siglaPermissao = sigla_permissao;
    }

    public Permissao Clonar(){
    
        Permissao permissao = new Permissao();
    
        permissao.setIdPermissao(getIdPermissao());
        permissao.setPermissao(getPermissao());
        permissao.setSiglaPermissao(getSiglaPermissao());
            
    return permissao;
    }

    public void zerarObjeto(){
        setIdPermissao(0);
        setPermissao("");
        setSiglaPermissao("");
    }
    
}
