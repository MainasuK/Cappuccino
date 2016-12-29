package cappuccino.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by MainasuK on 2016-12-21.
 */

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(value = { IOException.class , RuntimeException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(Exception exception, WebRequest request) {
        exception.printStackTrace();
        return new ModelAndView("/error");
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView noMapping(Exception exception, WebRequest request) {
        exception.printStackTrace();
        return new ModelAndView("/404");
    }

}
