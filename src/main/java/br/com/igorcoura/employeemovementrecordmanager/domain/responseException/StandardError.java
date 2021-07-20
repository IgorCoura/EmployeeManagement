package br.com.igorcoura.employeemovementrecordmanager.domain.responseException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class StandardError{
    private Instant timestamp;
    private String message;
    private String path;

}
