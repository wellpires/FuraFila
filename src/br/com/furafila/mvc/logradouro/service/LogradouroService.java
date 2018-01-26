package br.com.furafila.mvc.logradouro.service;

import java.util.List;

import br.com.furafila.mvc.logradouro.business.LogradouroBusiness;
import br.com.furafila.mvc.logradouro.model.Logradouro;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class LogradouroService {
    
    private LogradouroBusiness logradouroBusiness = new LogradouroBusiness();

    public Logradouro buscarLogradouro(Logradouro logradouro) throws Exception{
        
        List<String> lstDados = getLogradouroBusiness().buscarLogradouro(logradouro);
        
        if(!FuraFilaUtils.listaVaziaNula(lstDados)){
            logradouro.setNroCep(Integer.parseInt(lstDados.get(0)));
            logradouro.setLogradouro(lstDados.get(1));
            logradouro.getTipoLogradouro().setDescTipoLogradouro(lstDados.get(2));
        }
        else{
            logradouro = new Logradouro();
        }
        
        return logradouro;
        
    }
    
    public boolean logradouroExiste(Logradouro logradouro) throws Exception{
        
        List<String> lstDados = getLogradouroBusiness().buscarLogradouro(logradouro);
        
        return !FuraFilaUtils.listaVaziaNula(lstDados);
        
    }
    
    public void buscarEnderecoCompleto(Logradouro logradouro) throws Exception{
        
       List<String> lstDados = getLogradouroBusiness().buscarEnderecoCompleto(logradouro);
       
       if(!FuraFilaUtils.listaVaziaNula(lstDados)){

           int index = 0;
           logradouro.setNroCep(Integer.parseInt(lstDados.get(index++)));
           logradouro.getTipoLogradouro().setDescTipoLogradouro(lstDados.get(index++));
           logradouro.setLogradouro(lstDados.get(index++));
           logradouro.getBairro().setDescBairro(lstDados.get(index++));
           logradouro.getBairro().getCidade().setDescCidade(lstDados.get(index++));
           logradouro.getBairro().getCidade().getUf().setSiglaUf(lstDados.get(index++));
           
       }
        
    }
    
    public LogradouroBusiness getLogradouroBusiness() {
        return logradouroBusiness;
    }

    public void setLogradouroBusiness(LogradouroBusiness logradouroBusiness) {
        this.logradouroBusiness = logradouroBusiness;
    }
    
}
