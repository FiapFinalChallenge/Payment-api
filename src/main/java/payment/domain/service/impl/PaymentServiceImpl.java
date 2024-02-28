package payment.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import payment.application.dto.request.PaymentRequest;
import payment.application.dto.response.PaymentResponse;
import payment.application.mapper.PaymentMapper;
import payment.domain.enums.EPaymentStatus;
import payment.domain.model.Payment;
import payment.domain.repository.IPaymentRepository;
import payment.domain.service.contract.IPaymentService;
import payment.infra.external.client.CartClient;
import payment.infra.external.client.ItemClient;
import payment.infra.external.dto.response.CartItemResponse;
import payment.infra.external.dto.response.CartResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private static final String PAYMENT_NOT_FOUND = "Not found payment ID: ";
    private static final String PAYMENT_CART_ALREADY_EXISTS = "A cart can only be associated with one payment.";
    private final IPaymentRepository repository;
    private final CartClient cartClient;
    private final ItemClient itemClient;
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
        try {
            changeInformationStatusCompleted(paymentRequest);

            return mapper.convertToPaymentResponse(repository
                    .save(mapper.convertToPayment(paymentRequest)));
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(PAYMENT_CART_ALREADY_EXISTS);
        }
    }

    @Override
    public PaymentResponse cancelPayment(Long id) {
        var payment = getPaymentById(id);
        changeInformationStatusCancelled(payment);

        return mapper.convertToPaymentResponse(repository.save(payment));
    }

    private void changeInformationStatusCompleted(PaymentRequest paymentRequest) {
        var cart = getCartById(paymentRequest.getCartId());

        paymentRequest.setValue(cart.totalValue());
        paymentRequest.setStatus(EPaymentStatus.COMPLETED);
        decreaseItemAmount(cart.items());
    }

    private void changeInformationStatusCancelled(Payment payment) {
        var cart = getCartById(payment.getCartId());

        payment.setStatus(EPaymentStatus.CANCELLED);
        increaseItemAmount(cart.items());
    }

    private CartResponse getCartById(Long id) {
        return cartClient.getCartById(id);
    }

    private void decreaseItemAmount(List<CartItemResponse> items) {
        items.forEach(item -> itemClient.decreaseItemAmount(item.itemId(), item.amount()));
    }

    private void increaseItemAmount(List<CartItemResponse> items) {
        items.forEach(item -> itemClient.increaseItemAmount(item.itemId(), item.amount()));
    }

    private Payment getPaymentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PAYMENT_NOT_FOUND + id));
    }
}
