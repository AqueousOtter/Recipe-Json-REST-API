package dev.dustinb.RESTDemo.advice;

import dev.dustinb.RESTDemo.exception.ErrorMessage;
import dev.dustinb.RESTDemo.exception.RecipesException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecipeAdvice {


    //handles saving two of the same recipes, returns error message
    @ExceptionHandler(RecipesException.class)
    public ResponseEntity<ErrorMessage> handleRecipeExists(RecipesException RecipesException){
        ErrorMessage exceptionResponse = new ErrorMessage(RecipesException.getErrorMessage());
        return new ResponseEntity<ErrorMessage>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
