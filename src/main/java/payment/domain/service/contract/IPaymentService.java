package payment.domain.service.contract;

import payment.application.dto.request.PaymentRequest;
import payment.application.dto.response.PaymentResponse;

import java.util.List;

public interface IPaymentService {

    List<PaymentResponse> getAll();

    PaymentResponse getById(Long id);

    PaymentResponse create(PaymentRequest paymentRequest);

    PaymentResponse update(Long id, PaymentRequest paymentRequest);

    void deleteById(Long id);
}
