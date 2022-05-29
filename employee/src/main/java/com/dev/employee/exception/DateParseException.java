package com.dev.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateParseException extends Exception {
}
