package payment.domain.service.contract;

import payment.application.dto.request.PaymentRequest;
import payment.application.dto.response.PaymentResponse;

import java.util.List;

public interface IPaymentService {

    List<PaymentResponse> getAll();

    PaymentResponse getPayment(Long id);

    PaymentResponse payment(PaymentRequest paymentRequest);

    PaymentResponse cancelPayment(Long id);
}
