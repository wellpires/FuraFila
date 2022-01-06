
package br.com.furafila.mvc.estoque.model;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Estoque {
    private Integer idEstoque = 0;
    private Estabelecimento estabelecimento = new Estabelecimento();

    public Integer getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Integer id_estoque) {
        this.idEstoque = id_estoque;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
    
   public Estoque Clonar(){
        Estoque estoque = new Estoque();
        
        estoque.setEstabelecimento(getEstabelecimento());
        estoque.setIdEstoque(getIdEstoque());
        
        return estoque;
    }
            
}
