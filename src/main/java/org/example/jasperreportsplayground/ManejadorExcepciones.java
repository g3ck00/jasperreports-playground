package org.example.jasperreportsplayground;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //?
@Slf4j
public class ManejadorExcepciones {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidacion(MethodArgumentNotValidException ex){

        Map<String, String> errores=new HashMap();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error->errores.put(
                        error.getField(),
                        error.getDefaultMessage()
                ));

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> manejarConstraint(ConstraintViolationException ex){

        String errores = ex.getConstraintViolations().stream().map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .reduce("", (a,b) -> a + b + "\n");

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> manejarFormatoInvalido(HttpMessageNotReadableException ex){
        String mensaje=ex.getMessage();

        //debug
        /*System.out.println("EX:");
        System.out.println(ex.getMessage());

        System.out.println("CAUSE:");
        System.out.println(ex.getMostSpecificCause().getMessage());

        return ResponseEntity.badRequest().body(mensaje);*/

        if (mensaje.contains("LocalTime")){
            return ResponseEntity.badRequest().body("Formato inválido para hora. Use HH:MM (ej: 14:30)...");
        }

        if (mensaje.contains("LocalDate")){
            return ResponseEntity.badRequest().body("Formato inválido para fecha. Use AAAA-MM-DD (ej: 2026-12-25)...");
        }

        if (mensaje.contains("JSON")||mensaje.contains("Unexpected")||mensaje.contains("Unrecognized")||mensaje.contains("EOF")){
            return ResponseEntity.badRequest()
                    .body("JSON inválido o mal formado. Revise comas, comillas y estructura...");
        }

        return ResponseEntity.badRequest().body("Error en el formato del request.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarGeneral(Exception ex, HttpServletRequest request){
        log.error("""
            Error no controlado:
            Tipo: {}
            Mensaje: {}
            URL: {}
            Método: {}
            """,
                ex.getClass().getName(),
                ex.getMessage(),
                request.getRequestURI(),
                request.getMethod(),
                ex
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno...");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> manejarRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest()
                .body(ex.getMessage());
    }

    /*
    ////// Excepciones del entorno JWT
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CredencialesInvalidasDTO> handleBadCredentials(
            BadCredentialsException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new CredencialesInvalidasDTO(
                        401,
                        "Email o contraseña incorrecta...",
                        LocalDateTime.now()
                ));
    }
    */

    @ExceptionHandler(JRValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleJRValidationException(JRValidationException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "JasperReports Validation Error");
        error.put("message", ex.getMessage());

        return error;
    }

    @ExceptionHandler(JRException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleJRException(JRException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "JasperReports Error");
        error.put("message", ex.getMessage());

        return error;
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleIOException(IOException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "I/O Error");
        error.put("message", ex.getMessage());

        return error;
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleIllegalStateException(IllegalStateException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Illegal State");
        error.put("message", ex.getMessage());

        return error;
    }
}