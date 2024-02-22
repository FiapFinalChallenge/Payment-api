package payment.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.application.controller.contract.IPaymentController;
import payment.application.dto.request.PaymentRequest;
import payment.application.dto.response.PaymentResponse;
import payment.domain.service.contract.IPaymentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/payments")
public class PaymentController implements IPaymentController {

    private final IPaymentService service;

    @Override
    public List<PaymentResponse> getAll() {
        return service.getAll();
    }

    @Override
    public PaymentResponse getPayment(Long id) {
        return service.getPayment(id);
    }

    @Override
    public PaymentResponse payment(PaymentRequest paymentRequest) {
        return service.payment(paymentRequest);
    }

    @Override
    public PaymentResponse cancelPayment(Long id) {
        return service.cancelPayment(id);
    }
}
