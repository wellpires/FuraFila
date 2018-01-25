package br.com.furafila.validadores;

import java.text.ParseException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@FacesValidator("validarIdade")
public class ValidarIdade implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        try {
            
            int idade = FuraFilaUtils.calculaIdade((Date)value);

            if(idade < FuraFilaConstants.MAIOR_IDADE){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "O cliente deve ser maior de 18 anos", ""));
            }
            
        } catch (ParseException ex) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
        }

    }

}
