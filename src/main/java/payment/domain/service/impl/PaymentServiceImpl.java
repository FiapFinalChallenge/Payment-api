package payment.domain.service.impl;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import payment.application.dto.request.PaymentRequest;
import payment.application.dto.response.PaymentResponse;
import payment.application.mapper.PaymentMapper;
import payment.domain.repository.IPaymentRepository;
import payment.domain.service.contract.IPaymentService;
import payment.infra.external.CartClient;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

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
    public PaymentResponse getById(Long id) {
        return mapper.convertToPaymentResponse(repository
                .findById(id).orElseThrow(() -> new NotFoundException("No such payment")));
    }

    @Override
    public PaymentResponse create(PaymentRequest paymentRequest) {
        paymentRequest.setValue(getTotalValue(paymentRequest.getCartId()));

        return mapper.convertToPaymentResponse(repository
                .save(mapper.convertToPayment(paymentRequest)));
    }

    @Override
    public PaymentResponse update(Long id, PaymentRequest paymentRequest) {
        getById(id);
        return mapper.convertToPaymentResponse(repository
                .save(mapper.convertToPaymentWithId(paymentRequest, id)));
    }

    @Override
    public void deleteById(Long id) {
        getById(id);
        repository.deleteById(id);
    }

    private BigDecimal getTotalValue(Long cartId) {
        return cartClient.getCartById(cartId).totalValue();
    }
}
