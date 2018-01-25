package br.com.furafila.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.furafila.utils.FuraFilaConstants;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@FacesValidator("validarEmail")
public class ValidarEmail implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        pattern = Pattern.compile(FuraFilaConstants.PADRAO_EMAIL);
        
        if (!validarEmail(value.toString())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, FuraFilaConstants.AVISO_EMAIL_INVALIDO, FuraFilaConstants.AVISO_EMAIL_INVALIDO));
        }

    }

    public boolean validarEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
