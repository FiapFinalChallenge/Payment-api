package payment.domain.repository;

import payment.domain.model.Payment;

import java.util.List;
import java.util.Optional;

public interface IPaymentRepository {

    List<Payment> findAll();

    Optional<Payment> findById(Long id);

    Payment save(Payment payment);
}
