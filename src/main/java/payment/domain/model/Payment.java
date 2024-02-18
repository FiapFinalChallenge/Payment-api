package payment.domain.model;

import jakarta.persistence.*;
import lombok.*;
import payment.domain.enums.EPaymentStatus;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cart_id", nullable = false)
    private Long cartId;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EPaymentStatus status = EPaymentStatus.PENDING;
}
