package payment.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import payment.application.dto.request.PaymentRequest;
import payment.application.dto.response.PaymentResponse;
import payment.application.mapper.PaymentMapper;
import payment.domain.enums.EPaymentStatus;
import payment.domain.model.Payment;
import payment.domain.repository.IPaymentRepository;
import payment.domain.service.contract.IPaymentService;
import payment.infra.external.CartClient;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private static final String PAYMENT_NOT_FOUND = "Not found payment ID: ";
    private final IPaymentRepository repository;
    private final CartClient cartClient;
    private final PaymentMapper mapper;

    @Override
    public List<PaymentResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::convertToPaymentResponse)
                .toList();
    }

    @Override
    public PaymentResponse getPayment(Long id) {
        return mapper.convertToPaymentResponse(getPaymentById(id));
    }

    @Override
    public PaymentResponse payment(PaymentRequest paymentRequest) {
        paymentRequest.setValue(getTotalValue(paymentRequest.getCartId()));

        return mapper.convertToPaymentResponse(repository
                .save(mapper.convertToPayment(paymentRequest)));
    }

    @Override
    public PaymentResponse cancelPayment(Long id) {
        var payment = getPaymentById(id);
        payment.setStatus(EPaymentStatus.CANCELLED);

        return mapper.convertToPaymentResponse(repository.save(payment));
    }

    private BigDecimal getTotalValue(Long cartId) {
        return cartClient.getCartById(cartId).totalValue();
    }

    private Payment getPaymentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PAYMENT_NOT_FOUND + id));
    }
}
