package br.com.furafila.mvc.bairro.service;

import java.util.List;

import br.com.furafila.mvc.bairro.business.BairroBusiness;
import br.com.furafila.mvc.bairro.model.Bairro;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class BairroService {

    private BairroBusiness bairroBusiness = new BairroBusiness();

    public Bairro buscarBairro(Bairro bairro) throws Exception {

        List<String> lstDados = getBairroBusiness().buscarBairro(bairro);

        if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
            bairro.setId_bairro(Integer.parseInt(lstDados.get(0)));
            bairro.setDesc_bairro(lstDados.get(1));
        } else {
            bairro = new Bairro();
        }
        return bairro;

    }

    public BairroBusiness getBairroBusiness() {
        return bairroBusiness;
    }

    public void setBairroBusiness(BairroBusiness bairroBusiness) {
        this.bairroBusiness = bairroBusiness;
    }

}
