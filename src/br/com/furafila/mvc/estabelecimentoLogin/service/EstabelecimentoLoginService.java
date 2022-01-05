package br.com.furafila.mvc.estabelecimentoLogin.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.estabelecimentoLogin.business.EstabelecimentoLoginBusiness;
import br.com.furafila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import br.com.furafila.mvc.imagem.business.ImagemBusiness;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstabelecimentoLoginService {

    private EstabelecimentoLoginBusiness estabelecimentoLoginBusiness = new EstabelecimentoLoginBusiness();
    private ImagemBusiness imagemBusiness = new ImagemBusiness();

    public void buscarInformacoesIniciaisEstabelecimento(EstabelecimentoLogin estabelecimentoLogin) throws Exception {

        List<String> lstDados = getEstabelecimentoLoginBusiness().buscarInformacoesIniciaisEstabelecimento(estabelecimentoLogin);

        if (!FuraFilaUtils.listaVaziaNula(lstDados)) {

            Integer indice = 0;

            estabelecimentoLogin.getEstabelecimento().setIdEstabelecimento(Integer.parseInt(lstDados.get(indice++)));
            estabelecimentoLogin.getEstabelecimento().setRazaoSocial(lstDados.get(indice++));
            estabelecimentoLogin.getEstabelecimento().setStatus(lstDados.get(indice++).charAt(0) == FuraFilaConstants.COD_ATIVO);
            estabelecimentoLogin.getEstabelecimento().getImagem().setIdImagem(Integer.parseInt(lstDados.get(indice++)));

        }
        
        File imagem = imagemBusiness.buscarImagemPorId(estabelecimentoLogin.getEstabelecimento().getImagem().getIdImagem());
        estabelecimentoLogin.getEstabelecimento().getImagem().setImagem(imagem.getAbsolutePath());

    }
    
    public EstabelecimentoLoginBusiness getEstabelecimentoLoginBusiness() {
        return estabelecimentoLoginBusiness;
    }

    public void setEstabelecimentoLoginBusiness(EstabelecimentoLoginBusiness estabelecimentoLoginBusiness) {
        this.estabelecimentoLoginBusiness = estabelecimentoLoginBusiness;
    }

    public List<EstabelecimentoLogin> listarUsuarios(EstabelecimentoLogin estabelecimentoLogin) throws Exception {

        List<List<String>> lstDados = getEstabelecimentoLoginBusiness().listarUsuarios(estabelecimentoLogin);
        List<EstabelecimentoLogin> lstEstabelecimentoLogin = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {

            for (List<String> valores : lstDados) {

                EstabelecimentoLogin el = new EstabelecimentoLogin();
                el.getLogin().setIdLogin(Integer.parseInt(valores.get(0)));
                el.getLogin().setUsuario(valores.get(1));
                lstEstabelecimentoLogin.add(el);

            }

        }

        return lstEstabelecimentoLogin;
        
    }

}
