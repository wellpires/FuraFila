package br.com.furafila.mvc.logradouro.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.furafila.mvc.bairro.model.Bairro;
import br.com.furafila.mvc.tipoLogradouro.model.TipoLogradouro;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Logradouro {

	private static final Logger logger = LogManager.getLogger(Logradouro.class);
	
    private Integer nroCep = 0;
    private String logradouro = "";
    private Double latitude;
    private Double longitude;
    private TipoLogradouro tipoLogradouro = new TipoLogradouro();
    private Bairro bairro = new Bairro();

    public Integer getNroCep() {
        return nroCep;
    }

    public void setNroCep(Integer nroCep) {
        this.nroCep = nroCep;
    }

    public String getLatiLongGoogle(){
        return getLatitude().toString() + "+" + getLongitude().toString();
    }
    
    public String getLogradouroFormatadoGoogle() {
        return getNroCepFormatado() + "+" + getLogradouro().replace(" ", "+") + ",+" + getBairro().getDescBairro().replace(" ", "+")
                + ",+" + getBairro().getCidade().getUf().getSiglaUf() + ",+BR";
    }

    public String getNroCepFormatado() {
        String nroCepFormatado = "";
        try {
            if (nroCep.toString().length() < 8) {
                nroCepFormatado = "0" + nroCep;
            } else {
                nroCepFormatado = nroCep.toString();
            }
            nroCepFormatado = FuraFilaUtils.formataCep(nroCepFormatado);
        } catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

        return nroCepFormatado;

    }

    public void setNroCepFormatado(String nroCep) {
        if ("".equals(nroCep.replaceAll("[_|-]", ""))) {
            nroCep = "0";
        }
        this.nroCep = Integer.parseInt(nroCep.replace("-", ""));
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setLogradouroFormatado(String logradouroFormatado) {

        if (!"".equals(logradouroFormatado.trim())) {
            getTipoLogradouro().setDescTipoLogradouro(FuraFilaUtils.separarTipoLogradouro(logradouroFormatado).get(0));
            setLogradouro(FuraFilaUtils.separarTipoLogradouro(logradouroFormatado).get(1));
        }

    }

    public String getLogradouroFormatado() {
        return getTipoLogradouro().getDescTipoLogradouro() + " " + getLogradouro();
    }   
    
    public String getLogradouroCompleto() {
        return getTipoLogradouro().getDescTipoLogradouro() + " " + getLogradouro() + " - " + getBairro().getDescBairro() + ", " + getBairro().getCidade().getDescCidade() + " - " + getBairro().getCidade().getUf().getSiglaUf() + ", " + getNroCepFormatado();
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Logradouro clonar() {

        Logradouro logradouroObj = new Logradouro();
        logradouroObj.setNroCep(getNroCep());
        logradouroObj.setTipoLogradouro(getTipoLogradouro());
        logradouroObj.setLogradouro(getLogradouro());
        logradouroObj.setLatitude(getLatitude());
        logradouroObj.setLongitude(getLongitude());
        logradouroObj.setBairro(getBairro());

        return logradouroObj;
    }

    public boolean objetoVazio() {
        return 0 == getNroCep() && "".equals(getLogradouro()) && getTipoLogradouro().objetoVazio() && getBairro().objetoVazio();
    }

    public void zerarObjeto() {

        setNroCep(0);
        getTipoLogradouro();
        setLogradouro("");
        setLatitude(null);
        setLongitude(null);
        getBairro().zerarObjeto();

    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
