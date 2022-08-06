package platform.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class Advisor {

    private static final Logger LOGGER=Logger.getLogger(Advisor.class.getSimpleName());

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public void handleException(NotFoundException e){
        LOGGER.log(Level.INFO,e.getMessage());
    }
}
