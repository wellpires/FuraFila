package br.com.furafila.mvc.locker.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.furafila.mvc.bairro.business.BairroBusiness;
import br.com.furafila.mvc.bairro.service.BairroService;
import br.com.furafila.mvc.cep.service.CepService;
import br.com.furafila.mvc.cidade.business.CidadeBusiness;
import br.com.furafila.mvc.cidade.service.CidadeService;
import br.com.furafila.mvc.cliente.model.Cliente;
import br.com.furafila.mvc.comanda.bean.ComandaBean;
import br.com.furafila.mvc.conjuntoLocker.model.ConjuntoLocker;
import br.com.furafila.mvc.conjuntoLocker.service.ConjuntoLockerService;
import br.com.furafila.mvc.dimensao.business.DimensaoBusiness;
import br.com.furafila.mvc.locker.business.LockerBusiness;
import br.com.furafila.mvc.locker.model.Locker;
import br.com.furafila.mvc.locker.service.LockerService;
import br.com.furafila.mvc.logradouro.business.LogradouroBusiness;
import br.com.furafila.mvc.logradouro.service.LogradouroService;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@ViewScoped
public class LockerBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(ComandaBean.class);
    
    private ConjuntoLocker conjuntoLocker = new ConjuntoLocker();

    private LockerBusiness lockerBusiness = new LockerBusiness();
    private CidadeBusiness cidadeBusiness = new CidadeBusiness();
    private BairroBusiness bairroBusiness = new BairroBusiness();
    private LogradouroBusiness logradouroBusiness = new LogradouroBusiness();
    private DimensaoBusiness dimensaoBusiness = new DimensaoBusiness();

    private LockerService lockerService = new LockerService();
    private CepService cepService = new CepService();
    private CidadeService cidadeService = new CidadeService();
    private BairroService bairroService = new BairroService();
    private LogradouroService logradouroService = new LogradouroService();
    private ConjuntoLockerService conjuntoLockerService = new ConjuntoLockerService();

    private List<Locker> lstLockers = new ArrayList<>();

    public void gerarRelatorio(ActionEvent ae) {

        try {

            Cliente cliente = pegarDadosSessaoCliente().clonar();

            FuraFilaUtils.gerarRelatorios("lockersProximos", getConjuntoLockerService().listarLockersProximos(cliente), "LOCKERS_PROXIMOS");

        } catch (JRException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (IOException ex) {
        	logger.error(ex.getMessage(), ex);
        } catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
        }

    }

    private Cliente pegarDadosSessaoCliente() {
        return (Cliente) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_CLIENTE);
    }

    public List<Locker> getLstLockers() {
        return lstLockers;
    }

    public void setLstLockers(List<Locker> lstLockers) {
        this.lstLockers = lstLockers;
    }

    public LockerBusiness getLockerBusiness() {
        return lockerBusiness;
    }

    public void setLockerBusiness(LockerBusiness lockerBusiness) {
        this.lockerBusiness = lockerBusiness;
    }

    public LockerService getLockerService() {
        return lockerService;
    }

    public void setLockerService(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    public CepService getCepService() {
        return cepService;
    }

    public void setCepService(CepService cepService) {
        this.cepService = cepService;
    }

    public CidadeBusiness getCidadeBusiness() {
        return cidadeBusiness;
    }

    public void setCidadeBusiness(CidadeBusiness cidadeBusiness) {
        this.cidadeBusiness = cidadeBusiness;
    }

    public BairroBusiness getBairroBusiness() {
        return bairroBusiness;
    }

    public void setBairroBusiness(BairroBusiness bairroBusiness) {
        this.bairroBusiness = bairroBusiness;
    }

    public LogradouroBusiness getLogradouroBusiness() {
        return logradouroBusiness;
    }

    public void setLogradouroBusiness(LogradouroBusiness logradouroBusiness) {
        this.logradouroBusiness = logradouroBusiness;
    }

    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    public BairroService getBairroService() {
        return bairroService;
    }

    public void setBairroService(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    public LogradouroService getLogradouroService() {
        return logradouroService;
    }

    public void setLogradouroService(LogradouroService logradouroService) {
        this.logradouroService = logradouroService;
    }

    public DimensaoBusiness getDimensaoBusiness() {
        return dimensaoBusiness;
    }

    public void setDimensaoBusiness(DimensaoBusiness dimensaoBusiness) {
        this.dimensaoBusiness = dimensaoBusiness;
    }

    public ConjuntoLocker getConjuntoLocker() {
        return conjuntoLocker;
    }

    public void setConjuntoLocker(ConjuntoLocker conjuntoLocker) {
        this.conjuntoLocker = conjuntoLocker;
    }

    public ConjuntoLockerService getConjuntoLockerService() {
        return conjuntoLockerService;
    }

    public void setConjuntoLockerService(ConjuntoLockerService conjuntoLockerService) {
        this.conjuntoLockerService = conjuntoLockerService;
    }

}
