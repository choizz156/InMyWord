package inmyword.server.exception;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
public class ErrorResponse {

    private List<FieldError> fieldErrors;

    public ErrorResponse(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }


}

