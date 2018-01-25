package br.com.furafila.mvc.imagem.business;

import br.com.furafila.mvc.imagem.model.Imagem;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ImagemBusiness {
    
    public void gravar(Imagem imagem) throws Exception{
        imagem.setId_imagem(BancoDados.inserirImagem(imagem.getImagem()));
    }
    
    public void alterar(Imagem imagem) throws Exception{
        BancoDados.alterarImagem(imagem.getImagem(), imagem.getId_imagem());
    }
    
}
