package payment.domain.service.contract;

import payment.application.dto.response.PaymentResponse;

import java.util.List;

public interface IPaymentService {

    List<PaymentResponse> getAll();
}
