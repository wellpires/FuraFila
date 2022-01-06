
package br.com.furafila.mvc.formaPagamento.model;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class FormaPagamento {
    private Integer idFormaPagamento = 0;
    private String forma_pagamento = "";

    public Integer getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Integer id_forma_pagamento) {
        this.idFormaPagamento = id_forma_pagamento;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }
    
    public FormaPagamento Clonar(){
    FormaPagamento forma = new FormaPagamento();
        
        forma.setIdFormaPagamento(getIdFormaPagamento());
        forma.setForma_pagamento(getForma_pagamento());
       
        return forma;
    }
    
}
