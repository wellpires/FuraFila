package br.com.furafila.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gon�alves Pires
 */
public class CpfConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        String cpf = "";
        if(value != null && !"".equals(value)){
            cpf = value.replaceAll("[.|-]", "");;
        }
        return cpf;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        String cpf = "";
        if(value != null && value.toString().length() == 11){
            try {
                cpf = FuraFilaUtils.formataCpf(value);
            } catch (Exception ex) {
                FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
            }
        }
        
        return cpf;
        
    }
    
}
