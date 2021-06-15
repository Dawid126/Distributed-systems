package distributed.systems.restapihomework.advice;

import distributed.systems.restapihomework.exception.LocationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LocationNotFoundAdvice {

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(LocationNotFoundException ex, Model model) {
        model.addAttribute("locationName", ex.getMessage());
        return "location_not_found";
    }
}
