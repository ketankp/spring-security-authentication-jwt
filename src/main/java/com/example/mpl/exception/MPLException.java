package com.example.mpl.exception;

import com.example.mpl.constants.MPLConstants;
import com.example.mpl.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MPLException {

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ExceptionResponseDto> badRequestException(CustomBadRequestException customBadRequestException){
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(MPLConstants.FAILED)
                .message(customBadRequestException.getMessage())
                .description(customBadRequestException.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDto);
    }

    @ExceptionHandler(CustomSqlException.class)
    public ResponseEntity<ExceptionResponseDto> sqlException(CustomSqlException customSqlException){
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(MPLConstants.FAILED)
                .message(customSqlException.getMessage())
                .description(customSqlException.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDto);
    }

    @ExceptionHandler(CustomAuthorizationException.class)
    public ResponseEntity<ExceptionResponseDto> authorizationException(CustomAuthorizationException customAuthorizationException){
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .status(MPLConstants.FAILED)
                .message(customAuthorizationException.getMessage())
                .description(customAuthorizationException.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        List<String> stringList = new ArrayList<String>();
        for (FieldError error : errors ) {
            stringList.add(error.getDefaultMessage());
        }
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(MPLConstants.FAILED)
                .message(String.join(",", stringList))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDto);
    }

    @ExceptionHandler(CustomInternalServerException.class)
    public ResponseEntity<ExceptionResponseDto> customInternalServerErrorException(CustomInternalServerException customInternalServerException){
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(MPLConstants.FAILED)
                .message(customInternalServerException.getMessage())
                .description(customInternalServerException.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDto);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponseDto> accessDeniedException(AccessDeniedException accessDeniedException){
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .status(MPLConstants.FAILED)
                .message(accessDeniedException.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponseDto);
    }

}
