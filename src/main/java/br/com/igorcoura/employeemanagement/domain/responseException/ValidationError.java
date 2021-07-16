package br.com.igorcoura.employeemanagement.domain.responseException;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
public class ValidationError {
    private Instant timestamp;
    private HttpStatus status;
    private String objectName;
    private List<String> errors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationError that = (ValidationError) o;
        return status == that.status && Objects.equals(objectName, that.objectName) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, objectName, errors);
    }
}
