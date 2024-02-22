package payment.domain.exception;

public class PaymentApiException extends RuntimeException {

    public PaymentApiException(String message) {
        super(message);
    }
}
