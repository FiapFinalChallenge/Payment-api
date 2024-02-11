package payment.domain.repository;

import payment.domain.model.Payment;

import java.util.List;

public interface IPaymentRepository {

    List<Payment> findAll();
}
