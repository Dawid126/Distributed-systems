package distributed.systems.restapihomework.advice;

import distributed.systems.restapihomework.exception.BeginningDateAfterEndException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class BadParamsAdvice {

    @ExceptionHandler({MissingServletRequestParameterException.class, BeginningDateAfterEndException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleMethodArgumentNotValid(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "bad_request";
    }
}
