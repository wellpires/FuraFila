package br.com.furafila.mvc.cidade.model;

import br.com.furafila.mvc.uf.model.UF;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Cidade {

    private Integer idCidade = 0;
    private String descCidade = "";
    private UF uf = new UF();

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer id_cidade) {
        this.idCidade = id_cidade;
    }

    public String getDescCidade() {
        return descCidade;
    }

    public void setDescCidade(String desc_cidade) {
        this.descCidade = desc_cidade;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public Cidade clonar() {

        Cidade cidade = new Cidade();
        cidade.setIdCidade(getIdCidade());
        cidade.setDescCidade(getDescCidade());
        cidade.setUf(getUf());

        return cidade;

    }

    public boolean objetoVazio() {
        return 0 == getIdCidade() && "".equals(getDescCidade()) && getUf().objetoVazio();
    }

    public void zerarObjeto() {
        setIdCidade(0);
        setDescCidade("");
        getUf().zerarObjeto();
    }

}
