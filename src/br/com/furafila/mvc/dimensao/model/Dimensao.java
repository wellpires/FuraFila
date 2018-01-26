package br.com.furafila.mvc.dimensao.model;

import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Dimensao {

    private Integer idDimensao = 0;
    private String dimensaoDesc;
    private Integer altura;
    private Integer largura;
    private Integer profundidade;

    public Integer getIdDimensao() {
        return idDimensao;
    }

    public void setIdDimensao(Integer id_dimensao) {
        this.idDimensao = id_dimensao;
    }

    public String getDimensaoDesc() {
        return dimensaoDesc;
    }

    public void setDimensaoDesc(String dimensao_desc) {
        this.dimensaoDesc = dimensao_desc;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        if(altura != 0)
            this.altura = altura;
    }

    public Integer getLargura() {
        return largura;
    }

    public void setLargura(Integer largura) {
        if(largura != 0)
            this.largura = largura;
    }

    public Integer getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Integer profundidade) {
        if(profundidade != 0)
            this.profundidade = profundidade;
    }

    public Integer calcularVolume(){
        return FuraFilaUtils.calcularVolume(getAltura() , getLargura(), getProfundidade());
    }
    
    public Dimensao clonar() {
        Dimensao d = new Dimensao();

        d.setAltura(getAltura());
        d.setDimensaoDesc(getDimensaoDesc());
        d.setIdDimensao(getIdDimensao());
        d.setLargura(getLargura());
        d.setProfundidade(getProfundidade());

        return d;
    }

}
