package br.com.furafila.mvc.filiais.model;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.logradouro.model.Logradouro;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Filiais {

    private Integer idFilial = 0;
    private Long telefone = 0L;
    private Long outroTelefone = 0L;
    private String email = "";
    private Logradouro logradouro = new Logradouro();
    private Estabelecimento estabelecimento = new Estabelecimento();

    public Integer getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(Integer id_filial) {
        this.idFilial = id_filial;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public Long getOutroTelefone() {
        return outroTelefone;
    }

    public void setOutroTelefone(Long outro_telefone) {
        this.outroTelefone = outro_telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

  
    
}
