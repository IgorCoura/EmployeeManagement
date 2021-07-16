package br.com.igorcoura.employeemanagement.domain.responseException;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class StandardError{
    private Instant timestamp;
    private String message;
    private String path;

}
