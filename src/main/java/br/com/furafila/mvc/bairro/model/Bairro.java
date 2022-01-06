package br.com.furafila.mvc.bairro.model;

import br.com.furafila.mvc.cidade.model.Cidade;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Bairro {

    private Integer idBairro = 0;
    private String descBairro = "";
    private Cidade cidade = new Cidade();

    public Integer getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Integer id_bairro) {
        this.idBairro = id_bairro;
    }

    public String getDescBairro() {
        return descBairro;
    }

    public void setDescBairro(String desc_bairro) {
        this.descBairro = desc_bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Bairro clonar() {
        Bairro bairro = new Bairro();
        bairro.setIdBairro(getIdBairro());
        bairro.setDescBairro(getDescBairro());
        bairro.setCidade(getCidade());
        return bairro;
    }

    public boolean objetoVazio() {
        return 0 == getIdBairro() && "".equals(getDescBairro()) && getCidade().objetoVazio();
    }

    public void zerarObjeto() {
        setIdBairro(0);
        setDescBairro("");
        getCidade().zerarObjeto();
    }

}
