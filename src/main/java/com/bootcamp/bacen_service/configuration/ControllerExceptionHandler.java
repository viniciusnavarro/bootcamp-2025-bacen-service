package com.bootcamp.bacen_service.configuration;

import com.bootcamp.bacen_service.exception.ChaveJaCadastradaException;
import com.bootcamp.bacen_service.exception.ChaveNaoLocalizadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ChaveJaCadastradaException.class)
    private ProblemDetail handlerChaveJaCadastrada(ChaveJaCadastradaException chaveException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, chaveException.getMessage());
        problemDetail.setTitle("Chave duplicada");
        problemDetail.setType(URI.create("http://localhost/9002/document/chave-ja-cadastrada"));
        return problemDetail;
    }

    @ExceptionHandler(ChaveNaoLocalizadaException.class)
    private ProblemDetail handlerChaveNaoLocalizada(ChaveNaoLocalizadaException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Chave não localizada");
        problemDetail.setType(URI.create("http://localhost/9002/document/chave-nao-existe"));
        return problemDetail;
    }

}
