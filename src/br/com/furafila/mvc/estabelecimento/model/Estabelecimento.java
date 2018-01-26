package br.com.furafila.mvc.estabelecimento.model;

import br.com.furafila.mvc.imagem.model.Imagem;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Estabelecimento {

    private Integer idEstabelecimento = 0;
    private String razaoSocial = "";
    private Long inscricaoEstadual;
    private Long cnpj = 0L;
    private Boolean status;
    private String email = "";
    private Imagem imagem = new Imagem();

    public Integer getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(Integer id_estabelecimento) {
        this.idEstabelecimento = id_estabelecimento;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razao_social) {
        this.razaoSocial = razao_social;
    }

    public Long getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(Long inscricao_estadual) {
        this.inscricaoEstadual = inscricao_estadual;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpjFormatado() {
        String cnpjFormatado = "";
        try {
            cnpjFormatado = FuraFilaUtils.formataCnpj(cnpj);
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
        return cnpjFormatado;
    }

    public void setCnpjFormatado(String cnpj) {
        this.cnpj = Long.parseLong("".equals(cnpj) ? "0" : cnpj.replaceAll("[.|//|-]", ""));
    }

    public String getStatusFormatado() {
        if (status != null) {
            return status ? FuraFilaConstants.ATIVO : FuraFilaConstants.INATIVO;
        } else {
            return FuraFilaConstants.ATIVO;
        }
    }

    public String getStatusSQL() {
        return String.valueOf(status ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        if (status != null) {
            this.status = status;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public boolean objetoVazio() {
        return FuraFilaUtils.valorZerado(getIdEstabelecimento())
                && FuraFilaUtils.valorVazioNulo(getRazaoSocial())
                && FuraFilaUtils.valorZerado(getInscricaoEstadual())
                && FuraFilaUtils.valorZerado(getCnpj())
                && FuraFilaUtils.valorVazioNulo(getEmail())
                && getImagem().objetoVazio();

    }

    public Estabelecimento clonar() {
        
        Estabelecimento e = new Estabelecimento();

        e.setCnpj(getCnpj());
        e.setEmail(getEmail());
        e.setInscricaoEstadual(getInscricaoEstadual());
        e.setRazaoSocial(getRazaoSocial());
        e.setIdEstabelecimento(getIdEstabelecimento());
        e.setImagem(getImagem());
        e.setStatus(getStatus());

        return e;
    }

    public void zerarObjeto() {
        this.idEstabelecimento = 0;
        this.razaoSocial = "";
        this.inscricaoEstadual = 0L;
        this.cnpj = 0L;
        this.status = null;
        this.email = "";
        this.imagem.zerarObjeto();

    }

}
