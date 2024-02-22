package payment.application.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {

    private String message;
    private String field;

    public ErrorMessage(String message) {
        this.message = message;
    }
}
