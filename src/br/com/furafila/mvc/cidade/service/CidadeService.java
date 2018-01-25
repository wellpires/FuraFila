package br.com.furafila.mvc.cidade.service;

import java.util.List;

import br.com.furafila.mvc.cidade.business.CidadeBusiness;
import br.com.furafila.mvc.cidade.model.Cidade;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class CidadeService {
    
    private CidadeBusiness cidadeBusiness = new CidadeBusiness();
    
    public Cidade buscarCidade(Cidade cidade) throws Exception{
        
        List<String> lstDados = getCidadeBusiness().buscarCidade(cidade);
        
        if(!FuraFilaUtils.listaVaziaNula(lstDados)){
            cidade.setId_cidade(Integer.parseInt(lstDados.get(0)));
            cidade.setDesc_cidade(lstDados.get(1));
        }
        else{
            cidade = new Cidade();
        }
        
        return cidade;
        
    }

    public CidadeBusiness getCidadeBusiness() {
        return cidadeBusiness;
    }

    public void setCidadeBusiness(CidadeBusiness cidadeBusiness) {
        this.cidadeBusiness = cidadeBusiness;
    }
    
}
