package com.autismcard.autismcard.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Cria uma exceção personalizada para indicar que um recurso não foi encontrado.
 */
@Getter
@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }

}
