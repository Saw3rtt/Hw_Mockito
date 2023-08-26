package com.skypro.hw_springandmockito.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "В имени могут быть только буквы")
public class WrongNameException extends RuntimeException {
}
