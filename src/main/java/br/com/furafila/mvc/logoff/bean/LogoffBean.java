package br.com.furafila.mvc.logoff.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@ViewScoped
public class LogoffBean implements Serializable {

	private static final Logger logger = LogManager.getLogger(LogoffBean.class);
	
    private static final long serialVersionUID = 1L;

    private Integer numero = 5;

    public Integer getNumero() {
        return numero;
    }

    public String getNumeroExibicao() {
        if (numero > 1) {
            return numero.toString() + " segundos";
        } else if (numero <= 1) {
            return numero.toString() + " segundo";
        }

        return null;
    }

    public void contador() {
        if (numero > 0) {
            numero--;
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
    			logger.error(ex.getMessage(), ex);
                FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
            } catch (Exception e) {
    			logger.error(e.getMessage(), e);
            	FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, e.getMessage());
			}
        }
    }

}
