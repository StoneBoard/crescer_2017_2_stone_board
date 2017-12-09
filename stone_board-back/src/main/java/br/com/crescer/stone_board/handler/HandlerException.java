package br.com.crescer.stone_board.handler;

import br.com.crescer.stone_board.entity.model.MessageErrorModel;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@ControllerAdvice
public class HandlerException  {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageErrorModel processoValidacaoDeErro(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();

        return processFieldError(error);
    }

    private MessageErrorModel processFieldError(FieldError error) {
        MessageErrorModel message = null;
        if (error != null) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String errorMessage = messageSource.getMessage(error.getDefaultMessage(), null, currentLocale);
            message = new MessageErrorModel(errorMessage, "error");
        }
        return message;
    }
}
