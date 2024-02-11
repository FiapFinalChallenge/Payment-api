package payment.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import payment.domain.model.Payment;
import payment.domain.repository.IPaymentRepository;

@Repository
public interface PaymentRepositoryJpa extends JpaRepository<Payment, Long>, IPaymentRepository {
}
