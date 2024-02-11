package payment.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.application.controller.contract.IPaymentController;
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
}
