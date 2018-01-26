package br.com.furafila.mvc.uf.model;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class UF {

    private Integer idUf = 0;
    private String siglaUf = "";
    private String descUf = "";

    public Integer getIdUf() {
        return idUf;
    }

    public void setIdUf(Integer id_uf) {
        this.idUf = id_uf;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String sigla_uf) {
        this.siglaUf = sigla_uf;
    }

    public String getDescUf() {
        return descUf;
    }

    public void setDescUf(String desc_uf) {
        this.descUf = desc_uf;
    }

    public UF clonar() {

        UF uf = new UF();

        uf.setIdUf(getIdUf());
        uf.setSiglaUf(getSiglaUf());
        uf.setDescUf(getDescUf());

        return uf;

    }

    public boolean objetoVazio() {
        return 0 == getIdUf() && "".equals(getDescUf()) && "".equals(getSiglaUf());
    }

    public void zerarObjeto() {
        setIdUf(0);
        setSiglaUf("");
        setDescUf("");
    }

}
